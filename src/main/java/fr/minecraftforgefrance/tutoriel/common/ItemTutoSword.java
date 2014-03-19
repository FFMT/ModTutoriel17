package fr.minecraftforgefrance.tutoriel.common;

import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class ItemTutoSword extends ItemSword
{
	public ItemTutoSword(ToolMaterial material)
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

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(player.isSneaking())
		{
			if(!stack.hasTagCompound())
			{
				stack.setTagCompound(new NBTTagCompound());
			}
			byte mode = stack.getTagCompound().getByte("mode");
			mode++;
			if(mode == 3)
			{
				mode = 0;
			}
			stack.getTagCompound().setByte("mode", mode);
			if(!world.isRemote)
			{
				player.addChatMessage(new ChatComponentTranslation("sword.mode.message." + mode));
			}
		}
		else
		{
			super.onItemRightClick(stack, world, player);
		}
		return stack;
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase attackedLiving, EntityLivingBase attackerLiving)
	{
		if(!stack.hasTagCompound()) // Si le stack n'a pas de tag NBT
		{
			stack.setTagCompound(new NBTTagCompound()); // je lui en ajoute un, sinon il y a un risque de NullPointerException
		}

		IEntitySelector filter; // Je déclare un filter, il est null pour l'instant
		if(stack.getTagCompound().getByte("mode") == 0) // si le mode est 0
		{
			filter = new IEntitySelector()
			{
				@Override
				public boolean isEntityApplicable(Entity entity)
				{
					if(entity instanceof EntityPlayer) // mon sélecteur prend tout les joueurs
					{
						return true;
					}
					return false;
				}
			};
		}
		else if(stack.getTagCompound().getByte("mode") == 1) // si le mode est 1
		{
			filter = new IEntitySelector()
			{
				@Override
				public boolean isEntityApplicable(Entity entity)
				{
					if(entity instanceof EntityMob)// mon sélecteur prend tout les monstres
					{
						return true;
					}
					return false;
				}
			};
		}
		else
		// sinon, le mode est forcement 3 (pas de else if ici, sinon la JVM va croire que filter peut être null, est donc vous allez avoir une erreur plus bas
		{
			filter = new IEntitySelector()
			{
				@Override
				public boolean isEntityApplicable(Entity entity)
				{
					if(entity instanceof EntityAnimal) // mon sélecteur prend tout les animaux
					{
						return true;
					}
					return false;
				}
			};
		}
		List entityTargetList = attackedLiving.worldObj.selectEntitiesWithinAABB(EntityLivingBase.class, attackedLiving.boundingBox.expand(8.0D, 2.0D, 8.0D), filter); // j'obtiens la liste de toute les entités vivantes sur un rayon de 8 en fonction du filtre
		for(Object entity : entityTargetList)
		{
			EntityLivingBase living = (EntityLivingBase)entity; // Il faut donc cast EntityLivingBase à l'objet pour utiliser les méthodes qui sont dans EntityLivingBase
			if(!living.equals(attackerLiving)) // Vérifie que l'entité n'est pas celui qui a donné le coup
			{
				living.setFire(4); // Mets feu à l'entité pendant 4 secondes
			}
		}
		return super.hitEntity(stack, attackedLiving, attackerLiving); // Execute le code dans la fonction hitEntity de la classe mère (endommage l'épée)
	}
}