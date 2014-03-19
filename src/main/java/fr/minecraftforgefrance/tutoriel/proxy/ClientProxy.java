package fr.minecraftforgefrance.tutoriel.proxy;

import net.minecraft.client.model.ModelBiped;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.minecraftforgefrance.tutoriel.client.RenderMobTutoriel;
import fr.minecraftforgefrance.tutoriel.common.EntityMobTutoriel;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRender()
	{
		System.out.println("méthode côté client");
		RenderingRegistry.registerEntityRenderingHandler(EntityMobTutoriel.class, new RenderMobTutoriel(new ModelBiped(), 0.5F));
	}
}