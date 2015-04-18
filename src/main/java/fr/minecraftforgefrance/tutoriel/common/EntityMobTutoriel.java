package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityMobTutoriel extends EntityMob
{
    public EntityMobTutoriel(World world)
    {
        super(world);
    }

    public void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.699999988079071D);
    }

    public Item getDropItem()
    {
        return Item.getItemFromBlock(ModTutoriel.blockTutoriel);
    }
}