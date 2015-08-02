package elec332.alchemicalbrewing.multiblock;

import elec332.alchemicalbrewing.client.gui.BasicGui;
import elec332.alchemicalbrewing.container.MundaneTankContainer;
import elec332.alchemicalbrewing.registry.PotionRegistry;
import elec332.core.baseclasses.tileentity.TileBase;
import elec332.core.util.BasicInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class MundaneTank extends ABMultiBlockBase {

    public MundaneTank() {
        super(new FluidTank(10*FluidContainerRegistry.BUCKET_VOLUME));
    }

    /**
     * Initialise your multiblock here, all fields provided by @link IMultiblock have already been given a value
     */
    @Override
    public void init() {
        inventory = new BasicInventory("inventory", 1, getSaveDelegate());
        ((TileBase)getSaveDelegate()).syncData();
    }

    BasicInventory inventory;

    public BasicInventory getInventory(){
        return this.inventory;
    }

    public boolean canDrain(){
        return getInternalTank().getFluid().tag.getByte("purity") == 100;
    }

    public int fill(FluidStack resource, boolean doFill){
        if (resource.getFluid() != FluidRegistry.WATER)
            return 0;
        FluidStack toAdd = new FluidStack(PotionRegistry.awkwardFluid, resource.amount);
        markDirty();
        return getInternalTank().fill(toAdd, doFill);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        inventory.readFromNBT(tagCompound);
    }

    @Override
    public Object getGui(EntityPlayer player, boolean client) {
        MundaneTankContainer container = new MundaneTankContainer(player, this);
        if (client)
            return new BasicGui(container, "gui_mundane_tank.png");
        return container;
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        inventory.writeToNBT(tagCompound);
    }

    @Override
    public boolean onAnyBlockActivated(EntityPlayer player) {
        openGui(player);
        return false;
    }

    /**
     * This gets run server-side only
     */
    @Override
    public void onTick() {

    }

    /**
     * Invalidate your multiblock here
     */
    @Override
    public void invalidate() {

    }
}
