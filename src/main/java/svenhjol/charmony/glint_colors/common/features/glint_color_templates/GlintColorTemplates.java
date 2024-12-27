package svenhjol.charmony.glint_colors.common.features.glint_color_templates;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import svenhjol.charmony.glint_colors.GlintColorsMod;
import svenhjol.charmony.core.annotations.Configurable;
import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

@FeatureDefinition(side = Side.Common, description = "Smithing template that changes the glint color of any enchanted item.")
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

    public GlintColorTemplates(Mod mod) {
        super(mod);
        registers = new Registers(this);
        advancements = new Advancements(this);
    }

    public static GlintColorTemplates feature() {
        return GlintColorsMod.instance().sidedFeature(GlintColorTemplates.class);
    }

    public ResourceLocation lootTable() {
        return ResourceLocation.tryParse(lootTable);
    }

    public double lootChance() {
        return Mth.clamp(lootChance, 0.0d, 1.0d);
    }
}
