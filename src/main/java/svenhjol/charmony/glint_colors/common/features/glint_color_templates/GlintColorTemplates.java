package svenhjol.charmony.glint_colors.common.features.glint_color_templates;

import net.minecraft.util.Mth;
import svenhjol.charmony.api.core.Configurable;
import svenhjol.charmony.api.core.FeatureDefinition;
import svenhjol.charmony.api.core.Side;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;

@FeatureDefinition(side = Side.Common, description = "Smithing template that changes the glint color of any enchanted item.")
@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public final class GlintColorTemplates extends SidedFeature {
    public final Registers registers;
    public final Advancements advancements;

    @Configurable(
        name = "Ancient city loot chance",
        description = """
            Chance (out of 1.0) of a colored glint smithing template appearing in ancient city loot.
            A value of zero prevents the template from being added."""
    )
    private static double ancientCityLootChance = 0.2d;

    @Configurable(
        name = "Stronghold loot chance",
        description = """
            Chance (out of 1.0) of a colored glint smithing template appearing in stronghold library loot.
            A value of zero prevents the template from being added."""
    )
    private static double strongholdLootChance = 0.0d;

    @Configurable(
        name = "Dungeon loot chance",
        description = """
            Chance (out of 1.0) of a colored glint smithing template appearing in dungeon loot.
            A value of zero prevents the template from being added."""
    )
    private static double dungeonLootChance = 0.0d;

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

    public double ancientCityLootChance() {
        return Mth.clamp(ancientCityLootChance, 0.0d, 1.0d);
    }

    public double strongholdLootChance() {
        return Mth.clamp(strongholdLootChance, 0.0d, 1.0d);
    }

    public double dungeonLootChance() {
        return Mth.clamp(dungeonLootChance, 0.0d, 1.0d);
    }

    public boolean allowUnenchantedItems() {
        return allowUnenchantedItems;
    }
}
