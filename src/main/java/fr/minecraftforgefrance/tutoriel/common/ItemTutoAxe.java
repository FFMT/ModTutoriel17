package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTutoAxe extends ItemAxe
{
    protected ItemTutoAxe(ToolMaterial material)
    {
        super(material);
    }

    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        if(repair.getItem() == ModTutoriel.itemTutoriel)
        {
            return true;
        }
        return false;
    }

    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living)
    {
        for(int i = y; i < 256; i++)
        {
            if(world.getBlock(x, i, z).isWood(world, x, i, z))
            {
                stack.damageItem(1, living); // endommage l'item à chaque buche cassée
                if(!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops"))  // si la règle de jeu drop est activé
                {
                    float f = 0.7F;
                    double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                    double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                    double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                    EntityItem entityitem = new EntityItem(world, (double)x + d0, (double)i + d1, (double)z + d2, new ItemStack(world.getBlock(x, i, z), 1, world.getBlockMetadata(x, i, z))); // instancie
 // une
 // nouvelle
 // entité
 // item
 // avec
 // les
 // coordonnées
 // + l'id
 // et le
 // metadata
 // du
 // bois
                    entityitem.delayBeforeCanPickup = 10;
                    world.spawnEntityInWorld(entityitem); // spawn l'entité item
                }
                world.setBlockToAir(x, i, z); // met de l'air à la place du bloc
            }
            else
            {
                return super.onBlockDestroyed(stack, world, block, x, i, z, living);
            }
        }
        return super.onBlockDestroyed(stack, world, block, x, y, z, living);
    }
}