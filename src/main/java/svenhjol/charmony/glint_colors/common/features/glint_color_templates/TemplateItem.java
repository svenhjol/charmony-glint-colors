package svenhjol.charmony.glint_colors.common.features.glint_color_templates;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class TemplateItem extends SmithingTemplateItem {
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;

    static final List<ResourceLocation> EMPTY_ITEMS = List.of(
        ResourceLocation.parse("container/slot/chestplate"),
        ResourceLocation.parse("container/slot/helmet"),
        ResourceLocation.parse("container/slot/sword"),
        ResourceLocation.parse("container/slot/pickaxe")
    );

    public TemplateItem(ResourceKey<Item> key) {
        super(
            makeText("applies_to").withStyle(DESCRIPTION_FORMAT),
            makeText("ingredients").withStyle(DESCRIPTION_FORMAT),
            makeText("base_slot_description"),
            makeText("additions_slot_description"),
            EMPTY_ITEMS,
            GlintColorTemplates.feature().registers.emptyDyes,
            new Properties().setId(key)
        );
    }

    private static MutableComponent makeText(String id) {
        return Component.translatable("smithing_template.charmony-glint-colors.glint_color_" + id);
    }
}
