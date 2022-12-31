package net.bingecraft.wannabe;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mod implements ModInitializer {
  public static final Logger LOGGER = LoggerFactory.getLogger("net-bingecraft-wannabe");
  public static final Item wannabeIngot = Registry.register(
    Registries.ITEM,
    new Identifier("wannabe", "ingot"),
    new Item(new FabricItemSettings())
  );

  @Override
  public void onInitialize() {
    LOGGER.info("Initialized Wannabe");
  }
}
