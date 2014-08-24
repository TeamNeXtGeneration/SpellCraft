package de.castelbuilder123.spellcraft.data;

public class ItemData
{
	private static boolean loaded = false;
	
	public static String CONFIG_CATEGORY_BLOCKDATA = "item data";
	/*
	public static String CONFIG_KEY_EXAMPLE = "exampleId"
	public static int CONFIG_VALUE_EXAMPLE;
	*/
	
	public static void Load()
	{
		if (!loaded)
		{
			loaded = true;
			// CONFIG_VALUE_EXAMPLE = SpellCraftMod.cfg.get(CONFIG_CATEGORY_BLOCKDATA, CONFIG_KEY_EXAMPLE, 2000).getInt(CONFIG_KEY_EXAMPLE);
		}
	}
	
	public static boolean getLoaded()
	{
		return loaded;
	}
}