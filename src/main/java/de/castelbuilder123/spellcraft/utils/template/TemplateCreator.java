package de.castelbuilder123.spellcraft.utils.template;

import de.castelbuilder123.spellcraft.SpellCraftMod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class TemplateCreator {
    public static String Generate(World world, int sx, int sy, int sz, int ex, int ey, int ez) {
        return Generate(world, sx, sy, sz, ex, ey, ez, true, true, false);
    }

    public static String Generate(World world, int sx, int sy, int sz, int ex, int ey, int ez, boolean nbt, boolean meta, boolean useIds) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append(genHeaders(world, sx, sy, sz, ex, ey, ez, nbt, meta, useIds)+",\n");
        json.append(genTpl(world, sx, sy, sz, ex, ey, ez, nbt, meta, useIds));
        json.append("\n}");
        return json.toString();
    }

    private static String genHeaders(World world, int sx, int sy, int sz, int ex, int ey, int ez, boolean nbt, boolean meta, boolean useIds)
    {
        return "\"headers\": {" +"\n"+
               "\"worldName\": \""+ world.getProviderName() + "\""+","+"\n"+
               "\"worldID\": "+world.provider.dimensionId+","+"\n"+
               "\"nbt\": "+"\""+(nbt?"true":"false")+"\""+","+"\n"+
               "\"meta\": "+"\""+(meta?"true":"false")+"\""+","+"\n"+
               "\"useIds\": "+"\""+(useIds?"true":"false")+"\""+","+"\n"+
               "\"mx\": "+(sx-ex)+","+"\n"+
               "\"my\": "+(sy-ey)+","+"\n"+
               "\"mz\": "+(sz-ez)+"\n"+
               "}"+"\n";
    }

    private static String genTpl(World world, int sx, int sy, int sz, int ex, int ey, int ez, boolean nbt, boolean meta, boolean useIds)
    {
        StringBuilder json = new StringBuilder();
        json.append("\"template\": {\"blocks\": ["+"\n");
        boolean Xbackward = (sx < ex);
        boolean Ybackward = (sy < ey);
        boolean Zbackward = (sz < ez);
        //SpellCraftMod.log.info(Xbackward + " | " + Ybackward + " | " + Zbackward);
        int x = Xbackward?sx:ex;
        int y = Ybackward?sy:ey;
        int z = Zbackward?sz:ez;
        //SpellCraftMod.log.info(x + " f|f " + y + " f|f " +z);
        while ((Xbackward?(x <= ex):(x <= sx)))
        {
            //SpellCraftMod.log.info(x + " x|x " + y + " x|x " +z);
            while ((Ybackward?(y <= ey):(y <= sy)))
            {
                //SpellCraftMod.log.info(x + " y|y " + y + " y|y " +z);
                while ((Zbackward?(z <= ez):(z <= sz)))
                {
                    //SpellCraftMod.log.info(x + " z|z " + y + " z|z " +z);
                    json.append(genJsonFromBlock(world, world.getBlock(x,y,z), x,y,z,nbt,meta,useIds) + ((((Xbackward?(x == ex):(x == sx)) && (Ybackward?(y == ey):(y == sy)) && (Zbackward?(z == ez):(z == sz))))?(""):(",")));
                    json.append("\n");
                    z++;
                }
                z = (Zbackward?sz:ez);
                y++;
            }
            y = (Ybackward?sy:ey);
            x++;
            SpellCraftMod.log.info("Progress: "+ x +"/" + (Xbackward?ex:sx));
        }
        //SpellCraftMod.log.info("Z: ("+Zbackward+") "+z+" "+(Zbackward?(ez):(sz)) + " Condition: " + (Zbackward?(z <= ez):(z <= sz)));
        json.append("]}");
        return json.toString();
    }

    private static String genJsonFromBlock(World world, Block block, int offX, int offY, int offZ, boolean nbt, boolean meta, boolean useIds)
    {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"blockid\": " + (useIds?Block.getIdFromBlock(block):"\""+block.getUnlocalizedName()+"\"") + ",");
        if (meta)
            json.append("\"meta\": " + world.getBlockMetadata(offX,offY,offZ));
        if (nbt)
        {
            TileEntity tileEntity;
            if ((tileEntity = world.getTileEntity(offX, offY, offZ)) != null)
            {
            	json.append(",\"nbt\": {");
                if (tileEntity instanceof TileEntityMobSpawner)
                {
                    TileEntityMobSpawner tileEntitySpawner = (TileEntityMobSpawner) tileEntity;
                    json.append("\"mobname\": \"" + tileEntitySpawner.func_145881_a().getEntityNameToSpawn() + "\"");
                }
                else if (tileEntity instanceof TileEntityChest)
                {
                    TileEntityChest tileEntityChest = (TileEntityChest)tileEntity;
                    for (int i = 0; i < 26; i++)
                    {
                        ItemStack item;
                        if ((item = tileEntityChest.getStackInSlot(i)) != null)
                        {
                            json.append("\"slotid\": " + i + ",");
                            json.append("\"itemid\": \"" + item.getItem().getUnlocalizedName() + "\",");
                            json.append("\"itemcount\": " + item.stackSize);
                            break;
                        }
                    }
                }
                json.append("}");
            }
        }
        json.append("}");
        return json.toString();
    }

    private static String getBlockMeta(World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x,y,z);
        return String.valueOf(tileEntity.getBlockMetadata());
    }
}
