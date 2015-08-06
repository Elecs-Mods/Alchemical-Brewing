package elec332.alchemicalbrewing.tile;

import elec332.alchemicalbrewing.multiblock.ABMultiBlockTileBase;
import elec332.alchemicalbrewing.registry.PotionRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
/**
 * Created by Elec332 on 2-8-2015.
 */
public class TileTankTap extends ABMultiBlockTileBase {

    @Override
    public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getCurrentEquippedItem();
        if (stack.getItem() == Items.glass_bottle && getMultiBlock() != null) {
            ItemStack stack1 = PotionRegistry.instance.fillBottle(getMultiBlock().getInternalTank());
            if (stack1 != null) {
                if (stack.stackSize > 1) {
                    if (!player.inventory.addItemStackToInventory(stack1)) {
                        player.dropPlayerItemWithRandomChoice(stack1, false);
                    }
                    stack.splitStack(1);
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, stack);
                } else {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, stack1);
                }
                return true;
            }
        }
        return super.onBlockActivated(player, side, hitX, hitY, hitZ);
    }
}
