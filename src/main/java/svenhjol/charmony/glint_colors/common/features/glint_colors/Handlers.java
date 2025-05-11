package svenhjol.charmony.glint_colors.common.features.glint_colors;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import svenhjol.charmony.core.base.Setup;

import javax.annotation.Nullable;

public class Handlers extends Setup<GlintColors> {
    public Handlers(GlintColors feature) {
        super(feature);
    }

    /**
     * Set the enchanted item's glint to the dye color.
     */
    public void apply(ItemStack stack, DyeColor color) {
        GlintColorData.create()
            .setColor(color)
            .save(stack);
    }

    public void remove(ItemStack stack) {
        GlintColorData.remove(stack);
    }

    /**
     * Get the enchanted item's glint color.
     */
    @SuppressWarnings("DataFlowIssue")
    @Nullable
    public DyeColor get(@Nullable ItemStack stack) {
        if (has(stack)) {
            return GlintColorData.get(stack).color();
        }

        return null;
    }

    /**
     * Check if stack has a colored glint.
     */
    public boolean has(@Nullable ItemStack stack) {
        return stack != null && GlintColorData.has(stack);
    }
}
