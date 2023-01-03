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
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mod implements ModInitializer {
  public static final Logger LOGGER = LoggerFactory.getLogger("net-bingecraft-wannabe");
  public static final String NAMESPACE = "wannabe";

  public static final class Items {
    public static final Item INGOT = new Item(new FabricItemSettings());
    public static final Item INFINITY_CRATE = new BlockItem(Blocks.INFINITY_CRATE, new FabricItemSettings());
  }

  public static final class Blocks {
    public static final Block INFINITY_CRATE = new InfinityCrate(FabricBlockSettings.of(Material.METAL).strength(0.5f));
  }

  public static class ScreenHandlers {
    public static final ScreenHandlerType<InfinityCrate.GuiDescription> INFINITY_CRATE = new ScreenHandlerType<>(InfinityCrate.GuiDescription::new);
  }

  @Override
  public void onInitialize() {
    Registry.register(Registries.ITEM, new Identifier(NAMESPACE, "ingot"), Items.INGOT);
    Registry.register(Registries.BLOCK, new Identifier(NAMESPACE, "infinity_crate"), Blocks.INFINITY_CRATE);
    Registry.register(Registries.ITEM, new Identifier(NAMESPACE, "infinity_crate"), Items.INFINITY_CRATE);
    Registry.register(Registries.SCREEN_HANDLER, new Identifier(NAMESPACE, "infinity_crate"), ScreenHandlers.INFINITY_CRATE);
  }
}
