package elec332.alchemicalbrewing.multiblock;

import elec332.alchemicalbrewing.client.gui.BasicGui;
import elec332.alchemicalbrewing.container.BreweryContainer;
import elec332.alchemicalbrewing.registry.PotionRegistry;
import elec332.alchemicalbrewing.registry.WrappedPotion;
import elec332.core.inventory.BaseContainer;
import elec332.core.util.BasicInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidTank;

/**
 * Created by Elec332 on 9-8-2015.
 */
public class Brewery extends ABMultiBlockBase {

    public Brewery(){
        setInternalTank(new FluidTank(10* FluidContainerRegistry.BUCKET_VOLUME));
    }

    private BasicInventory inventory;
    private int progress;

    /**
     * Initialise your multiblock here, all fields provided by @link IMultiblock have already been given a value
     */
    @Override
    public void init() {
        this.inventory = new BasicInventory("name", 2, getSaveDelegate());
    }

    /**
     * This gets run server-side only
     */
    @Override
    public void onTick() {
        if (progress > 0 && canBrew()){
            progress--;
        } else {
            if (canBrew())
                brewPotion();
            progress = 330;
        }
    }

    private boolean canBrew(){
        return inventory.getStackInSlot(0) != null && inventory.getStackInSlot(0).getItem() == Items.speckled_melon && getInternalTank().getFluid() != null && getInternalTank().getFluid().amount >= 1000;
    }

    private void brewPotion(){
        inventory.decrStackSize(0, 1);
        getInternalTank().drain(1000, true);
        if (inventory.getStackInSlot(1) == null) {
            inventory.setInventorySlotContents(1, PotionRegistry.instance.potionToStack.get(new WrappedPotion(Potion.heal, 1)));
        } else {
            inventory.getStackInSlot(1).stackSize++;
        }
        markDirty();
    }

    public BasicInventory getInventory(){
        return inventory;
    }

    @Override
    public Object getGui(EntityPlayer player, boolean client) {
        BaseContainer container = new BreweryContainer(player, this);
        if (client)
            return new BasicGui(container, "gui_mundane_tank.png");
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
}
