package elec332.alchemicalbrewing.proxies;

import cpw.mods.fml.common.network.IGuiHandler;
import elec332.core.baseclasses.tileentity.IInventoryTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Elec332 on 24-2-2015.
 */
public class CommonProxy implements IGuiHandler{

    public void fixVanillaFluidIcons(){
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof IInventoryTile){
            return ((IInventoryTile) tile).getGuiServer(player);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }
}
