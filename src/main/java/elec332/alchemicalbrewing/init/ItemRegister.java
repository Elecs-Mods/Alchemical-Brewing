package elec332.alchemicalbrewing.init;

import cpw.mods.fml.common.registry.GameRegistry;
import elec332.alchemicalbrewing.AlchemicalBrewing;
import elec332.alchemicalbrewing.tile.TileMundaneTankPart;
import elec332.core.player.PlayerHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Elec332 on 24-2-2015.
 */
public class ItemRegister {
    public static final ItemRegister instance = new ItemRegister();

    public void init(){
        GameRegistry.registerItem(new TestItem().setCreativeTab(AlchemicalBrewing.creativeTab), "TestNull");
        GameRegistry.registerItem(new TestActivatorItem().setCreativeTab(AlchemicalBrewing.creativeTab), "TestActivate");
    }

    private static class TestItem extends Item{
        @Override
        public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
            try {
                if (world.getTileEntity(x, y, z) instanceof TileMundaneTankPart && world.isRemote) {
                    TileMundaneTankPart tank = (TileMundaneTankPart) world.getTileEntity(x, y, z);
                    PlayerHelper.sendMessageToPlayer(player, "Valid multiblock: "+tank.isValidMultiBlock());
                    PlayerHelper.sendMessageToPlayer(player, "Has multiblock: "+(tank.getMultiBlock() != null));
                    PlayerHelper.sendMessageToPlayer(player, "Fluid: " + tank.getMultiBlock().getInternalTank().getFluid().getFluid().getName() + "  amount: " + tank.getMultiBlock().getInternalTank().getFluid().amount);
                }
            } catch (Exception e){
                //PlayerHelper.sendMessageToPlayer(player, "Wrong item");
                //e.printStackTrace();
            }
            return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
        }
    }

    private static class TestActivatorItem extends Item{
        @Override
        public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
            if (!world.isRemote) {
                return AlchemicalBrewing.multiBlockRegistry.getStructureRegistry().attemptCreate(world, x, y, z, ForgeDirection.getOrientation(side));
            }
            return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
        }
    }
}
