package elec332.alchemicalbrewing.container;

import elec332.alchemicalbrewing.multiblock.MundaneTank;
import elec332.core.inventory.BaseContainer;
import elec332.core.inventory.widget.FluidTankWidget;
import elec332.core.util.BasicInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;

/**
 * Created by Elec332 on 30-7-2015.
 */
public class MundaneTankContainer extends BaseContainer {

    public MundaneTankContainer(EntityPlayer player, MundaneTank mundaneTank) {
        super(player);
        this.addSlotToContainer(new Slot(mundaneTank == null ? new BasicInventory("", 1) : mundaneTank.getInventory(), 0, 66, 53));
        addPlayerInventoryToContainer();
        addWidget(new FluidTankWidget(130, 19, 176, 0, 32, 44, mundaneTank == null?null:mundaneTank.getInternalTank()));
    }
}
