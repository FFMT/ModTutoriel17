package fr.minecraftforgefrance.tutoriel.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import fr.minecraftforgefrance.tutoriel.proxy.CommonProxy;

@Mod(modid = "modtutoriel", name = "Mod Tutoriel", version = "1.0.0")

public class ModTutoriel
{
	@Instance("modtutoriel")
	public static ModTutoriel instance;
	
	@SidedProxy(clientSide = "fr.minecraftforgefrance.tutoriel.proxy.ClientProxy", serverSide = "fr.minecraftforgefrance.tutoriel.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerRender();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
}