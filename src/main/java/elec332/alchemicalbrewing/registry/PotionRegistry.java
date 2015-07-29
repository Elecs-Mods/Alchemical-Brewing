package elec332.alchemicalbrewing.registry;

import net.minecraft.potion.Potion;
import net.minecraftforge.fluids.Fluid;

import java.util.Map;

/**
 * Created by Elec332 on 29-7-2015.
 */
public final class PotionRegistry {

    public static final PotionRegistry instance = new PotionRegistry();
    private PotionRegistry(){

    }

    private Map<Potion, Fluid> registry;


}
