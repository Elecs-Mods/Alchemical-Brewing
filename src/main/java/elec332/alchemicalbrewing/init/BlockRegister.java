package elec332.alchemicalbrewing.init;

import elec332.alchemicalbrewing.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Elec332 on 24-2-2015.
 */
public class BlockRegister {
    public static final BlockRegister instance = new BlockRegister();

    public static Block mundaneTankBlock, tankTap, breweryBlock, cannery;

    public void init(){
        mundaneTankBlock = new BlockChemicalTank(Material.wood, "mundanetank").registerTile().register();
        tankTap = new BlockTankTap("tankTap").registerTile().register();
        breweryBlock = new BlockBrewery("brewery").registerTile().register();
        cannery = new BlockCannery("bottler").registerTile().register();
    }
}
