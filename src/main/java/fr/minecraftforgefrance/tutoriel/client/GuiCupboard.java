package fr.minecraftforgefrance.tutoriel.client;

import org.lwjgl.opengl.GL11;

import fr.minecraftforgefrance.tutoriel.common.ContainerCupboard;
import fr.minecraftforgefrance.tutoriel.common.ModTutoriel;
import fr.minecraftforgefrance.tutoriel.common.PacketCobbleOnly;
import fr.minecraftforgefrance.tutoriel.common.TileEntityTutoriel;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiCupboard extends GuiContainer
{
    private static final ResourceLocation textures = new ResourceLocation(ModTutoriel.MODID, "textures/gui/container/cupboard.png");
    private TileEntityTutoriel tileTuto;
    private IInventory playerInv;

    public GuiCupboard(TileEntityTutoriel tile, InventoryPlayer inventory)
    {
        super(new ContainerCupboard(tile, inventory));
        this.tileTuto = tile;
        this.playerInv = inventory;
        this.allowUserInput = false;
        this.ySize = 170;
    }

    public void initGui()
    {
        super.initGui();
        this.buttonList.add(new GuiButton(0, this.guiLeft, this.guiTop, 50, 20, "Cobble"));
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if(button.id == 0)
        {
            ModTutoriel.network.sendToServer(new PacketCobbleOnly(!this.tileTuto.isCobbleOnly()));
        }
    }

    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String tileName = this.tileTuto.hasCustomInventoryName() ? this.tileTuto.getInventoryName() : I18n.format(this.tileTuto.getInventoryName()) + " : " + this.tileTuto.isCobbleOnly();
        this.fontRendererObj.drawString(tileName, this.xSize / 2 - this.fontRendererObj.getStringWidth(tileName) / 2, 6, 0);
        String invName = this.playerInv.hasCustomInventoryName() ? this.playerInv.getInventoryName() : I18n.format(this.playerInv.getInventoryName());
        this.fontRendererObj.drawString(invName, (this.xSize - this.fontRendererObj.getStringWidth(invName)) / 2, this.ySize - 96, 0);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(textures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}