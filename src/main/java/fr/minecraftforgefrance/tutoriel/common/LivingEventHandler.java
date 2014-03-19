package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class LivingEventHandler
{
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		ItemStack boots = event.entityLiving.getEquipmentInSlot(1);
		ItemStack leggings = event.entityLiving.getEquipmentInSlot(2);
		ItemStack chestPlate = event.entityLiving.getEquipmentInSlot(3);
		ItemStack helmet = event.entityLiving.getEquipmentInSlot(4);

		if(boots != null && boots.getItem() == ModTutoriel.bootsTuto && leggings != null && leggings.getItem() == ModTutoriel.leggingsTuto && chestPlate != null && chestPlate.getItem() == ModTutoriel.chestPlateTuto && helmet != null && helmet.getItem() == ModTutoriel.helmetTuto)
		{
			if(event.source.getEntity() != null && event.source.getEntity() instanceof EntityCreeper)
			{
				boots.damageItem(MathHelper.floor_float(event.ammount / 4), event.entityLiving);
				leggings.damageItem(MathHelper.floor_float(event.ammount / 4), event.entityLiving);
				chestPlate.damageItem(MathHelper.floor_float(event.ammount / 4), event.entityLiving);
				helmet.damageItem(MathHelper.floor_float(event.ammount / 4), event.entityLiving);
				event.ammount = 0F;
			}
			else if(event.source.getDamageType().equals("cactus"))
			{
				event.entityLiving.worldObj.newExplosion(event.entityLiving, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, event.ammount, true, true);
			}
		}
	}

	@SubscribeEvent
	public void onLivingFall(LivingFallEvent event)
	{
		ItemStack boots = event.entityLiving.getEquipmentInSlot(1); // récupère les bottes
		if(boots != null && boots.getItem() == ModTutoriel.bootsTuto) // on vérifie que l'entité à des bottes avec le null check puis si elle a des bottes, on vérifie si c'est les bottes tuto
		{
			boots.damageItem(MathHelper.floor_float(event.distance), event.entityLiving); // on endommage les bottes en fonction de la puissance de la chute
			event.distance = 0F; // on défini la distance à 0 pour pas que l'entité prenne de dégât
			if(boots.stackSize == 0) // si le stack size est de 0 (donc que les bottes ont cassées)
			{
				event.entityLiving.setCurrentItemOrArmor(1, null); // on met un item null au niveau des bottes, c'est pour évite un bug d'affichage 
			}
		}
	}
}