package elec332.alchemicalbrewing.util;

import elec332.core.config.Configurable;

/**
 * Created by Elec332 on 29-7-2015.
 */
public class Config {

    @Configurable(minValue = 32, maxValue = Short.MAX_VALUE)
    public static int maxPotionID = 2048;

}
