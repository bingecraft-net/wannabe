package net.bingecraft.wannabe;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IngotSource extends Block {
  public IngotSource(Settings settings) {
    super(settings);
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    if (!world.isClient) {
      player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
    }
    return ActionResult.SUCCESS;
  }

  @Override
  public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
    return new SimpleNamedScreenHandlerFactory(
      (syncId, inv, player) -> new GuiDescription(syncId, inv),
      Text.translatable(getTranslationKey())
    );
  }

  public static class GuiDescription extends io.github.cottonmc.cotton.gui.SyncedGuiDescription {
    public GuiDescription(int syncId, PlayerInventory playerInventory) {
      super(Mod.ScreenHandlers.INGOT_SOURCE, syncId, playerInventory);

      WGridPanel wGridPanel = new WGridPanel();
      setRootPanel(wGridPanel);
      wGridPanel.setInsets(Insets.ROOT_PANEL);

      WItemSlot wItemSlot = WItemSlot.of(InfiniteInventory.of(new ItemStack(Mod.Items.INGOT, 64)), 0);
      wGridPanel.add(wItemSlot, 4, 1);

      wGridPanel.add(createPlayerInventoryPanel(), 0, 3);

      wGridPanel.validate(this);
    }
  }

  public static class Screen extends CottonInventoryScreen<GuiDescription> {
    public Screen(GuiDescription guiDescription, PlayerInventory inventory, Text title) {
      super(guiDescription, inventory, title);
    }
  }
}