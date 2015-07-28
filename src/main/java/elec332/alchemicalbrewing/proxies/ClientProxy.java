package elec332.alchemicalbrewing.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Elec332 on 24-2-2015.
 */
public class ClientProxy extends CommonProxy{

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return super.getClientGuiElement(ID, player, world, x, y, z);
    }
}
