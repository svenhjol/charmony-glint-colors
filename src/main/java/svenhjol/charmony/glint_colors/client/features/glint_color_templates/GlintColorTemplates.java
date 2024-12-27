package svenhjol.charmony.glint_colors.client.features.glint_color_templates;

import svenhjol.charmony.core.annotations.FeatureDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.base.SidedFeature;
import svenhjol.charmony.core.enums.Side;

import java.util.function.Supplier;

@FeatureDefinition(side = Side.Client, showInConfig = false)
public final class GlintColorTemplates extends SidedFeature {
    public final Supplier<Common> common;
    public final Registers registers;

    public GlintColorTemplates(Mod mod) {
        super(mod);
        common = Common::new;
        registers = new Registers(this);
    }
}
