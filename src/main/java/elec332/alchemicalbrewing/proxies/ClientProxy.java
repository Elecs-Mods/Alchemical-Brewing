package elec332.alchemicalbrewing.proxies;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import elec332.alchemicalbrewing.AlchemicalBrewing;
import elec332.alchemicalbrewing.registry.PotionRegistry;
import elec332.core.baseclasses.tileentity.IInventoryTile;
import elec332.core.util.EventHelper;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by Elec332 on 24-2-2015.
 */
public class ClientProxy extends CommonProxy{

    public ClientProxy(){
        EventHelper.registerHandlerForge(this);
    }

    private int i = 0;

    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent.Post event){
        if (event.map.getTextureType() == 0){
            i++;
            if (i == 2) {
                AlchemicalBrewing.proxy.fixVanillaFluidIcons();
                PotionRegistry.instance.registerTextures(event.map);
            }
        }
    }

    @Override
    public void fixVanillaFluidIcons() {
        FluidRegistry.WATER.setFlowingIcon(BlockLiquid.getLiquidIcon("water_flow"));
        FluidRegistry.WATER.setStillIcon(BlockLiquid.getLiquidIcon("water_still"));
        FluidRegistry.LAVA.setFlowingIcon(BlockLiquid.getLiquidIcon("lava_flow"));
        FluidRegistry.LAVA.setStillIcon(BlockLiquid.getLiquidIcon("lava_still"));
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof IInventoryTile){
            return ((IInventoryTile) tile).getGuiClient(player);
        }
        return super.getClientGuiElement(ID, player, world, x, y, z);
    }
}
