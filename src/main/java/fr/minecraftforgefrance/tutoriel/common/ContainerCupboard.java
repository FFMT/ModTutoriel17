package fr.minecraftforgefrance.tutoriel.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerCupboard extends Container
{
    private final TileEntityTutoriel tileTuto;

    public ContainerCupboard(TileEntityTutoriel tile, InventoryPlayer inventory)
    {
        this.tileTuto = tile;
        tile.openInventory();
        for(int i = 0; i < 3; ++i)
        {
            for(int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(tile, j + i * 9, 8 + j * 18, 18 + i * 18)
                {
                    public boolean isItemValid(ItemStack stack)
                    {
                        return tileTuto.isCobbleOnly() ? stack.getItem() == Item.getItemFromBlock(Blocks.cobblestone) : true;
                    }
                });
            }
        }
        this.bindPlayerInventory(inventory);
    }

    private void bindPlayerInventory(InventoryPlayer inventory)
    {
        int i;
        for(i = 0; i < 3; ++i)
        {
            for(int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
            }
        }

        for(i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 144));
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);

        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if(this.tileTuto.isCobbleOnly() && itemstack.getItem() != Item.getItemFromBlock(Blocks.cobblestone))
            {
                return null;
            }

            if(slotIndex < this.tileTuto.getSizeInventory())
            {
                if(!this.mergeItemStack(itemstack1, this.tileTuto.getSizeInventory(), this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if(!this.mergeItemStack(itemstack1, 0, this.tileTuto.getSizeInventory(), false))
            {
                return null;
            }

            if(itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tileTuto.isUseableByPlayer(player);
    }

    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);
        this.tileTuto.closeInventory();
    }

    public TileEntityTutoriel getTileTuto()
    {
        return tileTuto;
    }
}