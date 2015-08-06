package elec332.alchemicalbrewing.tile;
import elec332.alchemicalbrewing.multiblock.ABMultiBlockTileBase;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class TileMundaneTankPart extends ABMultiBlockTileBase implements IFluidHandler{

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (getMultiBlock() == null || from != ForgeDirection.UP || resource.getFluid() != FluidRegistry.WATER)
            return 0;
        return getMultiBlock().getInternalTank().fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return fluid == FluidRegistry.WATER && getMultiBlock() != null;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        if (getMultiBlock() == null)
            return new FluidTankInfo[0];
        return new FluidTankInfo[]{
                getMultiBlock().getInternalTank().getInfo()
        };
    }
}
