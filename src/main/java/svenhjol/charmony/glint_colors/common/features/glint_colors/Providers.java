package svenhjol.charmony.glint_colors.common.features.glint_colors;

import svenhjol.charmony.api.glint_colors.GlintColorsApi;
import svenhjol.charmony.core.base.Setup;

import java.util.Optional;

public class Providers extends Setup<GlintColors> {
    public Providers(GlintColors feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> {
            // Implementation to apply a glint color to an item.
            GlintColorsApi.Impl.apply((stack, color) -> feature().handlers.apply(stack, color));

            // Implementation to remove a glint color from an item.
            GlintColorsApi.Impl.remove(stack -> feature().handlers.remove(stack));

            // Implementation to check if an item has a glint color.
            GlintColorsApi.Impl.has(stack -> feature().handlers.has(stack));

            // Implementation to get the color of an item. If not present, returns empty optional.
            GlintColorsApi.Impl.getColor(stack -> {
                var color = feature().handlers.get(stack);
                return Optional.ofNullable(color);
            });
        };
    }
}
