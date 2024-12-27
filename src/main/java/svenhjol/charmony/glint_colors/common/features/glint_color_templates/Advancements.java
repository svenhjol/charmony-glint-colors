package svenhjol.charmony.glint_colors.common.features.glint_color_templates;

import net.minecraft.world.entity.player.Player;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.helper.AdvancementHelper;

public final class Advancements extends Setup<GlintColorTemplates> {
    public Advancements(GlintColorTemplates feature) {
        super(feature);
    }

    public void appliedGlintColorTemplate(Player player) {
        AdvancementHelper.trigger("applied_glint_color_template", player);
    }
}
