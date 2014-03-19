package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class ItemTutoHoe extends ItemHoe
{
	public ItemTutoHoe(ToolMaterial material)
	{
		super(material);
	}
	
	public boolean getIsRepairable(ItemStack input, ItemStack repair)
	{
		if(repair.getItem() == ModTutoriel.itemTutoriel)
		{
			return true;
		}
		return false;
	}
}