package elec332.alchemicalbrewing.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class BlockChemicalTank extends BlockABBase {
    public BlockChemicalTank(Material mat, String blockName) {
        super(mat, null, blockName);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return super.createNewTileEntity(world, metadata);
    }
}
