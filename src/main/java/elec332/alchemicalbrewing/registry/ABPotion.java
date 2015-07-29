package elec332.alchemicalbrewing.registry;

import net.minecraft.potion.Potion;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class ABPotion extends Potion {
    public ABPotion(int p_i1573_1_, boolean p_i1573_2_, int p_i1573_3_) {
        super(p_i1573_1_, p_i1573_2_, p_i1573_3_);
    }

    @Override
    public Potion setIconIndex(int p_76399_1_, int p_76399_2_) {
        return super.setIconIndex(p_76399_1_, p_76399_2_);
    }

    @Override
    public ABPotion setPotionName(String p_76390_1_) {
        super.setPotionName(p_76390_1_);
        return this;
    }
}
