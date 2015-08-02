package elec332.alchemicalbrewing.multiblock;

import elec332.alchemicalbrewing.AlchemicalBrewing;
import elec332.core.baseclasses.tileentity.IInventoryTile;
import elec332.core.inventory.BaseContainer;
import elec332.core.multiblock.AbstractMultiBlock;
import elec332.core.multiblock.AbstractMultiBlockTile;
import elec332.core.world.WorldHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidTank;

/**
 * Created by Elec332 on 29-7-2015.
 */
public abstract class ABMultiBlockBase extends AbstractMultiBlock implements IInventoryTile{

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

    public TileEntity getSaveDelegate(){
        return WorldHelper.getTileAt(getWorldObj(), getLocation());
    }

    public void markDirty(){
        getSaveDelegate().markDirty();
        //((TileBase)getSaveDelegate()).syncData();
    }

    public void writeToNBT(NBTTagCompound tagCompound){
        internalTank.writeToNBT(tagCompound);
    }

    public void readFromNBT(NBTTagCompound tagCompound){
        internalTank.readFromNBT(tagCompound);
    }

    public boolean openGui(EntityPlayer player){
        player.openGui(AlchemicalBrewing.instance, 0, getWorldObj(), getLocation().xCoord, getLocation().yCoord, getLocation().zCoord);
        return true;
    }

    public Container getGuiServer(EntityPlayer player){
        return (Container) getGui(player, false);
    }

    @Override
    public Object getGuiClient(EntityPlayer player) {
        return getGui(player, true);
    }

    public abstract Object getGui(EntityPlayer player, boolean client);
}
