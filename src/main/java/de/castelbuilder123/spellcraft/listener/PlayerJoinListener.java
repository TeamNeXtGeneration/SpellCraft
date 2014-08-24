package de.castelbuilder123.spellcraft.listener;

import com.mojang.realmsclient.gui.ChatFormatting;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.data.MapData;
import de.castelbuilder123.spellcraft.data.PlayerData;
import de.castelbuilder123.spellcraft.utils.dict.PlayerDict;
import de.castelbuilder123.spellcraft.utils.template.BlockDictionary;
import de.castelbuilder123.spellcraft.utils.template.ItemDictionary;
import de.castelbuilder123.spellcraft.utils.template.TemplateCreator;
import de.castelbuilder123.spellcraft.utils.template.TemplateParser;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBucket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Jona on 05.08.14.
 */
public class PlayerJoinListener {
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
    {
        PlayerData.onJoin(event.player);
        /*
        String awesome = de.castelbuilder123.spellcraft.utils.template.TemplateCreator.Generate(event.player.worldObj, -14, 124, -15, 71, 153, 16);
        SpellCraftMod.log.info(awesome.length());
        try
        {
            BufferedWriter out = new BufferedWriter(new PrintWriter("template.txt"));
            out.write(awesome);
            out.flush();
            out.close();
        }
        catch (Exception e)
        {
            SpellCraftMod.log.error("Couldn't save template");
            SpellCraftMod.log.error(e.toString());
        }*/
        //event.player.addChatMessage(new ChatComponentText(awesome));
        if (BlockDictionary.unlocalizedNameToBlock.isEmpty())
        {
            for (Object b: Block.blockRegistry)
            {
                if (b instanceof Block)
                {
                    Block block = (Block)b;
                    BlockDictionary.unlocalizedNameToBlock.put(block.getUnlocalizedName(), block);
                }
            }
        }
        if (ItemDictionary.unlocalizedNameToItem.isEmpty())
        {
            for (Object b: Item.itemRegistry)
            {
                if (b instanceof Item)
                {
                    Item block = (Item)b;
                    ItemDictionary.unlocalizedNameToItem.put(block.getUnlocalizedName(), block);
                }
            }
        }
        PlayerDict.playernameToPlayer.put(event.player.getDisplayName(), event.player);
    }
    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event)
    {
        PlayerData.onLogout(event.player);
        PlayerDict.playernameToPlayer.remove(event.player.getDisplayName());
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event)
    {
        if (BlockDictionary.unlocalizedNameToBlock.isEmpty())
        {
            for (Object b: Block.blockRegistry)
            {
                if (b instanceof Block)
                {
                    Block block = (Block)b;
                    BlockDictionary.unlocalizedNameToBlock.put(block.getUnlocalizedName(), block);
                }
            }
        }
        if (ItemDictionary.unlocalizedNameToItem.isEmpty())
        {
            for (Object b: Item.itemRegistry)
            {
                if (b instanceof Item)
                {
                    Item block = (Item)b;
                    ItemDictionary.unlocalizedNameToItem.put(block.getUnlocalizedName(), block);
                }
            }
        }

        //for (Block b: BlockDictionary.unlocalizedNameToBlock.values())
        //{
        //    SpellCraftMod.log.info(b.getUnlocalizedName());
        //}

        if (event.world.provider.dimensionId == -2) //if (false)
        {
            MapData mapData = (MapData)event.world.mapStorage.loadData(MapData.class, MapData.IDENTIFIER);
            if (mapData != null)
            {
                if (!mapData.getData().getBoolean("underWorldGenerated"))
                {
                    try
                    {
                        TemplateParser.Parse(TemplateParser.readTemplate("DevilCastle"),event.world, -14, 124, -15, 71, 153, 16);
                        mapData.getData().setBoolean("underWorldGenerated", true);
                    }
                    catch (IOException e)
                    {
                        SpellCraftMod.log.error(e.toString());
                    }
                }
            }
            else
            {
                mapData = new MapData();
                try
                {
                    TemplateParser.Parse(TemplateParser.readTemplate("DevilCastle"),event.world, -14, 124, -15, 71, 153, 16);
                    mapData.getData().setBoolean("underWorldGenerated", true);
                }
                catch (IOException e)
                {
                    SpellCraftMod.log.error(e.toString());
                }
            }
            mapData.markDirty();
            event.world.mapStorage.setData(MapData.IDENTIFIER, mapData);
            event.world.mapStorage.saveAllData();
        }
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event)
    {
        //SpellCraftMod.log.info("UNLOADING WORLD!!!");
        if (event.world.provider.dimensionId == 0) // Because the OverWorld doesn't unload until server stop, because the spawn area is always loaded
        {
            for (WorldServer world: MinecraftServer.getServer().worldServers)
            {
                for (Object playerObj: world.playerEntities)
                {
                    if (playerObj instanceof EntityPlayer)
                    {
                        PlayerData.onLogout((EntityPlayer)playerObj);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event)
    {
        if (event.getPlayer().capabilities.isCreativeMode)
            return;
        if (event.world.provider.dimensionId == -2) // No block breaking in Dim -2
        {
            if (event.isCancelable())
                event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onBlockPlace(PlayerInteractEvent event)
    {
        if (event.entityPlayer.worldObj.provider.dimensionId != -2)
            return;
        if (event.entityPlayer.capabilities.isCreativeMode)
            return;
        if (event.entityPlayer.inventory.getCurrentItem() == null)
            return;
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
        {
            if (event.entityPlayer.inventory.getCurrentItem().getItem() instanceof ItemBlock || event.entityPlayer.inventory.getCurrentItem().getItem() instanceof ItemBucket) // if item is block or bucket cancel
            {
                if (event.isCancelable())
                {
                    event.entityPlayer.addChatMessage(new ChatComponentText(ChatFormatting.DARK_PURPLE+"If "+ChatFormatting.DARK_PURPLE+"you "+ChatFormatting.DARK_PURPLE+"want "+ChatFormatting.DARK_PURPLE+"to "+ChatFormatting.DARK_PURPLE+"interact "+ChatFormatting.DARK_PURPLE+"with "+ChatFormatting.DARK_PURPLE+"an "+ChatFormatting.DARK_PURPLE+"block "+ChatFormatting.DARK_PURPLE+"please "+ChatFormatting.DARK_PURPLE+"do "+ChatFormatting.DARK_PURPLE+"this "+ChatFormatting.DARK_PURPLE+"with "+ChatFormatting.DARK_PURPLE+"an "+ChatFormatting.DARK_PURPLE+"empty "+ChatFormatting.DARK_PURPLE+"Hand "+ChatFormatting.DARK_PURPLE+"or "+ChatFormatting.DARK_PURPLE+"with "+ChatFormatting.DARK_PURPLE+"an "+ChatFormatting.DARK_PURPLE+"Item "+ChatFormatting.DARK_PURPLE+"in "+ChatFormatting.DARK_PURPLE+"your "+ChatFormatting.DARK_PURPLE+"Hand"));
                    event.setCanceled(true);
                }
            }
        }
    }
}
