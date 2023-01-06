package net.bingecraft.wannabe;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ClientMod implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    HandledScreens.register(Mod.ScreenHandlerTypes.INFINITY_CRATE, InfinityCrate.Screen::new);
    HandledScreens.<Lug.LugGuiDescription, Lug.LugScreen>register(Mod.ScreenHandlerTypes.LUG_GUI_DESCRIPTION, Lug.LugScreen::new);
  }
}
