package de.castelbuilder123.spellcraft.registers;

public class FluidRegistery
{
	private static boolean registered = false;
	public static void Register()
	{
		if (!registered)
		{
			registered = true;
			//TODO: Add registration Stuff and Things.
		}
	}
	public static boolean getRegistered()
	{
		return registered;
	}
}