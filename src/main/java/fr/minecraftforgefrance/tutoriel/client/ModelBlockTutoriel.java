package fr.minecraftforgefrance.tutoriel.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlockTutoriel extends ModelBase
{
    ModelRenderer bottomPlate;
    ModelRenderer topPlate;
    ModelRenderer rightPlate;
    ModelRenderer leftPlate;
    ModelRenderer backPlate;
    ModelRenderer doorLeft;
    ModelRenderer doorRight;
    ModelRenderer leftHandle;
    ModelRenderer rightHandle;

    public ModelBlockTutoriel()
    {
        textureWidth = 64;
        textureHeight = 64;

        bottomPlate = new ModelRenderer(this, 0, 0);
        bottomPlate.addBox(0F, 0F, 0F, 14, 1, 14);
        bottomPlate.setRotationPoint(-7F, 23F, -7F);
        bottomPlate.setTextureSize(64, 64);
        setRotation(bottomPlate, 0F, 0F, 0F);
        topPlate = new ModelRenderer(this, 0, 0);
        topPlate.mirror = true;
        topPlate.addBox(0F, 0F, 0F, 14, 1, 14);
        topPlate.setRotationPoint(-7F, 10F, -7F);
        topPlate.setTextureSize(64, 64);
        setRotation(topPlate, 0F, 0F, 0F);
        rightPlate = new ModelRenderer(this, 0, 16);
        rightPlate.addBox(0F, 0F, 0F, 1, 12, 13);
        rightPlate.setRotationPoint(-7F, 11F, -7F);
        rightPlate.setTextureSize(64, 64);
        setRotation(rightPlate, 0F, 0F, 0F);
        leftPlate = new ModelRenderer(this, 0, 16);
        leftPlate.mirror = true;
        leftPlate.addBox(0F, 0F, 0F, 1, 12, 13);
        leftPlate.setRotationPoint(6F, 11F, -7F);
        leftPlate.setTextureSize(64, 64);
        setRotation(leftPlate, 0F, 0F, 0F);
        backPlate = new ModelRenderer(this, 29, 16);
        backPlate.mirror = true;
        backPlate.addBox(0F, 0F, 0F, 14, 12, 1);
        backPlate.setRotationPoint(-7F, 11F, 6F);
        backPlate.setTextureSize(64, 64);
        setRotation(backPlate, 0F, 0F, 0F);
        doorLeft = new ModelRenderer(this, 29, 30);
        doorLeft.mirror = true;
        doorLeft.addBox(-6F, -6F, 0F, 6, 12, 1);
        doorLeft.setRotationPoint(6F, 17F, -6.8F);
        doorLeft.setTextureSize(64, 64);
        setRotation(doorLeft, 0F, 0F, 0F);
        doorRight = new ModelRenderer(this, 29, 30);
        doorRight.addBox(0F, -6F, 0F, 6, 12, 1);
        doorRight.setRotationPoint(-6F, 17F, -6.8F);
        doorRight.setTextureSize(64, 64);
        setRotation(doorRight, 0F, 0F, 0F);
        leftHandle = new ModelRenderer(this, 0, 44);
        leftHandle.mirror = true;
        leftHandle.addBox(-5F, -1.5F, -1F, 1, 3, 1);
        leftHandle.setRotationPoint(6F, 17F, -6.8F);
        leftHandle.setTextureSize(64, 64);
        setRotation(leftHandle, 0F, 0F, 0F);
        rightHandle = new ModelRenderer(this, 0, 44);
        rightHandle.addBox(4F, -1.5F, -1F, 1, 3, 1);
        rightHandle.setRotationPoint(-6F, 17F, -6.8F);
        rightHandle.setTextureSize(64, 64);
        setRotation(rightHandle, 0F, 0F, 0F);
    }

    public void renderAll()
    {
        bottomPlate.render(0.0625F);
        topPlate.render(0.0625F);
        rightPlate.render(0.0625F);
        leftPlate.render(0.0625F);
        backPlate.render(0.0625F);
        doorLeft.render(0.0625F);
        doorRight.render(0.0625F);
        leftHandle.render(0.0625F);
        rightHandle.render(0.0625F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}