package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class BlockTutoriel2 extends Block
{
	protected BlockTutoriel2(Material material)
	{
		super(material);
	}

	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityTutoriel();
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{
			TileEntity tile = world.getTileEntity(x, y, z);
			if(tile instanceof TileEntityTutoriel)
			{
				TileEntityTutoriel tileTuto = (TileEntityTutoriel)tile;
				if(side == 0)
				{
					tileTuto.decrease();
				}
				else if(side == 1)
				{
					tileTuto.increase();
				}
				player.addChatMessage(new ChatComponentTranslation("tile.tutoriel2.number", tileTuto.getNumber()));
				return true;
			}
		}
		return false;
	}
}