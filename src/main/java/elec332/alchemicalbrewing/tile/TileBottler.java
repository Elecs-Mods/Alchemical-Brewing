package elec332.alchemicalbrewing.tile;

import elec332.alchemicalbrewing.AlchemicalBrewing;
import elec332.alchemicalbrewing.client.gui.BasicGui;
import elec332.alchemicalbrewing.multiblock.ABMultiBlockTileBase;
import elec332.alchemicalbrewing.multiblock.Brewery;
import elec332.alchemicalbrewing.registry.PotionRegistry;
import elec332.core.inventory.BaseContainer;
import elec332.core.inventory.ContainerMachine;
import elec332.core.inventory.IHasProgressBar;
import elec332.core.inventory.ITileWithSlots;
import elec332.core.inventory.widget.FluidTankWidget;
import elec332.core.inventory.widget.WidgetProgressArrow;
import elec332.core.util.BasicInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Elec332 on 18-8-2015.
 */
public class TileBottler extends ABMultiBlockTileBase implements ITileWithSlots, IHasProgressBar{

    public TileBottler(){
        this.inventory = new BasicInventory("name", 2, this);
        this.progress = 0;
    }

    private BasicInventory inventory;
    private int progress;

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (canWork()) {
            if (progress < 240) {
                progress++;
            } else {
                progress = 0;
                process();
            }
        } else if (progress > 0){
            progress = 0;
        }
    }

    private boolean canWork(){
        return getMultiBlock() != null && ((Brewery)getMultiBlock()).getPotionTank().getFluidAmount() >= 1000 && PotionRegistry.instance.isValidBottleStack(inventory.getStackInSlot(0), ((Brewery)getMultiBlock()).getPotionTank().getFluid()) && inventory.getStackInSlot(1) == null;
    }

    private void process(){
        inventory.decrStackSize(0, 1);
        inventory.setInventorySlotContents(1, PotionRegistry.instance.fillBottle(((Brewery)getMultiBlock()).getPotionTank().getFluid()));
        ((Brewery)getMultiBlock()).getPotionTank().drain(1000, true);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        inventory.writeToNBT(tagCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        inventory.readFromNBT(tagCompound);
    }

    @Override
    public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        return getMultiBlock() != null && openGui(player, AlchemicalBrewing.instance, 0);
    }

    @Override
    public BaseContainer getGuiServer(EntityPlayer player) {
        return new ContainerMachine(this, player, 0);
    }

    @Override
    public Object getGuiClient(EntityPlayer player) {
        return new BasicGui(getGuiServer(player), "cannery.png");
    }

    @Override
    public void addSlots(BaseContainer baseContainer) {
        baseContainer.addSlotToContainer(new Slot(inventory, 0, 80, 17));
        baseContainer.addSlotToContainer(new Slot(inventory, 1, 134, 42));
        baseContainer.addPlayerInventoryToContainer();
        baseContainer.addWidget(new FluidTankWidget(14, 19, 176, 0, 32, 44, ((Brewery)getMultiBlock()).getPotionTank()));
        baseContainer.addWidget(new WidgetProgressArrow(76, 42, this, true));
    }

    @Override
    public int getProgress() {
        return progress;
    }

    @Override
    public float getProgressScaled(int progress) {
        return progress/240f;
    }
}
