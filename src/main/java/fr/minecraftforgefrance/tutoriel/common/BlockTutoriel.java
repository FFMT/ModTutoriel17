package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.common.util.RotationHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTutoriel extends Block
{
    private IIcon blockIcon2;

    protected BlockTutoriel(Material material)
    {
        super(material);
    }

    public void registerBlockIcons(IIconRegister iiconRegister)
    {
        this.blockIcon = iiconRegister.registerIcon(ModTutoriel.MODID + ":white");
        this.blockIcon2 = iiconRegister.registerIcon(ModTutoriel.MODID + ":yellow");
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
        int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, direction, 2);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return side == 3 && metadata == 0 ? this.blockIcon2 : (side == 4 && metadata == 1 ? this.blockIcon2 : (side == 2 && metadata == 2 ? this.blockIcon2 : (side == 5 && metadata == 3 ? this.blockIcon2 : this.blockIcon)));
    }

    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis)
    {
        if((axis == ForgeDirection.UP || axis == ForgeDirection.DOWN) && !world.isRemote)
        {
            int direction = world.getBlockMetadata(x, y, z) + 1;
            if(direction > 3)
            {
                direction = 0;
            }
            world.setBlockMetadataWithNotify(x, y, z, direction, 3);
            return true;
        }
        return false;
    }
    
    public ForgeDirection[] getValidRotations(World world, int x, int y, int z)
    {
        return new ForgeDirection[] {ForgeDirection.UP, ForgeDirection.DOWN};
    }
}