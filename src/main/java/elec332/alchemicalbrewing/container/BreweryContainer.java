package elec332.alchemicalbrewing.container;

import elec332.alchemicalbrewing.multiblock.Brewery;
import elec332.core.inventory.BaseContainer;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Elec332 on 9-8-2015.
 */
public class BreweryContainer extends BaseContainer {

    public BreweryContainer(EntityPlayer player, Brewery brewery) {
        super(player);

    }

}
