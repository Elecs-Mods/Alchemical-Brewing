package elec332.alchemicalbrewing.multiblock;

import elec332.alchemicalbrewing.client.gui.BasicGui;
import elec332.alchemicalbrewing.container.MundaneTankContainer;
import elec332.alchemicalbrewing.registry.PotionRegistry;
import elec332.core.baseclasses.tileentity.TileBase;
import elec332.core.util.BasicInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.*;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class MundaneTank extends ABMultiBlockBase{

    public MundaneTank() {
        super();
        setInternalTank(new FluidTank(10*FluidContainerRegistry.BUCKET_VOLUME){
            @Override
            public int fill(FluidStack resource, boolean doFill) {
                if (resource == null || resource.getFluid() != FluidRegistry.WATER) {
                    return 0;
                }
                if (!doFill) {
                    if (fluid == null) {
                        return Math.min(capacity, resource.amount);
                    }
                    return Math.min(capacity - fluid.amount, resource.amount);
                }
                if (fluid == null) {
                    fluid = new FluidStack(PotionRegistry.awkwardFluid, Math.min(capacity, resource.amount), new NBTTagCompound());
                    return Math.min(capacity, resource.amount);
                }
                int filled = capacity - fluid.amount;
                if (resource.amount < filled) {
                    fluid.amount += resource.amount;
                    filled = resource.amount;
                } else {
                    fluid.amount = capacity;
                }
                if (!getWorldObj().isRemote) {
                    final float f = getFluid().tag.getFloat("purity");
                    getFluid().tag.setFloat("purity", f*(1f-((float)filled/fluid.amount)));
                    markDirty();
                }
                return filled;
            }
        });
    }

    /**
     * Initialise your multiblock here, all fields provided by @link IMultiblock have already been given a value
     */
    @Override
    public void init() {
        inventory = new BasicInventory("inventory", 1, getSaveDelegate());
        ((TileBase)getSaveDelegate()).syncData();
    }

    private BasicInventory inventory;
    private int progress;


    public BasicInventory getInventory(){
        return this.inventory;
    }

    public boolean canDrain(){
        return getPurity() == 100f;
    }

    public final float getPurity(){
        if (getInternalTank().getFluid() != null)
            return getInternalTank().getFluid().tag.getFloat("purity");
        return 0;
    }

    public int fill(FluidStack resource, boolean doFill){
        /*if (resource.getFluid() != FluidRegistry.WATER)
            return 0;
        FluidStack toAdd = new FluidStack(PotionRegistry.awkwardFluid, resource.amount);
        if (getInternalTank().getFluid() == null)
            toAdd.tag = new NBTTagCompound();*/
        markDirty();
        return getInternalTank().fill(resource, doFill);
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
        if (progress == 0 && inventory.getStackInSlot(0) != null && inventory.getStackInSlot(0).stackSize > 0 && getPurity() != 100){
            this.progress = 100;
            inventory.decrStackSize(0, 1);
        }
        if (progress > 0 && getInternalTank().getFluid() != null){
            progress--;
            float f = getPurity();
            if (f < 100){
                float f1 = 1000f/getInternalTank().getFluid().amount;
                f +=f1;
                if (f > 100)
                    f = 100;
                getInternalTank().getFluid().tag.setFloat("purity", f);
                ((TileBase)getSaveDelegate()).syncData();
            }
        }
    }

    /**
     * Invalidate your multiblock here
     */
    @Override
    public void invalidate() {

    }
}
