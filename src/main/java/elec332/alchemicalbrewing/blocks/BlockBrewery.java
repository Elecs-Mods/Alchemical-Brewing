package elec332.alchemicalbrewing.blocks;

import elec332.alchemicalbrewing.tile.TileBrewery;
import net.minecraft.block.material.Material;

/**
 * Created by Elec332 on 20-8-2015.
 */
public class BlockBrewery extends BlockABBase {

    public BlockBrewery(String blockName) {
        super(Material.wood, TileBrewery.class, blockName);
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
