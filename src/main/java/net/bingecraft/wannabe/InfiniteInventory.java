package net.bingecraft.wannabe;

import net.minecraft.item.ItemStack;

class InfiniteInventory {
  public static PermissiveSidedInventory of(ItemStack itemStack) {
    PermissiveSidedInventory inventory = new PermissiveSidedInventory(itemStack.copy());
    inventory.addListener(event -> {
      if (event.count(itemStack.getItem()) == 0)
        event.setStack(0, itemStack.copy());
    });
    return inventory;
  }
}
