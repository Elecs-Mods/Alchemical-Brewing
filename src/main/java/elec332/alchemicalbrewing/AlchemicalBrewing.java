package elec332.alchemicalbrewing;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import elec332.alchemicalbrewing.init.BlockRegister;
import elec332.alchemicalbrewing.init.CommandRegister;
import elec332.alchemicalbrewing.init.ItemRegister;
import elec332.alchemicalbrewing.reflection.Reflection;
import elec332.core.config.ConfigWrapper;
import elec332.core.helper.FileHelper;
import elec332.core.helper.MCModInfo;
import elec332.core.modBaseUtils.ModInfo;
import elec332.alchemicalbrewing.proxies.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;

/**
 * Created by Elec332 on 24-2-2015.
 */
@Mod(modid = AlchemicalBrewing.ModID, name = AlchemicalBrewing.ModName, dependencies = ModInfo.DEPENDENCIES+"@[#ELECCORE_VER#,)",
        acceptedMinecraftVersions = ModInfo.ACCEPTEDMCVERSIONS, useMetadata = true, canBeDeactivated = true)
public class AlchemicalBrewing {

    public static final String ModName = "Alchemical Brewing";
    public static final String ModID = "alchemicalbrewing";

    @SidedProxy(clientSide = "elec332.alchemicalbrewing.proxies.ClientProxy", serverSide = "elec332.alchemicalbrewing.proxies.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(ModID)
    public static AlchemicalBrewing instance;
    public static Configuration config;
    public static ConfigWrapper configWrapper;
    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(FileHelper.getConfigFileElec(event));
        configWrapper = new ConfigWrapper(config);
        config.load();
        logger = event.getModLog();
        Reflection.transform();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
        ItemRegister.instance.preInit(event);
        BlockRegister.instance.preInit(event);
        //setting up mod stuff


        MCModInfo.CreateMCModInfo(event, "Created by Elec332",
                "A complete brewing overhaul!",
                "website link", "logo",
                new String[]{"Elec332"});
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        config.load();
        ItemRegister.instance.init(event);
        BlockRegister.instance.init(event);
        //register items/blocks

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        //NOPE
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        CommandRegister.instance.init(event);
    }

}
