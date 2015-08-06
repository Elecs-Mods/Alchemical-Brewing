package elec332.alchemicalbrewing.blocks;

import elec332.alchemicalbrewing.tile.TileMundaneTankPart;
import net.minecraft.block.material.Material;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class BlockChemicalTank extends BlockABBase {
    public BlockChemicalTank(Material mat, String blockName) {
        super(mat, TileMundaneTankPart.class, blockName);
    }

}
