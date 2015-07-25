package fr.minecraftforgefrance.tutoriel.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@SuppressWarnings("rawtypes")
public class MachineTutoRecipes
{

    private static final MachineTutoRecipes smeltingBase = new MachineTutoRecipes(); // Permet d'instancier votre classe car vous le l'instancierez nul part ailleur
    private Map smeltingList = new HashMap(); // Ceci permet de mettre vos recettes

    public MachineTutoRecipes()
    {
        this.addRecipe(Items.apple, Items.apple, Items.arrow, new ItemStack(Blocks.diamond_block)); // Ajout d'une recette, on fait un bloc de diamant � partie de deux pommes et une fl�che
    }

    @SuppressWarnings("unchecked")
    public void addRecipe(ItemStack stack1, ItemStack stack2, ItemStack stack3, ItemStack stack4) // Cette fonction de comprend que des ItemStack, c'est celle qui ajoute les recettes � la HashMap
    {
        ItemStack[] stackList = new ItemStack[] {stack1, stack2, stack3};
        this.smeltingList.put(stackList, stack4);
    }

    public void addRecipe(Item item1, Item item2, Item item3, ItemStack stack) // 1er cas
    {
        this.addRecipe(new ItemStack(item1), new ItemStack(item2), new ItemStack(item3), stack);
    }

    public void addRecipe(Block block1, Item item2, Item item3, ItemStack stack) // 2nd cas
    {
        this.addRecipe(Item.getItemFromBlock(block1), item2, item3, stack);
    }

    public void addRecipe(Block block1, Block block2, Item item3, ItemStack stack) // 3�me cas
    {
        this.addRecipe(Item.getItemFromBlock(block1), Item.getItemFromBlock(block2), item3, stack);
    }

    public void addRecipe(Block block1, Block block2, Block block3, ItemStack stack) // 4�me cas
    {
        this.addRecipe(Item.getItemFromBlock(block1), Item.getItemFromBlock(block2), Item.getItemFromBlock(block3), stack);
    }

    public ItemStack getSmeltingResult(ItemStack[] stack) // En argument : un tableau avec le contenu des trois slots d'input
    {
        Iterator iterator = this.smeltingList.entrySet().iterator();
        Entry entry;

        do
        {
            if(!iterator.hasNext())  // Si il n'y a plus de recettes dans la liste
            {
                return null; // Il n'y a pas de recette correspondante
            }
            entry = (Entry)iterator.next(); // prend la recette suivante
        }
        while(!this.isSameKey(stack, (ItemStack[])entry.getKey())); // Check si le tableau pass� en argument correspond � celui de la recette, vous avez une erreur ici, on cr�e la fonction tout de
                                                                    // suite.

        return (ItemStack)entry.getValue(); // retourne l'itemstack : resultat de la recette
    }

    private boolean isSameKey(ItemStack[] stackList, ItemStack[] stackList2)
    {
        boolean isSame = false; // Au d�but ce n'est pas la m�me
        for(int i = 0; i <= 2; i++) // Pour les 3 items
        {
            if(stackList[i].getItem() == stackList2[i].getItem())  // On v�rifie si ce sont les m�me
            {
                isSame = true; // Si c'est le cas alors isSame vaut true
            }
            else
            {
                return false; // Si un seul n'est pas bon, on cherche pas, c'est pas la bonne recette
            }
        }
        return isSame;
    }

    public Map getSmeltingList()
    {
        return this.smeltingList;
    }

    public static MachineTutoRecipes smelting()
    {
        return smeltingBase;
    }

}
