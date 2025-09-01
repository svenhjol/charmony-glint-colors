package svenhjol.charmony.glint_colors.client.mixins.glint_colors;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.item.TrackingItemStackRenderState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charmony.glint_colors.client.features.glint_colors.GlintColors;

/**
 * Note: as of 1.21.9 this mixin may no longer be required.
 */
@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {

    @Inject(
        method = "renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;III)V",
        at = @At("HEAD")
    )
    private void hookRenderItem(LivingEntity livingEntity, Level level, ItemStack itemStack, int i, int j, int k, CallbackInfo ci) {
        GlintColors.feature().handlers.setTargetStack(itemStack);
    }

    @WrapOperation(
        method = "renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;III)V",
        at = @At(
            value = "NEW",
            target = "()Lnet/minecraft/client/renderer/item/TrackingItemStackRenderState;"
        )
    )
    private TrackingItemStackRenderState testState(Operation<TrackingItemStackRenderState> original, @Local(argsOnly = true) ItemStack stack) {
        var called = original.call();
        var targetColor = GlintColors.feature().handlers.getTargetColor();
        if (targetColor != null) {
            // Add another identity element to this render state.
            // All render states that share an identity are rendererd in batch,
            // so we have to make each color its own unique identity.
            called.appendModelIdentityElement(String.valueOf(targetColor));
        }
        return called;
    }
}
