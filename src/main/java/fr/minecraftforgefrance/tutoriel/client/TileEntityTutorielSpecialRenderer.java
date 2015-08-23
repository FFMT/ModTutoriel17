package fr.minecraftforgefrance.tutoriel.client;

import org.lwjgl.opengl.GL11;

import fr.minecraftforgefrance.tutoriel.common.ModTutoriel;
import fr.minecraftforgefrance.tutoriel.common.TileEntityTutoriel;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityTutorielSpecialRenderer extends TileEntitySpecialRenderer
{
    public ModelBlockTutoriel model = new ModelBlockTutoriel();
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
        float f1 = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * partialRenderTick;
        f1 = 1.0F - f1;
        f1 = 1.0F - f1 * f1 * f1;
        model.doorLeft.rotateAngleY = -(f1 * (float)Math.PI / 2.0F);
        model.leftHandle.rotateAngleY = -(f1 * (float)Math.PI / 2.0F);
        model.doorRight.rotateAngleY = (f1 * (float)Math.PI / 2.0F);
        model.rightHandle.rotateAngleY = (f1 * (float)Math.PI / 2.0F);
        model.renderAll();
        GL11.glPopMatrix();
    }
}