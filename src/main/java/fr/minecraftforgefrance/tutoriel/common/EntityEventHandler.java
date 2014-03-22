package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EntityEventHandler
{
	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent event)
	{
		if(event.entity instanceof EntityZombie)
		{
			event.setCanceled(true);
		}
	}
}