package elec332.alchemicalbrewing.multiblock;

import elec332.alchemicalbrewing.AlchemicalBrewing;
import elec332.core.multiblock.AbstractMultiBlockTile;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class ABMultiBlockTileBase extends AbstractMultiBlockTile {

    public ABMultiBlockTileBase() {
        super(AlchemicalBrewing.multiBlockRegistry);
    }

    public ABMultiBlockBase getMultiBlock(){
        return super.getMultiBlock() instanceof ABMultiBlockBase ? (ABMultiBlockBase) super.getMultiBlock() : null;
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        if (getMultiBlock() != null){
            if (getMultiBlock().isSaveDelegate(this))
                getMultiBlock().writeToNBT(tagCompound);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        if (getMultiBlock() != null){
            if (getMultiBlock().isSaveDelegate(this))
                getMultiBlock().readFromNBT(tagCompound);
        }
    }
}
