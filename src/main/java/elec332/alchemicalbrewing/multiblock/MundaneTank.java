package elec332.alchemicalbrewing.multiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidTank;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class MundaneTank extends ABMultiBlockBase {

    public MundaneTank() {
        super(new FluidTank(10*FluidContainerRegistry.BUCKET_VOLUME));
        System.out.println("MultiBlock created");
    }

    public boolean canDrain(){
        return getInternalTank().getFluid().tag.getByte("purity") == 100;
    }

    @Override
    public boolean onAnyBlockActivated(EntityPlayer player) {
        return false;
    }

    /**
     * Initialise your multiblock here, all fields provided by @link IMultiblock have already been given a value
     */
    @Override
    public void init() {
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
