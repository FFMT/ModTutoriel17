package fr.minecraftforgefrance.tutoriel.client;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fr.minecraftforgefrance.tutoriel.common.ModTutoriel;
import fr.minecraftforgefrance.tutoriel.common.TileEntityTutoriel;

public class TileEntityTutorielSpecialRenderer extends TileEntitySpecialRenderer
{
    public static ModelBlockTutoriel model = new ModelBlockTutoriel();
    public static ResourceLocation texture = new ResourceLocation(ModTutoriel.MODID, "textures/models/blocks/model_block_tutoriel.png");
    
    public TileEntityTutorielSpecialRenderer()
    {
        this.func_147497_a(TileEntityRendererDispatcher.instance);
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialRenderTick)
    {
        this.renderTileEntityTutorielAt((TileEntityTutoriel)tile, x, y, z, partialRenderTick);
    }

    private void renderTileEntityTutorielAt(TileEntityTutoriel tile, double x, double y, double z, float partialRenderTick)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef((90F * tile.getDirection()) + 180F, 0.0F, 1.0F, 0.0F);
        this.bindTexture(texture);
        model.renderAll();
        GL11.glPopMatrix();
    }
}