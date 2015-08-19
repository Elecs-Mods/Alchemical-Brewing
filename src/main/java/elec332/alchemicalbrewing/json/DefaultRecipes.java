package elec332.alchemicalbrewing.json;

import elec332.alchemicalbrewing.registry.WrappedPotion;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import static net.minecraft.potion.Potion.*;

/**
 * Created by Elec332 on 19-8-2015.
 */
public class DefaultRecipes {

    public static final JsonPotionRecipe[] recipes = new JsonPotionRecipe[]{
            new JsonPotionRecipe(new ItemStack(Items.speckled_melon), new WrappedPotion("awkwardPotion", -1, false), new WrappedPotion(heal, 1, false)),
            new JsonPotionRecipe(new ItemStack(Items.glowstone_dust), new WrappedPotion(heal, 1, false), new WrappedPotion(heal, 2, false))
    };

}
