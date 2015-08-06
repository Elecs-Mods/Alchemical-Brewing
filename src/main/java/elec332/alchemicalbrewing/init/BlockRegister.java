package elec332.alchemicalbrewing.init;

import cpw.mods.fml.common.registry.GameRegistry;
import elec332.alchemicalbrewing.blocks.BlockABBase;
import elec332.alchemicalbrewing.blocks.BlockChemicalTank;
import elec332.alchemicalbrewing.tile.TileMundaneTankPart;
import elec332.alchemicalbrewing.tile.TileTankTap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Elec332 on 24-2-2015.
 */
public class BlockRegister {
    public static final BlockRegister instance = new BlockRegister();

    public static Block woodenTankBlock, mundaneTankBlock, tankTap;

    public void init(){
        woodenTankBlock = new BlockABBase(Material.wood, null, "woodentank").register();
        mundaneTankBlock = new BlockChemicalTank(Material.wood, "mundanetank").register();
        GameRegistry.registerTileEntity(TileMundaneTankPart.class, "mundanetank");
        tankTap = new BlockABBase(Material.wood, TileTankTap.class, "tankTap").register();
        GameRegistry.registerTileEntity(TileTankTap.class, "tankTap");
    }
}
