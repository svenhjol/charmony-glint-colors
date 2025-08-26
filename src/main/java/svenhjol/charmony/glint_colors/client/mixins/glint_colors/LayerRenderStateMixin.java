package svenhjol.charmony.glint_colors.client.mixins.glint_colors;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charmony.glint_colors.client.features.glint_color_templates.FoilColorHolder;
import svenhjol.charmony.glint_colors.client.features.glint_colors.GlintColors;

@Mixin(ItemStackRenderState.LayerRenderState.class)
public class LayerRenderStateMixin implements FoilColorHolder {
    @Unique
    DyeColor dyeColor;

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Unique
    @Override
    public void setFoilColorFromItemStack(ItemStack stack) {
        dyeColor = GlintColors.feature().common.get().handlers.get(stack);
    }

    @Inject(
        method = "submit",
        at = @At("HEAD")
    )
    public void hookRenderStart(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, int k, CallbackInfo ci) {
        GlintColors.feature().handlers.setTargetColor(dyeColor);
    }


    @Inject(
        method = "submit",
        at = @At("TAIL")
    )
    public void hookRenderFinish(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, int k, CallbackInfo ci) {
        GlintColors.feature().handlers.setTargetColor(null);
    }
}
