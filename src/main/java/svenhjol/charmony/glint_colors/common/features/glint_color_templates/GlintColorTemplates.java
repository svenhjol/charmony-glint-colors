package svenhjol.charmony.glint_colors.common.features.glint_color_templates;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import svenhjol.charmony.api.core.Configurable;
import svenhjol.charmony.api.core.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.api.core.Side;

@FeatureDefinition(side = Side.Common, description = "Smithing template that changes the glint color of any enchanted item.")
@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public final class GlintColorTemplates extends SidedFeature {
    public final Registers registers;
    public final Advancements advancements;

    @Configurable(
        name = "Loot table",
        description = "Loot table in which a colored glint smithing template will be added."
    )
    private static String lootTable = "minecraft:chests/ancient_city";

    @Configurable(
        name = "Loot chance",
        description = "Chance (out of 1.0) of a colored glint smithing template appearing in loot."
    )
    private static double lootChance = 0.2d;

    @Configurable(
        name = "Allow unenchanted enchantable items",
        description = """
            If true, a template can be applied to an item that supports enchantments but does not yet have an enchantment.
            If false, a template can only be applied to an item that has an enchantment.""",
        requireRestart = false
    )
    private static boolean allowUnenchantedItems = false;

    public GlintColorTemplates(Mod mod) {
        super(mod);
        registers = new Registers(this);
        advancements = new Advancements(this);
    }

    public static GlintColorTemplates feature() {
        return Mod.getSidedFeature(GlintColorTemplates.class);
    }

    public ResourceLocation lootTable() {
        return ResourceLocation.tryParse(lootTable);
    }

    public double lootChance() {
        return Mth.clamp(lootChance, 0.0d, 1.0d);
    }

    public boolean allowUnenchantedItems() {
        return allowUnenchantedItems;
    }
}
