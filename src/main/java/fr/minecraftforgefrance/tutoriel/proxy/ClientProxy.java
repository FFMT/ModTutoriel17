package fr.minecraftforgefrance.tutoriel.proxy;

import net.minecraft.client.model.ModelBiped;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.minecraftforgefrance.tutoriel.client.RenderMobTutoriel;
import fr.minecraftforgefrance.tutoriel.client.TESRInventoryRenderer;
import fr.minecraftforgefrance.tutoriel.client.TileEntityTutorielSpecialRenderer;
import fr.minecraftforgefrance.tutoriel.common.EntityMobTutoriel;
import fr.minecraftforgefrance.tutoriel.common.TileEntityTutoriel;

public class ClientProxy extends CommonProxy
{
    public static int tesrRenderId;
    @Override
    public void registerRender()
    {
        System.out.println("méthode côté client");
        RenderingRegistry.registerEntityRenderingHandler(EntityMobTutoriel.class, new RenderMobTutoriel(new ModelBiped(), 0.5F));
        
        tesrRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new TESRInventoryRenderer());
        
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTutoriel.class, new TileEntityTutorielSpecialRenderer());
    }
}