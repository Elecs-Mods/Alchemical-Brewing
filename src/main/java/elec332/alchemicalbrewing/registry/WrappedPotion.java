package elec332.alchemicalbrewing.registry;

import net.minecraft.potion.Potion;

/**
 * Created by Elec332 on 9-8-2015.
 */
public final class WrappedPotion {

    public WrappedPotion(Potion potion, int strength){
        this.potion = potion;
        this.strength = strength;
    }

    private final Potion potion;
    private final int strength;

    public Potion getPotion() {
        return potion;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public int hashCode() {
        return strength;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof WrappedPotion && ((WrappedPotion) obj).potion == potion && obj.hashCode() == hashCode();
    }
}
