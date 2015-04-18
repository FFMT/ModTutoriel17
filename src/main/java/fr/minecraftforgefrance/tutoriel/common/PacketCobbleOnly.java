package fr.minecraftforgefrance.tutoriel.common;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketCobbleOnly implements IMessage
{
    private boolean cobbleOnly;
    
    public PacketCobbleOnly()
    {

    }
    
    public PacketCobbleOnly(boolean cobbleOnly)
    {
        this.cobbleOnly = cobbleOnly;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.cobbleOnly = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeBoolean(this.cobbleOnly);
    }

    public static class Handler implements IMessageHandler<PacketCobbleOnly, IMessage>
    {
        @Override
        public IMessage onMessage(PacketCobbleOnly message, MessageContext ctx)
        {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            Container c = player.openContainer;
            if(c instanceof ContainerCupboard)
            {
                TileEntityTutoriel tile = ((ContainerCupboard)c).getTileTuto();
                tile.setCobbleOnly(message.cobbleOnly);
                player.worldObj.markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
            }
            return null;
        }
    }
}