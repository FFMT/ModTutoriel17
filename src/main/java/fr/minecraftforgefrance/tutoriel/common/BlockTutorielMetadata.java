package fr.minecraftforgefrance.tutoriel.common;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockTutorielMetadata extends Block
{
	public static String[] subBlock = new String[] {"block1", "block2", "block3", "block4"};
	private IIcon[][] icons = new IIcon[subBlock.length][3];
	
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
			for(int j = 0; j < 3; j++)
			{
				this.icons[i][j] = iconRegister.registerIcon(ModTutoriel.MODID + ":" + subBlock[i] + "_" + j);
			}
		}
	}

	public IIcon getIcon(int side, int metadata)
	{
		if(side > 2)
		{
			side = 2;
		}
		return metadata >= 0 && metadata < subBlock.length ? this.icons[metadata][side] : this.icons[0][0];
	}
}