package svenhjol.charmony.glint_colors.common.features.glint_colors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

public record GlintColorData(DyeColor color) {
    public static final Codec<GlintColorData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        DyeColor.CODEC.fieldOf("color")
            .forGetter(GlintColorData::color)
    ).apply(instance, GlintColorData::new));

    public static final StreamCodec<FriendlyByteBuf, GlintColorData> STREAM_CODEC = StreamCodec.composite(
        DyeColor.STREAM_CODEC,
            GlintColorData::color,
        GlintColorData::new
    );

    public static final GlintColorData EMPTY = new GlintColorData(DyeColor.PURPLE);

    public static Mutable create() {
        return new Mutable(EMPTY);
    }

    public static boolean has(ItemStack stack) {
        return stack.has(GlintColors.feature().registers.data.get());
    }

    public static GlintColorData get(ItemStack stack) {
        return stack.getOrDefault(GlintColors.feature().registers.data.get(), EMPTY);
    }

    public static void set(ItemStack stack, Mutable mutable) {
        stack.set(GlintColors.feature().registers.data.get(), mutable.toImmutable());
    }

    public static void remove(ItemStack stack) {
        stack.remove(GlintColors.feature().registers.data.get());
    }

    public static class Mutable {
        private DyeColor color;

        public Mutable(GlintColorData data) {
            this.color = data.color;
        }

        public Mutable setColor(DyeColor color) {
            this.color = color;
            return this;
        }

        public GlintColorData toImmutable() {
            return new GlintColorData(color);
        }

        public void save(ItemStack stack) {
            GlintColorData.set(stack, this);
        }
    }
}
