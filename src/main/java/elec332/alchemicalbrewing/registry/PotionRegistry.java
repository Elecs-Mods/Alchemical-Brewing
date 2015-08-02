package elec332.alchemicalbrewing.registry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.potion.Potion;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.Map;

/**
 * Created by Elec332 on 29-7-2015.
 */
public final class PotionRegistry {

    public static final PotionRegistry instance = new PotionRegistry();
    private PotionRegistry(){
    }

    private Map<Potion, Fluid> registry;


    public static final Fluid awkwardFluid = new Fluid("awkwardFluid");

    public void preInitFluids(){
        FluidRegistry.registerFluid(awkwardFluid);
    }

    @SideOnly(Side.CLIENT)
    public void registerTextures(IIconRegister iIconRegister){
        awkwardFluid.setStillIcon(FluidRegistry.WATER.getStillIcon());
    }

}
