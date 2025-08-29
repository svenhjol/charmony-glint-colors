package svenhjol.charmony.glint_colors.client.features.glint_colors;

import net.minecraft.client.renderer.item.ItemStackRenderState.FoilType;

import javax.annotation.Nullable;

public interface TridentFoilStateHolder {
    void setFoilType(@Nullable FoilType foilType);

    @Nullable
    FoilType getFoilType();
}
