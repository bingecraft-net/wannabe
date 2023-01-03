package net.bingecraft.wannabe;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;

class InfiniteInventory {
  public static SimpleInventory of(ItemStack itemStack) {
    SimpleInventory inventory = new SimpleInventory(itemStack.copy());
    inventory.addListener(event -> {
      if (event.count(itemStack.getItem()) == 0)
        event.setStack(0, itemStack.copy());
    });
    return inventory;
  }
}
