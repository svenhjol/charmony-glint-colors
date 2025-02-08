package svenhjol.charmony.glint_colors.client.features.glint_color_templates;

import svenhjol.charmony.glint_colors.GlintColorsMod;
import svenhjol.charmony.glint_colors.common.features.glint_color_templates.GlintColorTemplates;
import svenhjol.charmony.glint_colors.common.features.glint_color_templates.Registers;

public final class Common {
    public final Registers registers;

    public Common() {
        var feature = GlintColorsMod.getSidedFeature(GlintColorTemplates.class);
        registers = feature.registers;
    }
}
