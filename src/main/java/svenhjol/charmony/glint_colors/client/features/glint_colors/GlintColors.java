package svenhjol.charmony.glint_colors.client.features.glint_colors;

import net.minecraft.world.item.DyeColor;
import svenhjol.charmony.core.annotations.Configurable;
import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

@FeatureDefinition(side = Side.Client, canBeDisabled = false)
public final class GlintColors extends SidedFeature {
    public final Handlers handlers;
    public final Registers registers;
    public final Supplier<Common> common;

    @Configurable(
        name = "Override enchantment glint color",
        description = """
            This setting changes the glint color for all enchanted items.
            Note that this setting only changes the color for your client.
            It must be a valid dye color, for example 'red'.""",
        requireRestart = false
    )
    private static String glintColorOverride = "";

    public GlintColors(Mod mod) {
        super(mod);
        common = Common::new;
        registers = new Registers(this);
        handlers = new Handlers(this);
    }

    public static GlintColors feature() {
        return Mod.getSidedFeature(GlintColors.class);
    }

    public Optional<DyeColor> glintColorOverride() {
        if (!glintColorOverride.isEmpty()) {
            try {
                var dyeColor = DyeColor.valueOf(glintColorOverride.toUpperCase(Locale.ROOT));
                return Optional.of(dyeColor);
            } catch (IllegalArgumentException e) {
                // fall through to empty optional
            }
        }
        return Optional.empty();
    }
}
