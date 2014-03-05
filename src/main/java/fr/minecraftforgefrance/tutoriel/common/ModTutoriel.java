package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.minecraftforgefrance.tutoriel.proxy.CommonProxy;

@Mod(modid = ModTutoriel.MODID, name = "Mod Tutoriel", version = "1.0.0")
public class ModTutoriel
{
	public static final String MODID = "modtutoriel";

	@Instance(MODID)
	public static ModTutoriel instance;

	@SidedProxy(clientSide = "fr.minecraftforgefrance.tutoriel.proxy.ClientProxy", serverSide = "fr.minecraftforgefrance.tutoriel.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Item itemTutoriel, itemTutoriel2;
	public static Block blockTutoriel, blockTutoriel2;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		itemTutoriel = new ItemTutoriel().setUnlocalizedName("tutoriel").setTextureName(MODID + ":item_tutoriel").setCreativeTab(CreativeTabs.tabMaterials);
		itemTutoriel2 = new ItemTutoriel().setUnlocalizedName("tutoriel2").setTextureName(MODID + ":item_tutoriel2").setCreativeTab(CreativeTabs.tabMaterials);

		GameRegistry.registerItem(itemTutoriel, "item_tutoriel");
		GameRegistry.registerItem(itemTutoriel2, "item_tutoriel2");

		blockTutoriel = new BlockTutoriel(Material.rock).setBlockName("tutoriel").setBlockTextureName(MODID + ":block_tutoriel").setCreativeTab(CreativeTabs.tabBlock);
		blockTutoriel2 = new BlockTutoriel(Material.wood).setBlockName("tutoriel2").setBlockTextureName(MODID + ":block_tutoriel2").setCreativeTab(CreativeTabs.tabBlock);

		GameRegistry.registerBlock(blockTutoriel, "block_tutoriel");
		GameRegistry.registerBlock(blockTutoriel2, "block_tutoriel2");
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