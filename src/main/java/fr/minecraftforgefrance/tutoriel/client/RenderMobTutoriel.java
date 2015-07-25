package fr.minecraftforgefrance.tutoriel.client;

import fr.minecraftforgefrance.tutoriel.common.EntityMobTutoriel;
import fr.minecraftforgefrance.tutoriel.common.ModTutoriel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderMobTutoriel extends RenderBiped
{
    public final ResourceLocation texture = new ResourceLocation(ModTutoriel.MODID, "textures/entity/mob_tutoriel.png");

    public RenderMobTutoriel(ModelBiped model, float shadow)
    {
        super(model, shadow);
    }

    protected ResourceLocation getEntityTexture(EntityLiving living)
    {
        return this.getMobTutorielTexture((EntityMobTutoriel)living);
    }

    private ResourceLocation getMobTutorielTexture(EntityMobTutoriel mobTutoriel)
    {
        return texture;
    }
}