package fr.minecraftforgefrance.tutoriel.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fr.minecraftforgefrance.tutoriel.common.ContainerMachineTuto;
import fr.minecraftforgefrance.tutoriel.common.ModTutoriel;
import fr.minecraftforgefrance.tutoriel.common.TileEntityMachineTuto;
	
	public class GuiMachineTuto extends GuiContainer {

		private static final ResourceLocation texture = new ResourceLocation(ModTutoriel.MODID,"textures/gui/container/guiMachineTuto.png");
	    @SuppressWarnings("unused")
		private TileEntityMachineTuto tileCompressor;
	    private IInventory playerInv;
		
		public GuiMachineTuto(TileEntityMachineTuto tile, InventoryPlayer inventory) 
		{
			super(new ContainerMachineTuto(tile, inventory));
	        this.tileCompressor = tile;
	        this.playerInv = inventory;
	        this.allowUserInput = false;
	        this.ySize = 256;
	        this.xSize = 256;
		}

		@Override
		protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y) 
		{
			
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        this.mc.getTextureManager().bindTexture(texture);
	        int k = (this.width - this.xSize) / 2;
	        int l = (this.height - this.ySize) / 2;
	        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	        this.drawTexturedModalRect(0, 0, 176, 14, 100 + 1, 16);
				
		}
		
		protected void drawGuiContainerForegroundLayer(int x, int y)
	    {
	        this.fontRendererObj.drawString(this.playerInv.hasCustomInventoryName() ? this.playerInv.getInventoryName() : I18n.format(this.playerInv.getInventoryName()), 10, this.ySize - 98, 4210752);
	    }

	}
