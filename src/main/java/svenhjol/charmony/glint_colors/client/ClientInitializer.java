package svenhjol.charmony.glint_colors.client;

import net.fabricmc.api.ClientModInitializer;
import svenhjol.charmony.glint_colors.GlintColorsMod;
import svenhjol.charmony.glint_colors.client.features.glint_color_templates.GlintColorTemplates;
import svenhjol.charmony.glint_colors.client.features.glint_colors.GlintColors;
import svenhjol.charmony.api.core.Side;

public final class ClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Ensure charmony is launched first.
        svenhjol.charmony.core.client.ClientInitializer.init();

        // Prepare and run the mod.
        var mod = GlintColorsMod.instance();
        mod.addSidedFeature(GlintColors.class);
        mod.addSidedFeature(GlintColorTemplates.class);
        mod.run(Side.Client);
    }
}
