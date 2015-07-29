package elec332.alchemicalbrewing.init;

import cpw.mods.fml.common.registry.GameRegistry;
import elec332.alchemicalbrewing.blocks.BlockABBase;
import elec332.alchemicalbrewing.blocks.BlockChemicalTank;
import elec332.alchemicalbrewing.tile.TileChemicalTank;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Elec332 on 24-2-2015.
 */
public class BlockRegister {
    public static final BlockRegister instance = new BlockRegister();

    public static Block woodenTankBlock, mundaneTankBlock;

    public void init(){
        woodenTankBlock = new BlockABBase(Material.wood, null, "woodentank").register();
        mundaneTankBlock = new BlockChemicalTank(Material.wood, "mundanetank").register();
        GameRegistry.registerTileEntity(TileChemicalTank.class, "mundanetank");
    }
}
