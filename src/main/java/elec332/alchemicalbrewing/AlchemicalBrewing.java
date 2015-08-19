package elec332.alchemicalbrewing;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import elec332.alchemicalbrewing.init.BlockRegister;
import elec332.alchemicalbrewing.init.CommandRegister;
import elec332.alchemicalbrewing.init.ItemRegister;
import elec332.alchemicalbrewing.multiblock.MultiBlocks;
import elec332.alchemicalbrewing.util.SortingHandler;
import elec332.alchemicalbrewing.proxies.CommonProxy;
import elec332.alchemicalbrewing.registry.PotionRegistry;
import elec332.alchemicalbrewing.util.Config;
import elec332.core.config.ConfigWrapper;
import elec332.core.helper.FileHelper;
import elec332.core.helper.MCModInfo;
import elec332.core.modBaseUtils.ModInfo;
import elec332.core.multiblock.BlockData;
import elec332.core.multiblock.MultiBlockRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Created by Elec332 on 24-2-2015.
 */
@Mod(modid = AlchemicalBrewing.ModID, name = AlchemicalBrewing.ModName, dependencies = ModInfo.DEPENDENCIES+"@[#ELECCORE_VER#,)",
        acceptedMinecraftVersions = ModInfo.ACCEPTEDMCVERSIONS, useMetadata = true, canBeDeactivated = true)
public class AlchemicalBrewing {

    public static final String ModName = "Alchemical Brewing";
    public static final String ModID = "AlchemicalBrewing";

    @SidedProxy(clientSide = "elec332.alchemicalbrewing.proxies.ClientProxy", serverSide = "elec332.alchemicalbrewing.proxies.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(ModID)
    public static AlchemicalBrewing instance;
    public static Configuration config;
    public static ConfigWrapper configWrapper;
    public static Logger logger;
    public static File configFolder;
    public static CreativeTabs creativeTab;
    public static MultiBlockRegistry multiBlockRegistry;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configFolder = FileHelper.getElecConfigFolder(event);
        config = new Configuration(configFolder, ModID+".cfg");
        configWrapper = new ConfigWrapper(config);
        config.load();
        logger = event.getModLog();
        configWrapper.registerConfig(new Config());
        creativeTab = new CreativeTabs(ModID) {
            @Override
            public Item getTabIconItem() {
                return Items.potionitem;
            }
        };
        multiBlockRegistry = new MultiBlockRegistry();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
        PotionRegistry.instance.preInitFluids();
        //setting up mod stuff
        logger.info("Glass bottle capacity: " + FluidContainerRegistry.getContainerCapacity(new FluidStack(FluidRegistry.WATER, 987), new ItemStack(Items.glass_bottle)));
        logger.info("Glass bottle capacity: "+FluidContainerRegistry.getContainerCapacity(new ItemStack(Items.potionitem)));


        MCModInfo.CreateMCModInfo(event, "Created by Elec332",
                "A complete brewing overhaul!",
                "website link", "logo",
                new String[]{"Elec332"});
        config.save();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        config.load();
        ItemRegister.instance.init();
        BlockRegister.instance.init();
        MultiBlocks.registerMultiBlocks();
        PotionRegistry.instance.init();
        //register items/blocks

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event){
        SortingHandler.checkForConflicts();
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        CommandRegister.instance.init(event);
    }

}
