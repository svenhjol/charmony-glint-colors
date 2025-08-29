package svenhjol.charmony.glint_colors.client.mixins.glint_colors;

import net.minecraft.client.renderer.item.ItemStackRenderState.FoilType;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Adds sixteen new foiltypes to the FoilType enum, one for each possible glint color.
 * Enum solution from LudoCrypt:
 * @link <a href="https://github.com/SpongePowered/Mixin/issues/387#issuecomment-888408556">...</a>
 */
@Mixin(FoilType.class)
@Unique
public class FoilTypeMixin {
    @Shadow
    @Final
    @Mutable
    private static FoilType[] $VALUES;

    static {
        for (var dyeColor : DyeColor.values()) {
            addVariant(dyeColor);
        }
    }

    @Invoker("<init>")
    public static FoilType invokeInit(String internalName, int internalId) {
        throw new AssertionError();
    }

    @Unique
    private static void addVariant(DyeColor color) {
        List<FoilType> variants = new ArrayList<>(Arrays.asList(FoilTypeMixin.$VALUES));

        // Our custom enums are STANDARD_ with the uppercase dye color name appended, e.g. STANDARD_RED
        var internalName = "STANDARD_" + color.getSerializedName().toUpperCase(Locale.ROOT);
        var internalId = variants.getLast().ordinal() + 1;

        variants.add(invokeInit(internalName, internalId));
        FoilTypeMixin.$VALUES = variants.toArray(new FoilType[0]);
    }
}
