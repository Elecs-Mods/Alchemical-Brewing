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
        this.name = null;
    }

    public WrappedPotion(String potion, int strength, boolean splash){
        if (com.google.common.base.Strings.isNullOrEmpty(potion))
            throw new IllegalArgumentException();
        this.potion = PotionRegistry.getPotionByName(potion);
        this.strength = strength;
        this.splash = splash;
        this.name = potion;
    }

    private final String name;
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

    public String getName(){
        return com.google.common.base.Strings.isNullOrEmpty(name) ? potion.getName() : name;
    }

    public final boolean isAwkwardPotion(){
        return name != null && name.equalsIgnoreCase("awkwardPotion");
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
