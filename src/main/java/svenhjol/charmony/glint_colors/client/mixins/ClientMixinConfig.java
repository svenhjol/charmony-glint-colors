package svenhjol.charmony.glint_colors.client.mixins;

import svenhjol.charmony.core.base.MixinConfig;
import svenhjol.charmony.core.enums.Side;
import svenhjol.charmony.glint_colors.GlintColorsMod;

public class ClientMixinConfig extends MixinConfig {
    @Override
    protected String modId() {
        return GlintColorsMod.ID;
    }

    @Override
    protected String modRoot() {
        return "svenhjol.charmony.glint_colors";
    }

    @Override
    protected Side side() {
        return Side.Client;
    }
}
