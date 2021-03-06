package elec332.alchemicalbrewing.multiblock;

import elec332.alchemicalbrewing.client.gui.BasicGui;
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
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidTank;

/**
 * Created by Elec332 on 9-8-2015.
 */
public class Brewery extends ABMultiBlockBase implements ITileWithSlots, IHasProgressBar {

    public Brewery(){
        setInternalTank(new FluidTank(10* FluidContainerRegistry.BUCKET_VOLUME));
        this.potionTank = new FluidTank(10*FluidContainerRegistry.BUCKET_VOLUME);
    }

    private BasicInventory inventory;
    private int progress;
    private FluidTank potionTank;

    /**
     * Initialise your multiblock here, all fields provided by @link IMultiblock have already been given a value
     */
    @Override
    public void init() {
        this.inventory = new BasicInventory("name", 1, getSaveDelegate());
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        NBTTagCompound tank = new NBTTagCompound();
        potionTank.writeToNBT(tank);
        tagCompound.setTag("tank", tank);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        potionTank.readFromNBT(tagCompound.getCompoundTag("tank"));
    }

    /**
     * This gets run server-side only
     */
    @Override
    public void onTick() {
        if (progress < 330 && canBrew()){
            progress++;
        } else {
            if (canBrew())
                brewPotion();
            progress = 0;
        }
    }

    private boolean canBrew(){
        return PotionRegistry.instance.processRecipe(false, getInternalTank(), inventory, potionTank);
    }

    private void brewPotion(){
        /*inventory.decrStackSize(0, 1);
        getInternalTank().drain(1000, true);
        if (inventory.getStackInSlot(1) == null) {
            inventory.setInventorySlotContents(1, PotionRegistry.instance.potionToStack.get(new WrappedPotion(Potion.heal, 1)));
        } else {
            inventory.getStackInSlot(1).stackSize++;
        }*/
        PotionRegistry.instance.processRecipe(true, getInternalTank(), inventory, potionTank);
        markDirty();
    }

    public BasicInventory getInventory(){
        return inventory;
    }

    public FluidTank getPotionTank(){
        return this.potionTank;
    }

    @Override
    public Object getGui(EntityPlayer player, boolean client) {
        BaseContainer container = new ContainerMachine(this, player, 0);
        if (client)
            return new BasicGui(container, "brewery.png");
        return container;
    }

    @Override
    public boolean onAnyBlockActivated(EntityPlayer player) {
        return openGui(player);
    }

    /**
     * Invalidate your multiblock here
     */
    @Override
    public void invalidate() {

    }

    @Override
    public void addSlots(BaseContainer baseContainer) {
        baseContainer.addSlotToContainer(new Slot(getInventory(), 0, 80, 17));
        //baseContainer.addSlotToContainer(new Slot(getInventory(), 1, 26, 33));
        baseContainer.addPlayerInventoryToContainer();
        baseContainer.addWidget(new FluidTankWidget(130, 19, 176, 0, 32, 44, potionTank));
        baseContainer.addWidget(new FluidTankWidget(14, 19, 176, 0, 32, 44, getInternalTank()));
        baseContainer.addWidget(new WidgetProgressArrow(76, 42, this, true));
    }

    @Override
    public int getProgress() {
        return progress;
    }

    @Override
    public float getProgressScaled(int progress) {
        return progress/330f;
    }
}
