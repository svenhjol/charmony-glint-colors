package svenhjol.charmony.glint_colors.client.mixins.glint_colors;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.ByteBufferBuilder;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charmony.glint_colors.client.features.glint_colors.GlintColors;

import java.util.SequencedMap;

@Mixin(RenderBuffers.class)
public class RenderBuffersMixin {
    /**
     * Capture a reference to the buffer builders so we can add new elements to it.
     */
    @Inject(
        method = "<init>",
        at = @At("TAIL")
    )
    private void hookInit(int i, CallbackInfo ci, @Local(ordinal = 0) SequencedMap<RenderType, ByteBufferBuilder> builders) {
        GlintColors.feature().handlers.setBuilders(builders);
    }
}
