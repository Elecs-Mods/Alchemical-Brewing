package elec332.alchemicalbrewing.tile;

import elec332.alchemicalbrewing.multiblock.ABMultiBlockTileBase;
import elec332.alchemicalbrewing.registry.PotionRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by Elec332 on 9-8-2015.
 */
public class TileBrewery extends ABMultiBlockTileBase {

    @Override
    public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (ForgeDirection.getOrientation(side) == ForgeDirection.UP && PotionRegistry.instance.isPotion(player.getHeldItem())) {
            if (getMultiBlock() != null ) {
                Fluid fluid = PotionRegistry.instance.getFluid(player.getHeldItem());
                if (fluid != null) {
                    getMultiBlock().getInternalTank().fill(new FluidStack(fluid, 1000), true);
                    player.inventory.decrStackSize(player.inventory.currentItem, 1);
                    return true;
                }
            }
        }
        return super.onBlockActivated(player, side, hitX, hitY, hitZ);
    }
}
