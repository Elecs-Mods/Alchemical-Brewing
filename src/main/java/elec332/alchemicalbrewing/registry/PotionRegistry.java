package elec332.alchemicalbrewing.registry;

import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidTank;

import java.util.Map;

/**
 * Created by Elec332 on 29-7-2015.
 */
public final class PotionRegistry {

    public static final PotionRegistry instance = new PotionRegistry();
    private PotionRegistry(){
        this.registry = Maps.newHashMap();
        this.potionToStack = Maps.newHashMap();
    }

    private Map<Fluid, ItemStack> registry;
    public Map<WrappedPotion, ItemStack> potionToStack;

    public static final Fluid awkwardFluid = new Fluid("awkwardFluid");

    public static final ItemStack awkwardPotion = new ItemStack(Items.potionitem, 1, 16);

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

    public void preInitFluids(){
        FluidRegistry.registerFluid(awkwardFluid);
    }

    public void init(){
        registry.put(awkwardFluid, awkwardPotion);
        potionToStack.put(new WrappedPotion(Potion.heal, 1), new ItemStack(Items.potionitem, 1, 8197));
    }

    @SideOnly(Side.CLIENT)
    public void registerTextures(IIconRegister iIconRegister){
        awkwardFluid.setStillIcon(FluidRegistry.WATER.getStillIcon());
    }

}
