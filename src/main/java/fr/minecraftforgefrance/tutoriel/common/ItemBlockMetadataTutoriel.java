package fr.minecraftforgefrance.tutoriel.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockMetadataTutoriel extends ItemBlock
{
	private final String[] subName;
	public ItemBlockMetadataTutoriel(Block block, String[] subBlock)
	{
		super(block);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.subName = subBlock;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int metadata)
	{
		return this.field_150939_a.getIcon(2, metadata);
	}

	public int getMetadata(int metadata)
	{
		return metadata;
	}

	public String getUnlocalizedName(ItemStack stack)
	{
		int metadata = stack.getItemDamage();
		if(metadata < 0 || metadata >= this.subName.length)
		{
			metadata = 0;
		}
		return super.getUnlocalizedName() + "." + this.subName[metadata];
	}
}