package elec332.alchemicalbrewing.tile;

import elec332.alchemicalbrewing.multiblock.ABMultiBlockTileBase;
import elec332.alchemicalbrewing.multiblock.MundaneTank;
import elec332.core.multiblock.AbstractMultiBlockTile;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class TileChemicalTank extends ABMultiBlockTileBase implements IFluidHandler{

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (getMultiBlockTank() == null)
            return 0;
        return getMultiBlockTank().getInternalTank().fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (getMultiBlockTank() == null)
            return null;
        FluidTank tank = getMultiBlockTank().getInternalTank();
        if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
            return null;
        }
        return tank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        if (getMultiBlockTank() == null)
            return null;
        return getMultiBlockTank().getInternalTank().drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return getMultiBlockTank() != null;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return getMultiBlockTank() != null && getMultiBlockTank().canDrain();
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        if (getMultiBlockTank() == null)
            return new FluidTankInfo[0];
        return new FluidTankInfo[]{
                getMultiBlockTank().getInternalTank().getInfo()
        };
    }

    private MundaneTank getMultiBlockTank(){
        return getMultiBlock() instanceof MundaneTank ? (MundaneTank) getMultiBlock() : null;
    }
}
