package elec332.alchemicalbrewing.multiblock;

import elec332.core.multiblock.AbstractMultiBlock;
import elec332.core.multiblock.AbstractMultiBlockTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidTank;

/**
 * Created by Elec332 on 29-7-2015.
 */
public abstract class ABMultiBlockBase extends AbstractMultiBlock {

    public ABMultiBlockBase(FluidTank tank){
        super();
        this.internalTank = tank;
    }

    private FluidTank internalTank;

    public FluidTank getInternalTank() {
        return internalTank;
    }

    public boolean isSaveDelegate(AbstractMultiBlockTile tile){
        return tile.myLocation().equals(getLocation());
    }

    public void writeToNBT(NBTTagCompound tagCompound){
        internalTank.writeToNBT(tagCompound);
    }

    public void readFromNBT(NBTTagCompound tagCompound){
        internalTank.readFromNBT(tagCompound);
    }
}
