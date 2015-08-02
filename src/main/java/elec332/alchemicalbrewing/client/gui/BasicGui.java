package elec332.alchemicalbrewing.client.gui;

import elec332.alchemicalbrewing.client.ABResourceLocation;
import elec332.core.client.inventory.BaseGuiContainer;
import elec332.core.inventory.BaseContainer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Elec332 on 30-7-2015.
 */
public class BasicGui extends BaseGuiContainer {
    public BasicGui(BaseContainer container, String location) {
        super(container);
        this.location = location;
    }

    private final String location;

    @Override
    public ResourceLocation getBackgroundImageLocation() {
        return new ABResourceLocation(location);
    }
}
