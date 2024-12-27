package svenhjol.charmony.glint_colors.common.features.glint_color_templates;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.common.CommonRegistry;
import svenhjol.charmony.core.events.SmithingTableEvents;
import svenhjol.charmony.core.events.SmithingTableEvents.SmithingTableInstance;
import svenhjol.charmony.glint_colors.GlintColorsMod;
import svenhjol.charmony.glint_colors.common.features.glint_colors.GlintColors;
import svenhjol.charmony.glint_colors.common.features.glint_colors.Tags;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class Registers extends Setup<GlintColorTemplates> {
    public final List<ResourceLocation> emptyDyes = new ArrayList<>();
    public final Supplier<TemplateItem> item;
    public final Supplier<ResourceKey<LootTable>> lootTable;

    public static final String ITEM_ID = "glint_color_template";

    public Registers(GlintColorTemplates feature) {
        super(feature);
        var registry = CommonRegistry.forFeature(feature);

        emptyDyes.addAll(List.of(
            GlintColorsMod.id("container/slot/empty_dye_01"),
            GlintColorsMod.id("container/slot/empty_dye_02"),
            GlintColorsMod.id("container/slot/empty_dye_03"),
            GlintColorsMod.id("container/slot/empty_dye_04")
        ));

        item = registry.item(ITEM_ID, TemplateItem::new);
        lootTable = () -> ResourceKey.create(Registries.LOOT_TABLE, feature().lootTable());
    }

    @Override
    public Runnable boot() {
        return () -> {
            SmithingTableEvents.CALCULATE_OUTPUT.handle(this::handleCalculateOutput);
            SmithingTableEvents.CAN_TAKE.handle(this::handleCanTake);
            SmithingTableEvents.ON_TAKE.handle(this::handleOnTake);
            LootTableEvents.MODIFY.register(this::handleLootTableModify);
        };
    }

    private void handleLootTableModify(ResourceKey<LootTable> key, LootTable.Builder builder, net.fabricmc.fabric.api.loot.v3.LootTableSource source, HolderLookup.Provider provider) {
        if (source.isBuiltin() && key == lootTable.get()) {
            var pool = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .when(LootItemRandomChanceCondition.randomChance((float)feature().lootChance()))
                .add(LootItem.lootTableItem(item.get()).setWeight(1));
            builder.pool(pool.build());
        }
    }

    private InteractionResult handleCanTake(SmithingTableInstance instance, Player player) {
        if (!instance.output.isEmpty()) {
            var out = instance.output.getItem(0);
            if (GlintColors.feature().handlers.has(out)) {
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    private boolean handleCalculateOutput(SmithingTableInstance instance) {
        var input = instance.input;
        var slot0 = input.getItem(0);
        var slot1 = input.getItem(1);
        var slot2 = input.getItem(2);

        if (slot0.is(item.get())) {
            if (!slot1.isEnchanted() || !slot2.is(Tags.COLORED_DYES)) {
                instance.output.setItem(0, ItemStack.EMPTY);
                return true;
            }

            var dyeColor = ((DyeItem)slot2.getItem()).getDyeColor();
            var itemToChange = slot1.copy();

            GlintColors.feature().handlers.apply(itemToChange, dyeColor);
            instance.output.setItem(0, itemToChange);
            return true;
        }

        return false;
    }

    /**
     * We just hook in here to trigger the advancement.
     */
    private boolean handleOnTake(SmithingTableInstance instance, Player player, ItemStack stack) {
        if (GlintColors.feature().handlers.has(stack)) {
            feature().advancements.appliedGlintColorTemplate(player);
        }
        return false;
    }
}
