package elec332.alchemicalbrewing.registry;

import net.minecraft.potion.Potion;

/**
 * Created by Elec332 on 9-8-2015.
 */
public final class WrappedPotion {

    public WrappedPotion(Potion potion, int strength, boolean splash){
        this.potion = potion;
        this.strength = strength;
        this.splash = splash;
    }

    private final Potion potion;
    private final int strength;
    private final boolean splash;

    public Potion getPotion() {
        return potion;
    }

    public int getStrength() {
        return strength;
    }

    public boolean isSplash() {
        return splash;
    }

    @Override
    public int hashCode() {
        return strength;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof WrappedPotion && ((WrappedPotion) obj).potion == potion && ((WrappedPotion) obj).splash == splash && obj.hashCode() == hashCode();
    }
}
