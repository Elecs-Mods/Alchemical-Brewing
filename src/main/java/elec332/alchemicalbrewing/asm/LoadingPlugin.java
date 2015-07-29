package elec332.alchemicalbrewing.asm;

import elec332.alchemicalbrewing.AlchemicalBrewing;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;

import java.util.Map;

/**
 * Created by Brandon on 27/5/2015.
 */
@IFMLLoadingPlugin.Name(value = AlchemicalBrewing.ModID)
@IFMLLoadingPlugin.MCVersion(value = "1.7.10")
@IFMLLoadingPlugin.TransformerExclusions(value = "elec332.alchemicalbrewing.asm.")
@IFMLLoadingPlugin.SortingIndex(value = 1001)
public class LoadingPlugin implements IFMLLoadingPlugin {

	public LoadingPlugin(){
		AlchemicalBrewing.logger = LogManager.getLogger(AlchemicalBrewing.ModName);
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[] {ClassTransformer.class.getName()};
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
