package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class ItemTutoPickaxe extends ItemPickaxe
{
	protected ItemTutoPickaxe(ToolMaterial material)
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