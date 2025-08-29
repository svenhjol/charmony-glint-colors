package svenhjol.charmony.glint_colors.client.mixins.glint_colors;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charmony.glint_colors.client.features.glint_color_templates.FoilColorHolder;
import svenhjol.charmony.glint_colors.client.features.glint_colors.GlintColors;

import javax.annotation.Nullable;
import java.util.Locale;

@Mixin(ItemStackRenderState.LayerRenderState.class)
public class LayerRenderStateMixin implements FoilColorHolder {
    @Shadow private ItemStackRenderState.FoilType foilType;
    @Unique
    DyeColor dyeColor;

    @Nullable
    @Unique
    ItemStackRenderState.FoilType lastFoilType = null;

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Unique
    @Override
    public void setFoilColorFromItemStack(ItemStack stack) {
        dyeColor = GlintColors.feature().common.get().handlers.get(stack);
    }

    /**
     * Set the internal foilType to a custom color enum before submitting the item render state to the collection.
     * This hook only sets the foilType if the stack contains a custom glint dyeColor.
     */
    @Inject(
        method = "submit",
        at = @At("HEAD")
    )
    public void hookRenderStart(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, int k, CallbackInfo ci) {
        if (this.dyeColor != null) {
            // Our custom enums are STANDARD_ with the uppercase dye color name appended, e.g. STANDARD_RED
            var internalName = "STANDARD_" + this.dyeColor.getSerializedName().toUpperCase(Locale.ROOT);

            try {
                this.lastFoilType = this.foilType;
                this.foilType = ItemStackRenderState.FoilType.valueOf(internalName);
            } catch (Exception e) {
                // don't set the foiltype if the custom enum isn't available
            }
        }
    }

    /**
     * Reset the internal foilType to its default if we modified it in hookRenderStart.
     */
    @Inject(
        method = "submit",
        at = @At("TAIL")
    )
    public void hookRenderFinish(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, int k, CallbackInfo ci) {
        if (this.dyeColor != null && this.lastFoilType != null) {
            this.foilType = this.lastFoilType;
        }
        this.lastFoilType = null;
    }
}
