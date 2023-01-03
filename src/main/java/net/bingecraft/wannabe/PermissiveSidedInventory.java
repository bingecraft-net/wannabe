package net.bingecraft.wannabe;

import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

class PermissiveSidedInventory extends SimpleInventory implements SidedInventory {
  public PermissiveSidedInventory(ItemStack itemStack) {
    super(itemStack);
  }

  @Override
  public int[] getAvailableSlots(Direction side) {
    int[] slots = new int[size()];
    for (int i = 0; i < size(); i++) {
      slots[i] = i;
    }
    return slots;
  }

  @Override
  public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
    return false;
  }

  @Override
  public boolean canExtract(int slot, ItemStack stack, Direction dir) {
    return true;
  }
}
