package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTutoriel extends Block
{
	private IIcon crea;

	protected BlockTutoriel(Material material)
	{
		super(material);
	}

	public void registerBlockIcons(IIconRegister iiconRegister)
	{
		this.blockIcon = iiconRegister.registerIcon(ModTutoriel.MODID + ":inv");
		this.crea = iiconRegister.registerIcon(ModTutoriel.MODID + ":yellow");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		if(FMLClientHandler.instance().getClientPlayerEntity().capabilities.isCreativeMode)
		{
			return this.crea;
		}
		return this.blockIcon;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 1;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}
}