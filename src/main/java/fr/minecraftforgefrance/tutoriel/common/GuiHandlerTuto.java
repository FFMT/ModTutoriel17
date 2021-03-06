package fr.minecraftforgefrance.tutoriel.common;

import cpw.mods.fml.common.network.IGuiHandler;
import fr.minecraftforgefrance.tutoriel.client.GuiCupboard;
import fr.minecraftforgefrance.tutoriel.client.GuiMachineTuto;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandlerTuto implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileEntityTutoriel)
        {
            return new ContainerCupboard((TileEntityTutoriel)tile, player.inventory);
        }
        if(tile instanceof TileEntityMachineTuto)
        {
            return new ContainerMachineTuto((TileEntityMachineTuto)tile, player.inventory);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileEntityTutoriel)
        {
            return new GuiCupboard((TileEntityTutoriel)tile, player.inventory);
        }
        if(tile instanceof TileEntityMachineTuto)
        {
            return new GuiMachineTuto((TileEntityMachineTuto)tile, player.inventory);
        }
        return null;
    }
}