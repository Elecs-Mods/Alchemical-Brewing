package elec332.alchemicalbrewing.client;

import elec332.alchemicalbrewing.AlchemicalBrewing;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Elec332 on 30-7-2015.
 */
public class ABResourceLocation extends ResourceLocation {
    public ABResourceLocation(String path) {
        super(AlchemicalBrewing.ModID.toLowerCase(), path);
    }
}
