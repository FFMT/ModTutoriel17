package fr.minecraftforgefrance.tutoriel.client;

import net.minecraft.client.gui.GuiScreen;

public class GuiPlayerList extends GuiScreen
{
    private String[] playerList;

    public GuiPlayerList(String[] playerList)
    {
        this.playerList = playerList;
    }

    public void drawScreen(int x, int y, float tick)
    {
        super.drawScreen(x, y, tick);

        for(int i = 0; i < playerList.length;i++)
        {
            System.out.println(playerList[i]);
            this.fontRendererObj.drawString(playerList[i], 0, 20 + i * 10, 0xFFFFFF);
        }
    }
}