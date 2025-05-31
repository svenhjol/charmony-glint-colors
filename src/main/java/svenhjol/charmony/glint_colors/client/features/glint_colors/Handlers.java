package svenhjol.charmony.glint_colors.client.features.glint_colors;

import com.mojang.blaze3d.vertex.ByteBufferBuilder;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import svenhjol.charmony.api.core.Color;
import svenhjol.charmony.core.Charmony;
import svenhjol.charmony.core.base.Setup;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.SequencedMap;

public class Handlers extends Setup<GlintColors> {
    public final Map<DyeColor, ResourceLocation> ITEM_TEXTURES = new HashMap<>();
    public final Map<DyeColor, ResourceLocation> ENTITY_TEXTURES = new HashMap<>();
    public final Map<DyeColor, RenderType> GLINT = new HashMap<>();
    public final Map<DyeColor, RenderType> GLINT_TRANSLUCENT = new HashMap<>();
    public final Map<DyeColor, RenderType> ENTITY_GLINT = new HashMap<>();
    public final Map<DyeColor, RenderType> ARMOR_ENTITY_GLINT = new HashMap<>();

    private SequencedMap<RenderType, ByteBufferBuilder> builders;
    private @Nullable DyeColor targetColor;

    public static boolean initialized = false;

    public Handlers(GlintColors feature) {
        super(feature);
    }

    public void setTargetStack(@Nullable ItemStack targetStack) {
        this.setTargetColor(feature().common.get().handlers.get(targetStack));
    }

    public void setTargetColor(@Nullable DyeColor dyeColor) {
        this.targetColor = dyeColor;
    }

    @Nullable
    public DyeColor getTargetColor() {
        return targetColor;
    }

    public void setBuilders(@Nullable SequencedMap<RenderType, ByteBufferBuilder> builders) {
        this.builders = builders;
        if (initialized || builders == null) return;

        for (DyeColor dyeColor : DyeColor.values()) {
            ResourceLocation itemTexture;
            ResourceLocation entityTexture;
            var colorName = dyeColor.getSerializedName().toLowerCase(Locale.ROOT);

            if (dyeColor.equals(DyeColor.PURPLE)) {
                itemTexture = ItemRenderer.ENCHANTED_GLINT_ITEM;
                entityTexture = ItemRenderer.ENCHANTED_GLINT_ARMOR;
            } else {
                itemTexture = Charmony.id("textures/misc/enchanted_glints/" + colorName + "_glint.png");
                entityTexture = Charmony.id("textures/misc/enchanted_glints/" + colorName + "_glint.png");
            }

            ITEM_TEXTURES.put(dyeColor, itemTexture);
            ENTITY_TEXTURES.put(dyeColor, entityTexture);

            GLINT.put(dyeColor, createGlint(colorName, ITEM_TEXTURES.get(dyeColor)));
            GLINT_TRANSLUCENT.put(dyeColor, createGlintTranslucent(colorName, ENTITY_TEXTURES.get(dyeColor)));
            ENTITY_GLINT.put(dyeColor, createEntityGlint(colorName, ENTITY_TEXTURES.get(dyeColor)));
            ARMOR_ENTITY_GLINT.put(dyeColor, createArmorEntityGlint(colorName, ENTITY_TEXTURES.get(dyeColor)));
        }

        initialized = true;
    }

    public RenderType createGlint(String color, ResourceLocation texture) {
        RenderType renderLayer = RenderType.create("glint_" + color,
            1536,
            RenderPipelines.GLINT,
            RenderType.CompositeState.builder()
                .setTextureState(new RenderStateShard.TextureStateShard(texture, false))
                .setTexturingState(RenderStateShard.GLINT_TEXTURING)
                .createCompositeState(false));

        getBuilders().put(renderLayer, new ByteBufferBuilder(renderLayer.bufferSize()));
        return renderLayer;
    }

    public RenderType createGlintTranslucent(String color, ResourceLocation texture) {
        RenderType renderLayer = RenderType.create("glint_translucent_" + color,
            1536,
            RenderPipelines.GLINT,
            RenderType.CompositeState.builder()
                .setTextureState(new RenderStateShard.TextureStateShard(texture, false))
                .setTexturingState(RenderStateShard.GLINT_TEXTURING)
                .setOutputState(RenderStateShard.ITEM_ENTITY_TARGET)
                .createCompositeState(false));

        getBuilders().put(renderLayer, new ByteBufferBuilder(renderLayer.bufferSize()));
        return renderLayer;
    }

    public RenderType createEntityGlint(String color, ResourceLocation texture) {
        RenderType renderLayer = RenderType.create("entity_glint_" + color,
            1536,
            RenderPipelines.GLINT,
            RenderType.CompositeState.builder()
                .setTextureState(new RenderStateShard.TextureStateShard(texture, false))
                .setTexturingState(RenderStateShard.ENTITY_GLINT_TEXTURING)
                .setOutputState(RenderStateShard.ITEM_ENTITY_TARGET)
                .createCompositeState(false));

        getBuilders().put(renderLayer, new ByteBufferBuilder(renderLayer.bufferSize()));
        return renderLayer;
    }

    public RenderType createArmorEntityGlint(String color, ResourceLocation texture) {
        RenderType renderLayer = RenderType.create("armor_entity_glint_" + color,
            1536,
            RenderPipelines.GLINT,
            RenderType.CompositeState.builder()
                .setTextureState(new RenderStateShard.TextureStateShard(texture, false))
                .setTexturingState(RenderStateShard.ENTITY_GLINT_TEXTURING)
                .setLayeringState(RenderStateShard.VIEW_OFFSET_Z_LAYERING)
                .createCompositeState(false));

        getBuilders().put(renderLayer, new ByteBufferBuilder(renderLayer.bufferSize()));
        return renderLayer;
    }

    public SequencedMap<RenderType, ByteBufferBuilder> getBuilders() {
        return builders;
    }

    @Nullable
    public RenderType getArmorEntityGlintRenderLayer() {
        return ARMOR_ENTITY_GLINT.get(itemOrOverrideColor());
    }

    @Nullable
    public RenderType getEntityGlintRenderLayer() {
        return ENTITY_GLINT.get(itemOrOverrideColor());
    }

    @Nullable
    public RenderType getGlintTranslucentRenderLayer() {
        return GLINT_TRANSLUCENT.get(itemOrOverrideColor());
    }

    @Nullable
    public RenderType getGlintRenderLayer() {
        return GLINT.get(itemOrOverrideColor());
    }

    private DyeColor itemOrOverrideColor() {
        var override = GlintColors.feature().glintColorOverride();
        return override.orElseGet(() -> targetColor);
    }

    public Color hook() {
        return new Color(DyeColor.PURPLE);
    }
}
