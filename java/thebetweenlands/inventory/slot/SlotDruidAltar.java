package thebetweenlands.inventory.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thebetweenlands.items.SwampTalisman;
import thebetweenlands.items.SwampTalisman.TALISMAN;

public class SlotDruidAltar extends Slot {

	public SlotDruidAltar(IInventory inventory, int slotIndex, int x, int y) {
		super(inventory, slotIndex, x, y);
		slotNumber = slotIndex;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		if(slotNumber == 0 && stack.getItem() instanceof SwampTalisman && stack.getItemDamage() == TALISMAN.swampTalisman.ordinal())
			return true;
		if(slotNumber > 0 && slotNumber <= 4 && stack.getItem() instanceof SwampTalisman && stack.getItemDamage() != TALISMAN.swampTalisman.ordinal())
			return true;	
		return false;
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
		if (stack != null && slotNumber == 0)
			player.worldObj.playSoundAtEntity(player, "thebetweenlands:druidchant", 1.0F, 1.0F);

		super.onPickupFromSlot(player, stack);
	}
}