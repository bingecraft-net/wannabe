package net.bingecraft.wannabe;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mod implements ModInitializer {
  public static final Logger LOGGER = LoggerFactory.getLogger("net-bingecraft-wannabe");
  public static final String NAMESPACE = "wannabe";
  public static final Item ingot = new Item(new FabricItemSettings());
  public static final Block ingotSource = new IngotSource(FabricBlockSettings.of(Material.METAL).strength(0.5f));
  public static final Item ingotSourceItem = new BlockItem(ingotSource, new FabricItemSettings());

  @Override
  public void onInitialize() {
    Registry.register(Registries.ITEM, new Identifier(NAMESPACE, "ingot"), ingot);
    Registry.register(Registries.BLOCK, new Identifier(NAMESPACE, "ingot_source"), ingotSource);
    Registry.register(Registries.ITEM, new Identifier(NAMESPACE, "ingot_source"), ingotSourceItem);
    LOGGER.info("Initialized Wannabe");
  }
}
