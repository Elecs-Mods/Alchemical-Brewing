package elec332.alchemicalbrewing.container;

import com.google.common.collect.Lists;
import elec332.alchemicalbrewing.multiblock.MundaneTank;
import elec332.core.inventory.BaseContainer;
import elec332.core.inventory.tooltip.ToolTip;
import elec332.core.inventory.widget.FluidTankWidget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

/**
 * Created by Elec332 on 30-7-2015.
 */
public class MundaneTankContainer extends BaseContainer {

    public MundaneTankContainer(EntityPlayer player, final MundaneTank mundaneTank) {
        super(player);
        this.addSlotToContainer(new Slot(mundaneTank.getInventory(), 0, 66, 53){
            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem() == Items.nether_wart;
            }
        });
        addPlayerInventoryToContainer();
        addWidget(new FluidTankWidget(130, 19, 176, 0, 32, 44, mundaneTank.getInternalTank()){
            @Override
            public ToolTip getToolTip() {
                return new ToolTip(Lists.newArrayList(
                        new ToolTip.ColouredString(EnumChatFormatting.YELLOW, "Purity: "+mundaneTank.getPurity())
                ));
            }
        });
    }
}
