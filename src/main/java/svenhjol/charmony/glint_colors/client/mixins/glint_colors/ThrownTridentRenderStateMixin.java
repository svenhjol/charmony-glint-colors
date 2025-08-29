package svenhjol.charmony.glint_colors.client.mixins.glint_colors;

import net.minecraft.client.renderer.entity.state.ThrownTridentRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import svenhjol.charmony.glint_colors.client.features.glint_colors.TridentFoilStateHolder;

import javax.annotation.Nullable;

@SuppressWarnings("AddedMixinMembersNamePattern")
@Mixin(ThrownTridentRenderState.class)
public class ThrownTridentRenderStateMixin implements TridentFoilStateHolder {
    @Unique
    private ItemStackRenderState.FoilType foilType = null;

    @Override
    public void setFoilType(@Nullable ItemStackRenderState.FoilType foilType) {
        this.foilType = foilType;
    }

    @Nullable
    @Override
    public ItemStackRenderState.FoilType getFoilType() {
        return foilType;
    }
}
