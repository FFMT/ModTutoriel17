package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlayerEventHandler
{
    @SubscribeEvent
    public void onNameFormat(PlayerEvent.NameFormat event)
    {
        if(event.username.equals("robin4002"))
        {
            event.displayname = "[MFF]robin4002";
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTooltip(ItemTooltipEvent event)
    {
        if(event.itemStack.getItem() == Items.dye && event.itemStack.getItemDamage() == 15)
        {
            event.toolTip.add("clic droit sur les plantes pour les faire pousser");
        }
        else if(event.itemStack.getItem() == Item.getItemFromBlock(Blocks.obsidian))
        {
            if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
            {
                event.toolTip.add("Bloc très résistant aux exposions");
                event.toolTip.add("Résistance : 2000");
            }
            else
            {
                event.toolTip.add("Maintenir shift gauche pour plus d'information");
            }
        }
    }
}