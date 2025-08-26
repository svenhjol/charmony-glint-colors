package svenhjol.charmony.glint_colors.client.mixins.glint_colors;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.BlockModelWrapper;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.item.SpecialModelWrapper;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charmony.glint_colors.client.features.glint_color_templates.FoilColorHolder;

@Mixin({
    BlockModelWrapper.class,
    SpecialModelWrapper.class
})
public class ItemModelsMixin {
    @Inject(
        method = "update",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/item/ItemStackRenderState$LayerRenderState;setFoilType(Lnet/minecraft/client/renderer/item/ItemStackRenderState$FoilType;)V"
        )
    )
    private void hookUpdateSetFoilColor(ItemStackRenderState itemStackRenderState, ItemStack itemStack, ItemModelResolver itemModelResolver, ItemDisplayContext itemDisplayContext, ClientLevel clientLevel, ItemOwner itemOwner, int i, CallbackInfo ci, @Local ItemStackRenderState.LayerRenderState layerRenderState) {
        ((FoilColorHolder)layerRenderState).setFoilColorFromItemStack(itemStack);
    }
}
