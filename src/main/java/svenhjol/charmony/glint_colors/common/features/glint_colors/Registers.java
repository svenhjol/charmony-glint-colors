package svenhjol.charmony.glint_colors.common.features.glint_colors;

import net.minecraft.core.component.DataComponentType;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.common.CommonRegistry;

import java.util.function.Supplier;

public class Registers extends Setup<GlintColors> {
    public final Supplier<DataComponentType<GlintColorData>> data;

    public Registers(GlintColors feature) {
        super(feature);
        var registry = CommonRegistry.forFeature(feature);

        data = registry.dataComponent("glint_color",
            () -> builder -> builder
                .persistent(GlintColorData.CODEC)
                .networkSynchronized(GlintColorData.STREAM_CODEC));
    }
}
