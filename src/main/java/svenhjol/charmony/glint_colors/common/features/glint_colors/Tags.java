package svenhjol.charmony.glint_colors.common.features.glint_colors;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import svenhjol.charmony.glint_colors.GlintColorsMod;

public final class Tags {
    public static final TagKey<Item> COLORED_DYES = TagKey.create(Registries.ITEM,
        GlintColorsMod.id("colored_dyes"));
}
