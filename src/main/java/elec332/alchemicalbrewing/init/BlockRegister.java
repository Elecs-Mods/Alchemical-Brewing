package elec332.alchemicalbrewing.init;

import elec332.alchemicalbrewing.blocks.BlockABBase;
import elec332.alchemicalbrewing.blocks.BlockChemicalTank;
import elec332.alchemicalbrewing.tile.TileBottler;
import elec332.alchemicalbrewing.tile.TileBrewery;
import elec332.alchemicalbrewing.tile.TileTankTap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Elec332 on 24-2-2015.
 */
public class BlockRegister {
    public static final BlockRegister instance = new BlockRegister();

    public static Block woodenTankBlock, mundaneTankBlock, tankTap, breweryBlock, cannery;

    public void init(){
        woodenTankBlock = new BlockABBase(Material.wood, null, "woodentank").register();
        mundaneTankBlock = new BlockChemicalTank(Material.wood, "mundanetank").registerTile().register();
        tankTap = new BlockABBase(Material.wood, TileTankTap.class, "tankTap").registerTile().register();
        breweryBlock = new BlockABBase(Material.wood, TileBrewery.class, "brewery").registerTile().register();
        cannery = new BlockABBase(Material.wood, TileBottler.class, "bottler").registerTile().register();
    }
}
