package elec332.alchemicalbrewing.tile;

import elec332.core.multiblock.AbstractMultiBlockTile;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class TileChemicalTank extends AbstractMultiBlockTile {

    public TileChemicalTank(){
        super();
        this.internalTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*10);
    }

    private FluidTank internalTank;

}
