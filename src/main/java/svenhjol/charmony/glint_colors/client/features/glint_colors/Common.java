package svenhjol.charmony.glint_colors.client.features.glint_colors;

import svenhjol.charmony.glint_colors.GlintColorsMod;
import svenhjol.charmony.glint_colors.common.features.glint_colors.GlintColors;
import svenhjol.charmony.glint_colors.common.features.glint_colors.Handlers;

public final class Common {
    public final Handlers handlers;

    public Common() {
        var feature = GlintColorsMod.instance().sidedFeature(GlintColors.class);
        handlers = feature.handlers;
    }
}
