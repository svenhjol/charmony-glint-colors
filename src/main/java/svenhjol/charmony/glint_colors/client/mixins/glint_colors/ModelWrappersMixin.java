package svenhjol.charmony.glint_colors.client.mixins.glint_colors;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.item.BlockModelWrapper;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.item.SpecialModelWrapper;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import svenhjol.charmony.glint_colors.client.features.glint_colors.GlintColors;

@Mixin({
    BlockModelWrapper.class,
    SpecialModelWrapper.class
})
public class ModelWrappersMixin {
    @WrapOperation(
        method = "update",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/item/ItemStackRenderState$LayerRenderState;setFoilType(Lnet/minecraft/client/renderer/item/ItemStackRenderState$FoilType;)V"
        )
    )
    private void hookSetFoilType(ItemStackRenderState.LayerRenderState instance, ItemStackRenderState.FoilType foilType, Operation<Void> original, @Local(argsOnly = true) ItemStack itemStack) {
        var dyeColor = GlintColors.feature().common.get().handlers.get(itemStack);
        if (dyeColor != null) {
            foilType = GlintColors.feature().handlers.foilTypeFromDyeColor(dyeColor);
        }

        original.call(instance, foilType);
    }

    @WrapOperation(
        method = "update",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/item/ItemStackRenderState;appendModelIdentityElement(Ljava/lang/Object;)V"
        )
    )
    private void hookAppendModelIdentityElement(ItemStackRenderState instance, Object foilType, Operation<Void> original,
                                                @Local(argsOnly = true) ItemStack itemStack) {
        var dyeColor = GlintColors.feature().common.get().handlers.get(itemStack);
        if (dyeColor != null) {
            foilType = GlintColors.feature().handlers.foilTypeFromDyeColor(dyeColor);
        }

        original.call(instance, foilType);
    }
}
