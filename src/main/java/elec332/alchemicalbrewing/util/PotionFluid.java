package elec332.alchemicalbrewing.util;

import elec332.alchemicalbrewing.registry.WrappedPotion;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by Elec332 on 16-8-2015.
 */
public class PotionFluid extends Fluid {

    public PotionFluid(WrappedPotion potion) {
        super(potion.getPotion().getName()+potion.getStrength()+potion.isSplash());
        this.potion = potion;
    }

    private final WrappedPotion potion;

    public WrappedPotion getPotion() {
        return this.potion;
    }

    public boolean isExplosive(){
        return this.potion.isSplash();
    }
    @Override
    public int getColor() {
        return potion.getPotion().getLiquidColor();
    }
}
