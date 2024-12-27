package svenhjol.charmony.glint_colors.common.features.glint_colors;

import svenhjol.charmony.api.GlintColorApi;
import svenhjol.charmony.core.base.Setup;

public final class Providers extends Setup<GlintColors> {
    public Providers(GlintColors feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> {
            GlintColorApi.instance().setApply((i, d) -> feature().handlers.apply(i, d));
            GlintColorApi.instance().setRemove(i -> feature().handlers.remove(i));
            GlintColorApi.instance().setHas(i -> feature().handlers.has(i));
        };
    }
}
