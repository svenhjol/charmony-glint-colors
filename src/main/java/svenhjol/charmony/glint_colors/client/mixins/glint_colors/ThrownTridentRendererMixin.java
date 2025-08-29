package svenhjol.charmony.glint_colors.client.mixins.glint_colors;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.ThrownTridentRenderer;
import net.minecraft.client.renderer.entity.state.ThrownTridentRenderState;
import net.minecraft.world.entity.projectile.ThrownTrident;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charmony.glint_colors.client.features.glint_colors.GlintColors;
import svenhjol.charmony.glint_colors.client.features.glint_colors.TridentFoilStateHolder;

@Mixin(ThrownTridentRenderer.class)
public class ThrownTridentRendererMixin {
    @Inject(
        method = "extractRenderState(Lnet/minecraft/world/entity/projectile/ThrownTrident;Lnet/minecraft/client/renderer/entity/state/ThrownTridentRenderState;F)V",
        at = @At("TAIL")
    )
    private void hookExtractRenderState(ThrownTrident trident, ThrownTridentRenderState state, float f, CallbackInfo ci) {
        var stack = trident.getPickupItemStackOrigin();
        var wrapped = (TridentFoilStateHolder)state;
        var color = GlintColors.feature().common.get().handlers.get(stack);

        if (color != null) {
            var foilType = GlintColors.feature().handlers.foilTypeFromDyeColor(color);
            wrapped.setFoilType(foilType);
        }
    }

    @Inject(
        method = "submit(Lnet/minecraft/client/renderer/entity/state/ThrownTridentRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;)V",
        at = @At("HEAD")
    )
    private void hookSubmitStart(ThrownTridentRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CallbackInfo ci) {
        var wrapped = (TridentFoilStateHolder)state;
        var foilType = wrapped.getFoilType();

        if (foilType != null) {
            GlintColors.feature().handlers.dyeColorFromFoilType(foilType).ifPresent(
                color -> GlintColors.feature().handlers.setTargetColor(color));
        }
    }

    @Inject(
        method = "submit(Lnet/minecraft/client/renderer/entity/state/ThrownTridentRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;)V",
        at = @At("TAIL")
    )
    private void hookSubmitEnd(ThrownTridentRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CallbackInfo ci) {
        GlintColors.feature().handlers.setTargetColor(null);
    }
}
