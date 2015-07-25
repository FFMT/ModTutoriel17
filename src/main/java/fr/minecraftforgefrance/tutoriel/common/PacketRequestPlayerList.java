package fr.minecraftforgefrance.tutoriel.common;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;

public class PacketRequestPlayerList implements IMessage
{
    public PacketRequestPlayerList()
    {

    }

    @Override
    public void fromBytes(ByteBuf buf)
    {

    }

    @Override
    public void toBytes(ByteBuf buf)
    {

    }

    public static class Handler implements IMessageHandler<PacketRequestPlayerList, PacketPlayerList>
    {
        @Override
        public PacketPlayerList onMessage(PacketRequestPlayerList message, MessageContext ctx)
        {
            return new PacketPlayerList(MinecraftServer.getServer().getAllUsernames());
        }
    }
}