package elec332.alchemicalbrewing.registry;

import elec332.alchemicalbrewing.util.PotionFluid;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by Elec332 on 18-8-2015.
 */
public abstract interface IPotionRecipe {

    public ItemStack getIngredient();

    public Fluid getFluidInput();

    public PotionFluid getOutPut();

}
