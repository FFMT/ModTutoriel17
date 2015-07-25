package fr.minecraftforgefrance.tutoriel.proxy;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.minecraftforgefrance.tutoriel.client.RenderMobTutoriel;
import fr.minecraftforgefrance.tutoriel.client.TESRInventoryRenderer;
import fr.minecraftforgefrance.tutoriel.client.TileEntityTutorielSpecialRenderer;
import fr.minecraftforgefrance.tutoriel.common.EntityMobTutoriel;
import fr.minecraftforgefrance.tutoriel.common.TileEntityTutoriel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.settings.KeyBinding;

public class ClientProxy extends CommonProxy
{
    public static int tesrRenderId;
    public static KeyBinding playerListKey = new KeyBinding("modtutoriel.playerList", Keyboard.KEY_N, "key.categories.misc");

    @Override
    public void registerRender()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityMobTutoriel.class, new RenderMobTutoriel(new ModelBiped(), 0.5F));

        tesrRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new TESRInventoryRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTutoriel.class, new TileEntityTutorielSpecialRenderer());
    }

    @Override
    public void registerKeyBinding()
    {
        ClientRegistry.registerKeyBinding(playerListKey);
    }
}