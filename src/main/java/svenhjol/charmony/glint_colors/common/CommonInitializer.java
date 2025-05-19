package svenhjol.charmony.glint_colors.common;

import net.fabricmc.api.ModInitializer;
import svenhjol.charmony.glint_colors.GlintColorsMod;
import svenhjol.charmony.glint_colors.common.features.glint_color_templates.GlintColorTemplates;
import svenhjol.charmony.glint_colors.common.features.glint_colors.GlintColors;
import svenhjol.charmony.api.core.Side;

public final class CommonInitializer implements ModInitializer {
    @Override
    public void onInitialize() {
        // Ensure charmony is launched first.
        svenhjol.charmony.core.common.CommonInitializer.init();

        // Prepare and run the mod.
        var mod = GlintColorsMod.instance();
        mod.addSidedFeature(GlintColors.class);
        mod.addSidedFeature(GlintColorTemplates.class);
        mod.run(Side.Common);
    }
}
