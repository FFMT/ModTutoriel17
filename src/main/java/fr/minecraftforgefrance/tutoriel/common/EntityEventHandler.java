package fr.minecraftforgefrance.tutoriel.common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

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