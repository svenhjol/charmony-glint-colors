package svenhjol.charmony.glint_colors.common.features.glint_colors;

import svenhjol.charmony.glint_colors.GlintColorsMod;
import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Environment;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

import java.util.function.BooleanSupplier;

@FeatureDefinition(side = Side.Common, canBeDisabled = false, description = """
    Customizable item enchantment colors. This feature is a helper for other features.
    If disabled then other features and mods that rely on it will not function properly.""")
public final class GlintColors extends SidedFeature {
    public final Registers registers;
    public final Handlers handlers;
    public final Providers providers;

    public GlintColors(Mod mod) {
        super(mod);

        registers = new Registers(this);
        handlers = new Handlers(this);
        providers = new Providers(this);
    }

    public static GlintColors feature() {
        return GlintColorsMod.instance().sidedFeature(GlintColors.class);
    }

    @Override
    public BooleanSupplier check() {
        return () -> !Environment.isModLoaded("optifabric");
    }
}
