package elec332.alchemicalbrewing.blocks;

import elec332.alchemicalbrewing.tile.TileChemicalTank;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class BlockChemicalTank extends BlockABBase {
    public BlockChemicalTank(Material mat, String blockName) {
        super(mat, TileChemicalTank.class, blockName);
    }

}
