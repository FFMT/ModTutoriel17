package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.minecraftforgefrance.tutoriel.proxy.CommonProxy;

@Mod(modid = ModTutoriel.MODID, name = "Mod Tutoriel", version = "1.0.0")
public class ModTutoriel
{
	public static final String MODID = "modtutoriel";

	@Instance(MODID)
	public static ModTutoriel instance;

	@SidedProxy(clientSide = "fr.minecraftforgefrance.tutoriel.proxy.ClientProxy", serverSide = "fr.minecraftforgefrance.tutoriel.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Item itemTutoriel;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		itemTutoriel = new ItemTutoriel().setUnlocalizedName("itemTutoriel").setTextureName(ModTutoriel.MODID + ":itemtutoriel").setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerItem(itemTutoriel, "itemtutoriel", ModTutoriel.MODID);
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