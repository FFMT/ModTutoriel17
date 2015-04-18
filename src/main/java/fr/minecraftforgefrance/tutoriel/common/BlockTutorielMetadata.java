package fr.minecraftforgefrance.tutoriel.common;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTutorielMetadata extends Block
{
    public static String[] subBlock = new String[] {"block1", "block2", "block3", "block4"};
    private IIcon[][] icons = new IIcon[subBlock.length][3];

    protected BlockTutorielMetadata()
    {
        super(Material.rock);
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return metadata == 0;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return metadata == 0 ? new TileEntityDirectional() : null;
    }

    public int damageDropped(int metadata)
    {
        return metadata;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
        if(stack.getItemDamage() == 0)
        {
            TileEntity tile = world.getTileEntity(x, y, z);
            if(tile instanceof TileEntityDirectional)
            {
                int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
                ((TileEntityDirectional)tile).setDirection((byte)direction);
            }
        }
    }

    public void getSubBlocks(Item item, CreativeTabs tabs, List list)
    {
        for(int i = 0; i < subBlock.length; i++)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public void registerBlockIcons(IIconRegister iconRegister)
    {
        for(int i = 0; i < subBlock.length; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                this.icons[i][j] = iconRegister.registerIcon(ModTutoriel.MODID + ":" + subBlock[i] + "_" + j);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        if(world.getBlockMetadata(x, y, z) == 0)
        {
            if(side == 0 || side == 1)
            {
                return this.icons[0][0];
            }
            TileEntity tile = world.getTileEntity(x, y, z);
            if(tile instanceof TileEntityDirectional)
            {
                byte direction = ((TileEntityDirectional)tile).getDirection();
                return side == 3 && direction == 0 ? this.icons[0][1] : (side == 4 && direction == 1 ? this.icons[0][1] : (side == 2 && direction == 2 ? this.icons[0][1] : (side == 5 && direction == 3 ? this.icons[0][1] : this.icons[0][2])));
            }
        }
        return this.getIcon(side, world.getBlockMetadata(x, y, z));
    }

    public IIcon getIcon(int side, int metadata)
    {
        if(metadata == 0)
        {
            if(side == 0 || side == 1)
            {
                return this.icons[0][0];
            }
            else if(side == 3)
            {
                return this.icons[0][1];
            }
            else
            {
                return this.icons[0][2];
            }
        }
        else if(side > 2)
        {
            side = 2;
        }
        return metadata >= 0 && metadata < subBlock.length ? this.icons[metadata][side] : this.icons[0][0];
    }
    
    @Override
    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis)
    {
        if((axis == ForgeDirection.UP || axis == ForgeDirection.DOWN) && !world.isRemote && world.getBlockMetadata(x, y, z) == 0)
        {
            TileEntity tile = world.getTileEntity(x, y, z);
            if(tile instanceof TileEntityDirectional)
            {
                TileEntityDirectional tileDirectional = (TileEntityDirectional)tile;
                byte direction = tileDirectional.getDirection();
                direction++;
                if(direction > 3)
                {
                    direction = 0;
                }
                tileDirectional.setDirection(direction);
                return true;
            }
        }
        return false;
    }

    public ForgeDirection[] getValidRotations(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) == 0 ? new ForgeDirection[] {ForgeDirection.UP, ForgeDirection.DOWN} : ForgeDirection.VALID_DIRECTIONS;
    }
}