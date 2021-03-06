package de.castelbuilder123.spellcraft;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import de.castelbuilder123.spellcraft.data.BlockData;
import de.castelbuilder123.spellcraft.data.EntityData;
import de.castelbuilder123.spellcraft.data.FluidData;
import de.castelbuilder123.spellcraft.data.ItemData;
import de.castelbuilder123.spellcraft.listener.PlayerJoinListener;
import de.castelbuilder123.spellcraft.network.PacketHandler;
import de.castelbuilder123.spellcraft.proxies.Proxy;
import de.castelbuilder123.spellcraft.registers.*;
import de.castelbuilder123.spellcraft.worldgen.SpellCraftOreGenerator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = SpellCraftMod.MODID, version = SpellCraftMod.VERSION)
public class SpellCraftMod
{
    @Mod.Instance("spellcraft")
    public static SpellCraftMod instance;

	@SidedProxy(clientSide = "de.castelbuilder123.spellcraft.proxies.ClientProxy", serverSide = "de.castelbuilder123.spellcraft.proxies.ServerProxy")
	public static Proxy proxy = Proxy.getProxy();
	
    public static final String MODID = "spellcraft";
    public static final String VERSION = "1.0";
    public static final String MODNAME = "SpellCraft";

    public static Configuration cfg;
    public static Logger log;

    public static CreativeTabs tabSpellCraft = new CreativeTabs("SpellCraft") {
        @Override
        public Item getTabIconItem() {
            return ItemRegistery.DevilSummoningStaffItem;
        }
    };
    public static CreativeTabs tabSpellCraftBright = new CreativeTabs("SpellCraftBrightPath") {
        @Override
        public Item getTabIconItem() {
        return ItemRegistery.SourceOfLightItem;
    }
    };
    public static CreativeTabs tabSpellCraftDark = new CreativeTabs("SpellCraftDarkPath") {
        @Override
        public Item getTabIconItem() {
            return ItemRegistery.DevilTridentItem;
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        log = LogManager.getLogger(MODNAME);
        log.info("===SpellCraft PreInit===");
        log.info("Loading Config...");
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "/SpellCraft.cfg"));
        cfg.load();
        log.info("Reading config... BlockSettings");
        BlockData.Load();
        log.info("Reading config... ItemSettings");
        ItemData.Load();
        log.info("Reading config... FluidSettings");
        FluidData.Load();
        log.info("Reading config... EntitySettings");
        EntityData.Load();
        log.info("Registering Blocks");
        BlockRegistery.Register();
        log.info("Registering Items");
        ItemRegistery.Register();
        log.info("Registering Fluids");
        FluidRegistery.Register();
        log.info("Registering Entitys");
        EntityRegistery.Register();
        log.info("Registering Dimensions");
        DimensionRegistery.Register();
        log.info("Registering Packets");
        PacketHandler.init();
        log.info("Registering WorldGenerators");
        GameRegistry.registerWorldGenerator(new SpellCraftOreGenerator(), 36);
        log.info("Registering GuiHandler");
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		log.info("Registering Crafting");
        CraftingRegistery.Register();
        log.info("Registering OreDictionary Names");
        OreDictionary.registerOre("oreMagic", BlockRegistery.MagicOre);
        OreDictionary.registerOre("powerMagicBright", ItemRegistery.PowerOfTheBrightItem);
        OreDictionary.registerOre("powerMagicDark", ItemRegistery.PowerOfTheDarknessItem);
		log.info("Saving changed config");
        if (cfg.hasChanged())
            cfg.save();
        log.info("===SpellCraft EndPreInit===");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		log.info("===SpellCraft Init===");
        log.info("Registering Renderer");
        proxy.initRenderers();
        log.info("Registering Sounds");
        proxy.initSounds();
        log.info("Registering Events");
        FMLCommonHandler.instance().bus().register(new PlayerJoinListener()); // There are actually 2 event buses, one for game events
        MinecraftForge.EVENT_BUS.register(new PlayerJoinListener());          // one for FML events
        log.info("===SpellCraft EndInit===");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        log.info("===SpellCraft PostInit===");
        log.info("===SpellCraft EndPostInit===");
    }
}
