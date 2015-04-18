package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemTutoriel extends Item
{
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote)
        {
            player.addChatMessage(new ChatComponentText("serveur : side " + side));
            player.addChatMessage(new ChatComponentText("serveur : metadata " + world.getBlockMetadata(x, y, z)));
            TileEntity tile = world.getTileEntity(x, y, z);
            if(tile instanceof TileEntityDirectional)
            {
                player.addChatMessage(new ChatComponentText("serveur : direction " + ((TileEntityDirectional)tile).getDirection()));
            }
        }
        else
        {
            player.addChatMessage(new ChatComponentText("client : side " + side));
            player.addChatMessage(new ChatComponentText("client : metadata " + world.getBlockMetadata(x, y, z)));
            TileEntity tile = world.getTileEntity(x, y, z);
            if(tile instanceof TileEntityDirectional)
            {
                player.addChatMessage(new ChatComponentText("client : direction " + ((TileEntityDirectional)tile).getDirection()));
            }
        }
        if(world.getBlock(x, y, z).rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side)))
        {
            return true;
        }
        return false;
    }
}