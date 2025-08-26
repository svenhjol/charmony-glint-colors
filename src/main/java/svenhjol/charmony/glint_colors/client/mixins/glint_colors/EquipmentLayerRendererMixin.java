package svenhjol.charmony.glint_colors.client.mixins.glint_colors;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.EquipmentAsset;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charmony.glint_colors.client.features.glint_colors.GlintColors;

@Mixin(EquipmentLayerRenderer.class)
public class EquipmentLayerRendererMixin {
    @Inject(
        method = "renderLayers(Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;II)V",
        at = @At("HEAD")
    )
    private <S> void hookRenderLayers(EquipmentClientInfo.LayerType layerType, ResourceKey<EquipmentAsset> resourceKey, Model<? super S> model, S object, ItemStack itemStack, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, CallbackInfo ci) {
        GlintColors.feature().handlers.setTargetStack(itemStack);
    }
}
