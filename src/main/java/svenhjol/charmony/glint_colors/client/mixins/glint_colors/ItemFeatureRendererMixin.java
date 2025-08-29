package svenhjol.charmony.glint_colors.client.mixins.glint_colors;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.OutlineBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollection;
import net.minecraft.client.renderer.SubmitNodeStorage;
import net.minecraft.client.renderer.feature.ItemFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charmony.glint_colors.client.features.glint_colors.GlintColors;

@Mixin(ItemFeatureRenderer.class)
public class ItemFeatureRendererMixin {
    /**
     * Gets the dye color from the custom foil type and sets it so that the next
     * call to fetch a glint receives the correct color of glint.
     */
    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V"
        )
    )
    private void hookPreRender(SubmitNodeCollection submitNodeCollection, MultiBufferSource.BufferSource bufferSource, OutlineBufferSource outlineBufferSource, CallbackInfo ci, @Local SubmitNodeStorage.ItemSubmit itemSubmit) {
        GlintColors.feature().handlers.dyeColorFromFoilType(itemSubmit.foilType()).ifPresent(
            color -> GlintColors.feature().handlers.setTargetColor(color));
    }

    /**
     * Unset the target dye color after the render call.
     */
    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V"
        )
    )
    private void hookPostRender(SubmitNodeCollection submitNodeCollection, MultiBufferSource.BufferSource bufferSource, OutlineBufferSource outlineBufferSource, CallbackInfo ci) {
        GlintColors.feature().handlers.setTargetColor(null);
    }
}
