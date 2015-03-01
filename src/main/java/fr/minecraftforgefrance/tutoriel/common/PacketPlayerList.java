package fr.minecraftforgefrance.tutoriel.common;

import fr.minecraftforgefrance.tutoriel.client.GuiPlayerList;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketPlayerList implements IMessage
{
    String[] playerList;
    public PacketPlayerList()
    {
        
    }
    
    public PacketPlayerList(String[] playerList)
    {
        this.playerList = playerList;
    }
    
    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.playerList = new String[buf.readInt()];
        for(int i = 0; i < playerList.length; i++)
        {
            this.playerList[i] = ByteBufUtils.readUTF8String(buf);
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.playerList.length);
        for(String name : this.playerList)
        {
            ByteBufUtils.writeUTF8String(buf, name);
        }
    }
    
    public static class Handler implements IMessageHandler<PacketPlayerList, IMessage>
    {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketPlayerList message, MessageContext ctx)
        {
            Minecraft.getMinecraft().displayGuiScreen(new GuiPlayerList(message.playerList));
            return null;
        }
    }
}
