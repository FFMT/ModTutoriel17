package fr.minecraftforgefrance.tutoriel.common;

import java.awt.Color;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.minecraftforgefrance.tutoriel.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = ModTutoriel.MODID, name = "Mod Tutoriel", version = "1.0.0")
public class ModTutoriel
{
    public static final String MODID = "modtutoriel";

    @Instance(MODID)
    public static ModTutoriel instance;

    @SidedProxy(clientSide = "fr.minecraftforgefrance.tutoriel.proxy.ClientProxy", serverSide = "fr.minecraftforgefrance.tutoriel.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static Item itemTutoriel, itemTutoriel2, helmetTuto, chestPlateTuto, leggingsTuto, bootsTuto, swordTuto, pickaxeTuto, axeTuto, shovelTuto, hoeTuto;
    public static Block blockTutoriel, blockTutoriel2, blockMetadataTuto, machineTuto;

    public static ArmorMaterial armorTuto = EnumHelper.addArmorMaterial("armorTuto", 25, new int[] {4, 6, 5, 4}, 20);
    public static ToolMaterial toolTuto = EnumHelper.addToolMaterial("toolTuto", 2, 854, 12.0F, 4.0F, 18);

    public static SimpleNetworkWrapper network;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        itemTutoriel = new ItemTutoriel().setUnlocalizedName("tutoriel").setTextureName(MODID + ":item_tutoriel").setCreativeTab(CreativeTabs.tabMaterials);
        itemTutoriel2 = new ItemTutoriel().setUnlocalizedName("tutoriel2").setTextureName(MODID + ":item_tutoriel2").setCreativeTab(CreativeTabs.tabMaterials);
        helmetTuto = new ItemTutoArmor(armorTuto, 0).setUnlocalizedName("helmetTuto").setTextureName(MODID + ":helmet_tutoriel");
        chestPlateTuto = new ItemTutoArmor(armorTuto, 1).setUnlocalizedName("chestPlateTuto").setTextureName(MODID + ":chestplate_tutoriel");
        leggingsTuto = new ItemTutoArmor(armorTuto, 2).setUnlocalizedName("leggingsTuto").setTextureName(MODID + ":leggings_tutoriel");
        bootsTuto = new ItemTutoArmor(armorTuto, 3).setUnlocalizedName("bootsTuto").setTextureName(MODID + ":boots_tutoriel");
        swordTuto = new ItemTutoSword(toolTuto).setUnlocalizedName("swordTuto").setTextureName(MODID + ":sword_tutoriel");
        pickaxeTuto = new ItemTutoPickaxe(toolTuto).setUnlocalizedName("pickaxeTuto").setTextureName(MODID + ":pickaxe_tutoriel");
        axeTuto = new ItemTutoAxe(toolTuto).setUnlocalizedName("axeTuto").setTextureName(MODID + ":axe_tutoriel");
        shovelTuto = new ItemTutoShovel(toolTuto).setUnlocalizedName("shovelTuto").setTextureName(MODID + ":shovel_tutoriel");
        hoeTuto = new ItemTutoHoe(toolTuto).setUnlocalizedName("hoeTuto").setTextureName(MODID + ":hoe_tutoriel");
        obsidianBow= new obsidianBow().setUnlocalizedName("obsidianBow").setTextureName(MODID+ ":obsidian_bow").setCreativeTab(CreativeTabs.tabCombat);

        GameRegistry.registerItem(itemTutoriel, "item_tutoriel");
        GameRegistry.registerItem(itemTutoriel2, "item_tutoriel2");
        GameRegistry.registerItem(helmetTuto, "item_tuto_helmet");
        GameRegistry.registerItem(chestPlateTuto, "item_tuto_chestplate");
        GameRegistry.registerItem(leggingsTuto, "item_tuto_leggings");
        GameRegistry.registerItem(bootsTuto, "item_tuto_boots");
        GameRegistry.registerItem(swordTuto, "item_tuto_sword");
        GameRegistry.registerItem(pickaxeTuto, "item_tuto_pickaxe");
        GameRegistry.registerItem(axeTuto, "item_tuto_axe");
        GameRegistry.registerItem(shovelTuto, "item_tuto_shovel");
        GameRegistry.registerItem(hoeTuto, "item_tuto_hoe");
        GameRegistry.registerItem(obsidianBow, "obsidianBow");
      
        blockTutoriel = new BlockTutoriel(Material.rock).setHardness(1.5F).setResistance(10.0F).setBlockName("tutoriel").setCreativeTab(CreativeTabs.tabBlock);
        blockTutoriel2 = new BlockTutoriel2(Material.wood).setHardness(1.5F).setResistance(10.0F).setBlockName("tutoriel2").setBlockTextureName(MODID + ":block_tutoriel2").setCreativeTab(CreativeTabs.tabBlock);
        blockMetadataTuto = new BlockTutorielMetadata().setBlockName("metadataTuto").setHardness(1.5F).setResistance(10.0F).setCreativeTab(CreativeTabs.tabBlock);
        machineTuto = new MachineTuto().setBlockName("machineTuto");

        GameRegistry.registerBlock(blockTutoriel, "block_tutoriel");
        GameRegistry.registerBlock(blockTutoriel2, "block_tutoriel2");
        GameRegistry.registerBlock(blockMetadataTuto, ItemBlockMetadataTutoriel.class, "block_tuto_metadata", new Object[] {BlockTutorielMetadata.subBlock});
        GameRegistry.registerBlock(machineTuto, "machineTuto");

        network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        network.registerMessage(PacketCobbleOnly.Handler.class, PacketCobbleOnly.class, 0, Side.SERVER);
        network.registerMessage(PacketRequestPlayerList.Handler.class, PacketRequestPlayerList.class, 1, Side.SERVER);
        network.registerMessage(PacketPlayerList.Handler.class, PacketPlayerList.class, 2, Side.CLIENT);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        EntityRegistry.registerGlobalEntityID(EntityMobTutoriel.class, "mobTutoriel", EntityRegistry.findGlobalUniqueEntityId(), new Color(0, 0, 255).getRGB(), new Color(255, 0, 0).getRGB());
        EntityRegistry.registerModEntity(EntityMobTutoriel.class, "mobTutoriel", 420, this.instance, 40, 2, true);

        GameRegistry.registerTileEntity(TileEntityTutoriel.class, "modtutoriel:tutoriel");
        GameRegistry.registerTileEntity(TileEntityDirectional.class, "modtutoriel:directional");
        GameRegistry.registerTileEntity(TileEntityMachineTuto.class, "modTutoriel:MachineTutoTileEntity");

        MinecraftForge.EVENT_BUS.register(new LivingEventHandler());
        MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
        FMLCommonHandler.instance().bus().register(new PlayerEventHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
        proxy.registerRender();
        proxy.registerKeyBinding();

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandlerTuto());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
