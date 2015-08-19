package elec332.alchemicalbrewing.registry;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elec332.alchemicalbrewing.AlchemicalBrewing;
import elec332.alchemicalbrewing.json.DefaultRecipes;
import elec332.alchemicalbrewing.json.JsonPotionRecipe;
import elec332.alchemicalbrewing.json.Serializers;
import elec332.alchemicalbrewing.util.PotionFluid;
import elec332.core.helper.FileHelper;
import elec332.core.json.ExtraTypeAdapters;
import elec332.core.json.JsonHandler;
import elec332.core.main.ElecCore;
import elec332.core.util.BasicInventory;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.fluids.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Elec332 on 29-7-2015.
 */
public final class PotionRegistry {

    public static final PotionRegistry instance = new PotionRegistry();
    private PotionRegistry(){
        this.registry = Maps.newHashMap();
        this.potionToStack = Maps.newHashMap();
        this.alchemicalBrewingFluids = Maps.newHashMap();
        this.potionRecipes = Lists.newArrayList();
        ExtraTypeAdapters.allFactories.add(Serializers.wrappedPotion);
    }

    private Map<WrappedPotion, PotionFluid> alchemicalBrewingFluids;
    private Map<Fluid, ItemStack> registry;
    public Map<WrappedPotion, ItemStack> potionToStack;
    private List<IPotionRecipe> potionRecipes;

    public static final Fluid awkwardFluid = new Fluid("awkwardFluid");

    public static final ItemStack awkwardPotion = new ItemStack(Items.potionitem, 1, 16);

    public void registerRecipe(IPotionRecipe potionRecipe){
        this.potionRecipes.add(potionRecipe);
    }

    public ItemStack fillBottle(FluidTank fluidTank){
        if (fluidTank == null || fluidTank.getFluid() == null || fluidTank.getFluid().getFluid() == null || fluidTank.getFluid().amount < FluidContainerRegistry.BUCKET_VOLUME)
            return null;
        fluidTank.getFluid().amount -= FluidContainerRegistry.BUCKET_VOLUME;
        return getStackFromFluid(fluidTank.getFluid().getFluid());
    }

    public ItemStack getStackFromFluid(Fluid fluid){
        if (fluid == null)
            throw new IllegalArgumentException("Cannot get potion from a null fluid!");
        try {
            return registry.get(fluid).copy();
        } catch (NullPointerException e){
            throw new IllegalArgumentException("There is no potion for fluid: "+fluid.getName());
        }
    }

    public boolean processRecipe(boolean process, FluidTank first, BasicInventory inventory, FluidTank second){
        if (inventory.getStackInSlot(0) == null || first.getFluid() == null || first.getFluid().getFluid() == null || first.getFluidAmount() < 1000)
            return false;
        PotionFluid fluid = processRecipe(first.getFluid().getFluid(), inventory, process);
        if (fluid != null){
            FluidStack s1 = first.drain(1000, process);
            if (s1.amount < 1000)
                return false;
            int i = second.fill(new FluidStack(fluid, 3000), process);
            return !(i < 3000 && !process);
        }
        return false;
    }

    private PotionFluid processRecipe(Fluid input, BasicInventory inventory, boolean process){
        IPotionRecipe processed = processRecipe(input, inventory.getStackInSlot(0));
        if (processed != null){
            if (process)
                inventory.decrStackSize(0, processed.getIngredient().stackSize);
            return processed.getOutPut();
        }
        return null;
    }

    private IPotionRecipe processRecipe(Fluid input, ItemStack inputStack){
        if (input != null && inputStack != null && inputStack.getItem() != null && inputStack.stackSize > 0){
            for (IPotionRecipe potionRecipe : potionRecipes){
                if (potionRecipe.getFluidInput() == input && inputStack.getItem() == potionRecipe.getIngredient().getItem() && inputStack.getItemDamage() == potionRecipe.getIngredient().getItemDamage() && inputStack.stackSize >= potionRecipe.getIngredient().stackSize)
                    return potionRecipe;
            }
        }
        return null;
    }

    public void preInitFluids(){
        FluidRegistry.registerFluid(awkwardFluid);
    }

