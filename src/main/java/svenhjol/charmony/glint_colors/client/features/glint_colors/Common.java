package svenhjol.charmony.glint_colors.client.features.glint_colors;

import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.glint_colors.common.features.glint_colors.GlintColors;
import svenhjol.charmony.glint_colors.common.features.glint_colors.Handlers;

public class Common {
    public final Handlers handlers;

    public Common() {
        var feature = Mod.getSidedFeature(GlintColors.class);
        handlers = feature.handlers;
    }
}
