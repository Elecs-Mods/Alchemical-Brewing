package elec332.alchemicalbrewing.util;

import elec332.alchemicalbrewing.AlchemicalBrewing;
import elec332.core.java.ReflectionHelper;
import elec332.core.main.ElecCore;
import net.minecraft.potion.Potion;

import java.lang.reflect.Field;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class PotionArrayExpansion {

    public static void transform(){
        if (init)
            return;
        init = true;
        String fieldName = ElecCore.developmentEnvironment ? "potionTypes" : "field_76425_a";
        Field potion = null;
        try {
            potion = ReflectionHelper.makeFinalFieldModifiable(Potion.class.getField(fieldName));
        } catch (Exception e){
            AlchemicalBrewing.logger.error("Error accessing potion field, unable to extend potion ID's");
        }
        try {
            if (potion != null) {
                Potion[] toReplace = new Potion[Config.maxPotionID+1];
                System.arraycopy(Potion.potionTypes, 0, toReplace, 0, Potion.potionTypes.length);
                potion.set(null, toReplace);
            }
        } catch (IllegalAccessException e){
            AlchemicalBrewing.logger.error("Error transforming potion field, unable to extend potion ID's");
        }
        AlchemicalBrewing.logger.info("Successfully extended potion ID range to: " + Potion.potionTypes.length);
    }

    private static boolean init = false;

}
