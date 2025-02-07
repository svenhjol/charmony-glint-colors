package svenhjol.charmony.glint_colors.client.features.glint_color_templates;

import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.glint_colors.common.features.glint_color_templates.GlintColorTemplates;
import svenhjol.charmony.glint_colors.common.features.glint_color_templates.Registers;

public final class Common {
    public final Registers registers;

    public Common() {
        var feature = Mod.getSidedFeature(GlintColorTemplates.class);
        registers = feature.registers;
    }
}
