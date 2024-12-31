package svenhjol.charmony.glint_colors.integration;

import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.integration.BaseModMenuPlugin;
import svenhjol.charmony.glint_colors.GlintColorsMod;

public class ModMenuPlugin extends BaseModMenuPlugin {
    @Override
    public Mod mod() {
        return GlintColorsMod.instance();
    }
}
