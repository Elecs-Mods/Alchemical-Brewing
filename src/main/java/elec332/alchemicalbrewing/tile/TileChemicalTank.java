package elec332.alchemicalbrewing.tile;

import elec332.alchemicalbrewing.client.ABResourceLocation;
import elec332.alchemicalbrewing.client.gui.BasicGui;
import elec332.alchemicalbrewing.container.MundaneTankContainer;
import elec332.alchemicalbrewing.multiblock.ABMultiBlockTileBase;
import elec332.alchemicalbrewing.multiblock.MundaneTank;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;
import org.lwjgl.opengl.GL11;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class TileChemicalTank extends ABMultiBlockTileBase implements IFluidHandler{

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (getMultiBlockTank() == null || from != ForgeDirection.UP || resource.getFluid() != FluidRegistry.WATER)
            return 0;
        markDirty();
        return getMultiBlockTank().fill(resource, doFill);
    }

    /*@Override
    public Object getGuiClient(EntityPlayer player) {
        return isValidMultiBlock()?new GuiContainer(new MundaneTankContainer(player, null)) {
            @Override
            protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
                ResourceLocation rl = new ABResourceLocation("gui_mundane_tank.png");
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.mc.getTextureManager().bindTexture(rl);
                int k = (this.width - this.xSize) / 2;
                int l = (this.height - this.ySize) / 2;
                this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
            }
        }:null;
    }*/

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        /*if (getMultiBlockTank() == null)
            return null;
        FluidTank tank = getMultiBlockTank().getInternalTank();
        if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
            return null;
        }
        return tank.drain(resource.amount, doDrain);*/
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        /*if (getMultiBlockTank() == null)
            return null;
        return getMultiBlockTank().getInternalTank().drain(maxDrain, doDrain);*/
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return getMultiBlockTank() != null;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return false;//getMultiBlockTank() != null && getMultiBlockTank().canDrain();
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        if (getMultiBlockTank() == null)
            return new FluidTankInfo[0];
        return new FluidTankInfo[]{
                getMultiBlockTank().getInternalTank().getInfo()
        };
    }

    private MundaneTank getMultiBlockTank(){
        return getMultiBlock() instanceof MundaneTank ? (MundaneTank) getMultiBlock() : null;
    }
}
