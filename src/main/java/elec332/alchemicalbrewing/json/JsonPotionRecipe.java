package elec332.alchemicalbrewing.json;

import elec332.alchemicalbrewing.registry.IPotionRecipe;
import elec332.alchemicalbrewing.registry.PotionRegistry;
import elec332.alchemicalbrewing.registry.WrappedPotion;
import elec332.alchemicalbrewing.util.PotionFluid;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

import java.io.Serializable;

/**
 * Created by Elec332 on 18-8-2015.
 */
public class JsonPotionRecipe implements IPotionRecipe, Serializable {

    public JsonPotionRecipe(ItemStack ingredient, WrappedPotion input, WrappedPotion outputPotion){
        this.ingredient = ingredient;
        this.input = input;
        this.outputPotion = outputPotion;
    }

    /**
     * Json constructor
     */
    public JsonPotionRecipe(){
    }

    private ItemStack ingredient;
    private WrappedPotion input;
    private WrappedPotion outputPotion;

    @Override
    public ItemStack getIngredient() {
        return ingredient;
    }

    @Override
    public Fluid getFluidInput() {
        return input.isAwkwardPotion() ? PotionRegistry.awkwardFluid : PotionRegistry.instance.getFluid(input);
    }

    @Override
    public PotionFluid getOutPut() {
        return PotionRegistry.instance.getFluid(outputPotion);
    }
}
