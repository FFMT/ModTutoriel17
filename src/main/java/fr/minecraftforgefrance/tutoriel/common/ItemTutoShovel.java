package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class ItemTutoShovel extends ItemSpade
{
    public ItemTutoShovel(ToolMaterial material)
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
}