    private void registerFluid(WrappedPotion potion){
        PotionFluid fluid = new PotionFluid(potion);
        FluidRegistry.registerFluid(fluid);
        alchemicalBrewingFluids.put(potion, fluid);
    }

    public void init(){
        registry.put(awkwardFluid, awkwardPotion);
        potionToStack.put(new WrappedPotion(Potion.heal, 1, false), potionStack(8197));
        potionToStack.put(new WrappedPotion(Potion.heal, 2, false), potionStack(8229));
        potionToStack.put(new WrappedPotion(Potion.heal, 1, true), potionStack(16389));
        potionToStack.put(new WrappedPotion(Potion.heal, 2, true), potionStack(16421));
        potionToStack.put(new WrappedPotion(Potion.harm, 1, false), potionStack(8204));
        potionToStack.put(new WrappedPotion(Potion.harm, 2, false), potionStack(8236));
        potionToStack.put(new WrappedPotion(Potion.harm, 1, true), potionStack(16396));
        potionToStack.put(new WrappedPotion(Potion.harm, 2, true), potionStack(16428));
        for (WrappedPotion potion : potionToStack.keySet()){
            registerFluid(potion);
        }
        File file = new File(AlchemicalBrewing.configFolder, "recipes.json");
        if (!file.exists()){
            try {
                if (!file.getParentFile().mkdirs() && !file.createNewFile())
                    throw new IOException();
            } catch (IOException e){
                throw new RuntimeException(e);
            }
            JsonArray array = JsonHandler.newJsonArray();
            for (JsonPotionRecipe recipe : DefaultRecipes.recipes)
                array.add(JsonHandler.toJsonElement(recipe, JsonPotionRecipe.class));
            JsonHandler.toFile(file, JsonHandler.newJsonEntry("recipes", array));
        }
        try {
            List<JsonPotionRecipe> recipes= JsonHandler.getDataAsList(JsonHandler.getMainFileObject(file), "recipes", JsonPotionRecipe.class);
            potionRecipes.addAll(recipes);
        } catch (FileNotFoundException e){
            AlchemicalBrewing.logger.error("Error reading Json file: ", e);
        }
    }

    public boolean isValidBottleStack(ItemStack stack, FluidStack fluidStack){
        return isBottle(stack) && fluidStack != null && (fluidStack.getFluid() instanceof PotionFluid) && (((PotionFluid) fluidStack.getFluid()).isExplosive() == isSplashBottle(stack));
    }

    public ItemStack fillBottle(FluidStack fluidStack){
        return potionToStack.get(((PotionFluid)fluidStack.getFluid()).getPotion()).copy();
    }

    public boolean isBottle(ItemStack stack){
        return stack != null && stack.getItem() == Items.glass_bottle;
    }

    public boolean isSplashBottle(ItemStack stack){
        return false;
    }

    public boolean isPotion(ItemStack stack){
        return stack != null && stack.getItem() == Items.potionitem;
    }

    public Fluid getFluid(ItemStack stack){
        if (!isPotion(stack))
            return null;
        if (ItemStack.areItemStacksEqual(stack, awkwardPotion))
            return awkwardFluid;
        for (Map.Entry<WrappedPotion, ItemStack> entry : potionToStack.entrySet()){
            if (entry.getValue().getItem() == stack.getItem() && stack.getItemDamage() == entry.getValue().getItemDamage())
                return alchemicalBrewingFluids.get(entry.getKey());
        }
        return null;
    }

    private ItemStack potionStack(int meta){
        return new ItemStack(Items.potionitem, 1, meta);
    }

    public PotionFluid getFluid(WrappedPotion potion){
        return alchemicalBrewingFluids.get(potion);
    }

    @SideOnly(Side.CLIENT)
    public void registerTextures(IIconRegister iIconRegister){
        for (Fluid fluid : alchemicalBrewingFluids.values())
            fluid.setStillIcon(FluidRegistry.WATER.getStillIcon());
        awkwardFluid.setStillIcon(FluidRegistry.WATER.getStillIcon());
    }

    public static Potion getPotionByName(String name){
        for (Potion potion : Potion.potionTypes){
            if (potion != null && potion.getName().equals(name))
                return potion;
        }
        return null;
    }

}
