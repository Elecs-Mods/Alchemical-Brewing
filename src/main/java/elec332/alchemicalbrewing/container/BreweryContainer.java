package elec332.alchemicalbrewing.container;

import elec332.alchemicalbrewing.multiblock.Brewery;
import elec332.core.inventory.BaseContainer;
import elec332.core.inventory.widget.FluidTankWidget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

/**
 * Created by Elec332 on 9-8-2015.
 */
public class BreweryContainer extends BaseContainer {

    public BreweryContainer(EntityPlayer player, Brewery brewery) {
        super(player);
        this.addSlotToContainer(new Slot(brewery.getInventory(), 0, 66, 53));
        this.addSlotToContainer(new Slot(brewery.getInventory(), 1, 26, 33));
        addPlayerInventoryToContainer();
        addWidget(new FluidTankWidget(130, 19, 176, 0, 32, 44, brewery.getInternalTank()));
    }

}
