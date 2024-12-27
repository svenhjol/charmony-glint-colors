package svenhjol.charmony.glint_colors.client.mixins.glint_colors;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import svenhjol.charmony.glint_colors.client.features.glint_colors.GlintColors;

@Mixin(RenderType.class)
public class RenderTypeMixin {
    @ModifyReturnValue(
        method = "armorEntityGlint",
        at = @At("RETURN")
    )
    private static RenderType hookGetArmorEntityGlint(RenderType original) {
        var layer = GlintColors.feature().handlers.getArmorEntityGlintRenderLayer();
        return layer != null ? layer : original;
    }

    @ModifyReturnValue(
        method = "entityGlint",
        at = @At("RETURN")
    )
    private static RenderType hookGetEntityGlint(RenderType original) {
        var layer = GlintColors.feature().handlers.getEntityGlintRenderLayer();
        return layer != null ? layer : original;
    }

    @ModifyReturnValue(
        method = "glintTranslucent",
        at = @At("RETURN")
    )
    private static RenderType hookGetEntityGlintDirect(RenderType original) {
        var layer = GlintColors.feature().handlers.getGlintTranslucentRenderLayer();
        return layer != null ? layer : original;
    }

    @ModifyReturnValue(
        method = "glint",
        at = @At("RETURN")
    )
    private static RenderType hookGetGlint(RenderType original) {
        var layer = GlintColors.feature().handlers.getGlintRenderLayer();
        return layer != null ? layer : original;
    }
}
