package svenhjol.charmony.glint_colors.common.features.glint_colors;

import svenhjol.charmony.api.glint_colors.GlintColorsApi;
import svenhjol.charmony.core.base.Setup;

public class Providers extends Setup<GlintColors> {
    public Providers(GlintColors feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> {
            GlintColorsApi.instance().setApply((i, d) -> feature().handlers.apply(i, d));
            GlintColorsApi.instance().setRemove(i -> feature().handlers.remove(i));
            GlintColorsApi.instance().setHas(i -> feature().handlers.has(i));
        };
    }
}
