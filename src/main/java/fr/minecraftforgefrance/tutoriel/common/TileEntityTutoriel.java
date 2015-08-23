package fr.minecraftforgefrance.tutoriel.common;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.Constants;

public class TileEntityTutoriel extends TileEntity implements IInventory
{
    private byte direction;
    private ItemStack[] contents = new ItemStack[27];
    private String customName;
    private boolean cobbleOnly;
    public float lidAngle;
    public float prevLidAngle;
    public int numPlayersUsing;
    private int ticksSinceSync;

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.direction = compound.getByte("Direction");
        this.cobbleOnly = compound.getBoolean("Cobble");

        if(compound.hasKey("CustomName", Constants.NBT.TAG_STRING))
        {
            this.customName = compound.getString("CustomName");
        }

        NBTTagList nbttaglist = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        this.contents = new ItemStack[this.getSizeInventory()];
        for(int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if(j >= 0 && j < this.contents.length)
            {
                this.contents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setByte("Direction", this.direction);
        compound.setBoolean("Cobble", this.cobbleOnly);
        if(this.hasCustomInventoryName())
        {
            compound.setString("CustomName", this.customName);
        }

        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < this.contents.length; ++i)
        {
            if(this.contents[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.contents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        compound.setTag("Items", nbttaglist);
    }

    public byte getDirection()
    {
        return direction;
    }

    public void setDirection(byte direction)
    {
        this.direction = direction;
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbttagcompound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        this.readFromNBT(pkt.func_148857_g());
        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
    }

    @Override
    public int getSizeInventory()
    {
        return this.contents.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return this.contents[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int amount)
    {
        if(this.contents[slotIndex] != null)
        {
            ItemStack itemstack;

            if(this.contents[slotIndex].stackSize <= amount)
            {
                itemstack = this.contents[slotIndex];
                this.contents[slotIndex] = null;
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = this.contents[slotIndex].splitStack(amount);

                if(this.contents[slotIndex].stackSize == 0)
                {
                    this.contents[slotIndex] = null;
                }
                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        if(this.contents[slotIndex] != null)
        {
            ItemStack itemstack = this.contents[slotIndex];
            this.contents[slotIndex] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack stack)
    {
        this.contents[slotIndex] = stack;

        if(stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

    @Override
    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.customName : "tile.cupboard";
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory()
    {
        if(this.numPlayersUsing < 0)
        {
            this.numPlayersUsing = 0;
        }

        ++this.numPlayersUsing;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numPlayersUsing);
        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType());
    }

    @Override
    public void closeInventory()
    {
        --this.numPlayersUsing;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numPlayersUsing);
        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType());
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack stack)
    {
        return true;
    }

    @Override
    public void updateEntity()
    {
        ++this.ticksSinceSync;
        float f;

        if(!this.worldObj.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0)
        {
            this.numPlayersUsing = 0;
            f = 5.0F;
            List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)((float)this.xCoord - f), (double)((float)this.yCoord - f), (double)((float)this.zCoord - f), (double)((float)(this.xCoord + 1) + f), (double)((float)(this.yCoord + 1) + f), (double)((float)(this.zCoord + 1) + f)));
            Iterator iterator = list.iterator();

            while(iterator.hasNext())
            {
                EntityPlayer entityplayer = (EntityPlayer)iterator.next();

                if(entityplayer.openContainer instanceof ContainerCupboard)
                {
                    IInventory iinventory = ((ContainerCupboard)entityplayer.openContainer).getTileTuto();

                    if(iinventory == this)
                    {
                        ++this.numPlayersUsing;
                    }
                }
            }
        }

        this.prevLidAngle = this.lidAngle;
        f = 0.1F;

        if(this.numPlayersUsing > 0 && this.lidAngle == 0.0F)
        {
            this.worldObj.playSoundEffect(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if(this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F)
        {
            float f1 = this.lidAngle;

            if(this.numPlayersUsing > 0)
            {
                this.lidAngle += f;
            }
            else
            {
                this.lidAngle -= f;
            }

            if(this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            float f2 = 0.5F;

            if(this.lidAngle < f2 && f1 >= f2)
            {
                this.worldObj.playSoundEffect(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if(this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }
    }

    @Override
    public boolean receiveClientEvent(int id, int value)
    {
        if(id == 1)
        {
            this.numPlayersUsing = value;
            return true;
        }
        return super.receiveClientEvent(id, value);
    }

    public boolean isCobbleOnly()
    {
        return cobbleOnly;
    }

    public void setCobbleOnly(boolean cobbleOnly)
    {
        this.cobbleOnly = cobbleOnly;
    }
}