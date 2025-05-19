package svenhjol.charmony.glint_colors.client.features.glint_color_templates;

import svenhjol.charmony.api.core.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.api.core.Side;

import java.util.function.Supplier;

@FeatureDefinition(side = Side.Client, canBeDisabledInConfig = false)
public final class GlintColorTemplates extends SidedFeature {
    public final Supplier<Common> common;
    public final Registers registers;

    public GlintColorTemplates(Mod mod) {
        super(mod);
        common = Common::new;
        registers = new Registers(this);
    }
}
