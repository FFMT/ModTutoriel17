package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemTutoArmor extends ItemArmor
{
	public ItemTutoArmor(ArmorMaterial material, int type)
	{
		super(material, 0, type);
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if(slot == 2)
		{
			return ModTutoriel.MODID + ":textures/models/armor/tutorial_layer_2.png";
		}
		return ModTutoriel.MODID + ":textures/models/armor/tutorial_layer_1.png";
	}

	public boolean getIsRepairable(ItemStack input, ItemStack repair)
	{
		if(repair.getItem() == ModTutoriel.itemTutoriel)
		{
			return true;
		}
		return false;
	}

	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		if(this.armorType == 0 && world.getBlockLightValue(MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ)) < 8)
		{
			player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 220, 0));
		}
		if(this.armorType == 2 && player.isSprinting())
		{
			player.motionX *= 1.1F;
			player.motionZ *= 1.1F;
		}
		player.addPotionEffect(new PotionEffect(Potion.resistance.id, 20, 0));
	}
}