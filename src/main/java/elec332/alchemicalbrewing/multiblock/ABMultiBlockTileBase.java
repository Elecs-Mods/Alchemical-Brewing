package elec332.alchemicalbrewing.multiblock;

import elec332.alchemicalbrewing.AlchemicalBrewing;
import elec332.core.baseclasses.tileentity.IInventoryTile;
import elec332.core.inventory.BaseContainer;
import elec332.core.main.ElecCore;
import elec332.core.multiblock.AbstractMultiBlockTile;
import elec332.core.player.PlayerHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class ABMultiBlockTileBase extends AbstractMultiBlockTile implements IInventoryTile{

    public ABMultiBlockTileBase() {
        super(AlchemicalBrewing.multiBlockRegistry);
    }

    @Override
    public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (this instanceof IFluidHandler) {
            ItemStack stack = player.getCurrentEquippedItem();
            if (FluidContainerRegistry.isContainer(stack) && getMultiBlock() != null) {
                FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(stack);
                if (liquid != null) {
                    int i = ((IFluidHandler) this).fill(ForgeDirection.getOrientation(side), liquid, true);
                    if (i != 0 && !PlayerHelper.isPlayerInCreative(player)) {
                        if (stack.stackSize > 1) {
                            if (!player.inventory.addItemStackToInventory(FluidContainerRegistry.drainFluidContainer(stack))) {
                                player.dropPlayerItemWithRandomChoice(FluidContainerRegistry.drainFluidContainer(stack), false);
                            }
                            stack.splitStack(1);
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, stack);
                        } else {
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, FluidContainerRegistry.drainFluidContainer(stack));
                        }
                    }
                    return true;
                }
            }
        }
        return super.onBlockActivated(player, side, hitX, hitY, hitZ);
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
    public void readFromNBT(final NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        ElecCore.tickHandler.registerCall(new Runnable() {
            @Override
            public void run() {
                if (getMultiBlock() != null){
                    if (getMultiBlock().isSaveDelegate(ABMultiBlockTileBase.this))
                        getMultiBlock().readFromNBT(tagCompound);
                }
            }
        });
    }


    @Override
    public Container getGuiServer(EntityPlayer player) {
        return getMultiBlock() == null ? null : getMultiBlock().getGuiServer(player);
    }

    @Override
    public Object getGuiClient(EntityPlayer player) {
        return getMultiBlock() == null ? null : getMultiBlock().getGuiClient(player);
    }
}
