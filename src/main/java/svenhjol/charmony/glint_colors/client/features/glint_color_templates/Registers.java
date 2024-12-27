package svenhjol.charmony.glint_colors.client.features.glint_color_templates;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.client.ClientRegistry;

public final class Registers extends Setup<GlintColorTemplates> {
    public Registers(GlintColorTemplates feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> {
            var registry = ClientRegistry.forFeature(feature());

            registry.itemTab(
                feature().common.get().registers.item.get(),
                CreativeModeTabs.INGREDIENTS,
                Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE
            );
        };
    }
}
