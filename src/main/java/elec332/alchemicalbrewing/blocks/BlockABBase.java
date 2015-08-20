package elec332.alchemicalbrewing.blocks;

import elec332.alchemicalbrewing.AlchemicalBrewing;
import elec332.core.baseclasses.tileentity.BlockTileBase;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class BlockABBase extends BlockTileBase {

    public BlockABBase(Material mat, Class<? extends TileEntity> tileClass, String blockName) {
        super(mat, tileClass, blockName, AlchemicalBrewing.ModID);
        setCreativeTab(AlchemicalBrewing.creativeTab);
    }

}
