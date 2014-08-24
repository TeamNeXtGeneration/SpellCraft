package de.castelbuilder123.spellcraft.utils.template;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by Jona on 12.08.14.
 */
public class TemplateParser {
    public static String readTemplate(String templateName) throws IOException
    {
        SpellCraftMod.log.info("Opening Template");
        InputStream resStream = SpellCraftMod.instance.getClass().getResourceAsStream("/assets/"+SpellCraftMod.MODID+"/templates/"+templateName+".json");
        BufferedReader br = new BufferedReader( new InputStreamReader(resStream, StandardCharsets.UTF_8) );
        StringBuilder sb = new StringBuilder();
        String line;
        while ( (line = br.readLine()) != null) {
            sb.append(line).append(System.lineSeparator());
        }
        SpellCraftMod.log.info("Closing File");
        br.close();
        resStream.close();
        return sb.toString();
    }

    public static void Parse(String json, World world, int sx, int sy, int sz, int ex, int ey, int ez) {
        SpellCraftMod.log.info("Parsing");
        try
        {
            JSONObject root = new JSONObject(json);
            JSONObject headers = root.getJSONObject("headers");
            boolean nbt = Boolean.parseBoolean(headers.getString("nbt"));
            boolean meta = Boolean.parseBoolean(headers.getString("meta"));
            boolean ids = Boolean.parseBoolean(headers.getString("useIds"));
            JSONArray blockArray = root.getJSONObject("template").getJSONArray("blocks");
            boolean Xbackward = (sx < ex);
            boolean Ybackward = (sy < ey);
            boolean Zbackward = (sz < ez);
            //SpellCraftMod.log.info(Xbackward + " | " + Ybackward + " | " + Zbackward);
            int x = Xbackward?sx:ex;
            int y = Ybackward?sy:ey;
            int z = Zbackward?sz:ez;
            int index = 0;
            //SpellCraftMod.log.info(x + " f|f " + y + " f|f " +z);
            while ((Xbackward?(x <= ex):(x <= sx)))
            {
                //SpellCraftMod.log.info(x + " x|x " + y + " x|x " +z);
                while ((Ybackward?(y <= ey):(y <= sy)))
                {
                    //SpellCraftMod.log.info(x + " y|y " + y + " y|y " +z);
                    while ((Zbackward?(z <= ez):(z <= sz)))
                    {
                        JSONObject block = blockArray.getJSONObject(index);
                        if (block != null)
                        {
                            String id = block.getString("blockid");
                            int metaData = block.getInt("meta");
                            world.setBlock(x,y,z, BlockDictionary.unlocalizedNameToBlock.get(id), metaData, 2);
                            if (id.equalsIgnoreCase("tile.chest"))
                            {
                                //SpellCraftMod.log.info("CHEST!!!");
                                JSONObject nbtTag = block.getJSONObject("nbt");
                                int sid = nbtTag.getInt("slotid");
                                String item = nbtTag.getString("itemid");
                                int count = nbtTag.getInt("itemcount");
                                TileEntity tile = world.getTileEntity(x,y,z);
                                if (tile != null && tile instanceof TileEntityChest)
                                {
                                    TileEntityChest tileEntityChest = (TileEntityChest)tile;
                                    tileEntityChest.setInventorySlotContents(sid, new ItemStack(ItemDictionary.unlocalizedNameToItem.get(item), count));
                                }
                            }
                            else if (id.equalsIgnoreCase("tile.mobSpawner"))
                            {
                                //SpellCraftMod.log.info("SPAWNER!!!");
                                String mob = block.getJSONObject("nbt").getString("mobname");
                                TileEntity tile = world.getTileEntity(x,y,z);
                                if (tile != null && tile instanceof TileEntityMobSpawner)
                                {
                                    TileEntityMobSpawner tileEntityMobSpawner = (TileEntityMobSpawner)tile;
                                    tileEntityMobSpawner.func_145881_a().setEntityName(mob);
                                }
                            }
                        }
                        index++;
                        z++;
                    }
                    z = (Zbackward?sz:ez);
                    y++;
                }
                y = (Ybackward?sy:ey);

                x++;
                SpellCraftMod.log.info("Progress: "+ x +"/" + (Xbackward?ex:sx));
            }
        }
        catch (JSONException exc)
        {
            throw exc;
        }
    }
}
