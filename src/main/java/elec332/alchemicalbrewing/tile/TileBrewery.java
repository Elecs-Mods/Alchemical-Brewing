package elec332.alchemicalbrewing.tile;

import elec332.alchemicalbrewing.multiblock.ABMultiBlockTileBase;
import elec332.alchemicalbrewing.registry.PotionRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by Elec332 on 9-8-2015.
 */
public class TileBrewery extends ABMultiBlockTileBase {

    @Override
    public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (ForgeDirection.getOrientation(side) == ForgeDirection.UP && player.getHeldItem() != null && ItemStack.areItemStacksEqual(player.getHeldItem(), PotionRegistry.awkwardPotion))
            if (getMultiBlock() != null) {
                getMultiBlock().getInternalTank().fill(new FluidStack(PotionRegistry.awkwardFluid, 1000), true);
                return true;
            }
        return super.onBlockActivated(player, side, hitX, hitY, hitZ);
    }
}
