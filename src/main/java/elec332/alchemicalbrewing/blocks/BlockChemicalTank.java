package elec332.alchemicalbrewing.blocks;

import elec332.alchemicalbrewing.tile.TileMundaneTankPart;
import elec332.core.baseclasses.tileentity.BlockTileBase;
import net.minecraft.block.material.Material;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class BlockChemicalTank extends BlockABBase {

    public BlockChemicalTank(Material mat, String blockName) {
        super(mat, TileMundaneTankPart.class, blockName);
    }

    @Override
    public String getFrontTexture(boolean active) {
        return "alchemicalbrewing:tank_side";
    }

    @Override
    public String getSideTexture(boolean active, BlockSide side) {
        return "alchemicalbrewing:tank_side";
    }

    @Override
    public String getTopIconName(boolean active) {
        return "planks_oak";
    }

    @Override
    public String getBottomIconName(boolean active) {
        return "planks_oak";
    }

}
