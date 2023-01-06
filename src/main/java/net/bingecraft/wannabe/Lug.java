package net.bingecraft.wannabe;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class Lug extends BlockWithEntity {
  protected Lug(Settings settings) {
    super(settings);
  }

  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new LugBlockEntity(pos, state);
  }

  public static class LugBlockEntity extends BlockEntity implements InventoryProvider {
    private final PermissiveSidedInventory inventory = new PermissiveSidedInventory(ItemStack.EMPTY);

    public LugBlockEntity(BlockPos pos, BlockState state) {
      super(Mod.BlockEntityTypes.LUG, pos, state);
      inventory.addListener(e -> markDirty());
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
      Inventories.writeNbt(nbt, inventory.stacks);
      super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
      super.readNbt(nbt);
      Inventories.readNbt(nbt, inventory.stacks);
    }

    @Override
    public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos) {
      return inventory;
    }
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    player.openHandledScreen(
      new SimpleNamedScreenHandlerFactory(
        (syncId, inv, ignored) -> new LugGuiDescription(syncId, inv, ScreenHandlerContext.create(world, pos)),
        Text.translatable(getTranslationKey())
      )
    );
    return ActionResult.SUCCESS;
  }

  public static class LugGuiDescription extends SyncedGuiDescription {
    private static final int INVENTORY_SIZE = 1;

    public LugGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
      super(Mod.ScreenHandlerTypes.LUG_GUI_DESCRIPTION, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context));

      WGridPanel root = new WGridPanel();
      setRootPanel(root);
      root.setSize(300, 200);
      root.setInsets(Insets.ROOT_PANEL);

      WItemSlot itemSlot = WItemSlot.of(blockInventory, 0);
      root.add(itemSlot, 4, 1);

      root.add(this.createPlayerInventoryPanel(), 0, 3);

      root.validate(this);
    }
  }

  public static class LugScreen extends CottonInventoryScreen<LugGuiDescription> {
    public LugScreen(LugGuiDescription gui, PlayerInventory inventory, Text title) {
      super(gui, inventory, title);
    }
  }
}
