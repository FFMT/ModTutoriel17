package fr.minecraftforgefrance.tutoriel.common;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockTutorielMetadata extends Block
{
	public static String[] subBlock = new String[] {"block1", "block2", "block3", "block4"};
	public IIcon[] iconArray = new IIcon[subBlock.length];

	protected BlockTutorielMetadata()
	{
		super(Material.rock);
	}

	public int damageDropped(int metadata)
	{
		return metadata;
	}

	public void getSubBlocks(Item item, CreativeTabs tabs, List list)
	{
		for(int i = 0; i < subBlock.length; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	public void registerBlockIcons(IIconRegister iconRegister)
	{
		for(int i = 0; i < subBlock.length; i++)
		{
			this.iconArray[i] = iconRegister.registerIcon(ModTutoriel.MODID + ":" + subBlock[i]);
		}
	}

	public IIcon getIcon(int side, int metadata)
	{
		return metadata >= 0 && metadata < subBlock.length ? this.iconArray[metadata] : this.iconArray[0];
	}
}