/**
 * This work is made available under the terms of the Creative Commons Attribution License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/deed.en
 * 
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/deed.fr
 */
package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemTutoriel extends Item
{
	public ItemTutoriel()
	{
		super();
	}

	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		this.itemIcon = iconregister.registerIcon(ModTutoriel.MODID + ":itemtutoriel");
	}

	@Override
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		return this.itemIcon;
	}
}
