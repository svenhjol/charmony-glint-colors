package svenhjol.charmony.glint_colors;

import net.minecraft.resources.ResourceLocation;
import svenhjol.charmony.core.annotations.ModDefinition;
import svenhjol.charmony.core.base.Mod;
import svenhjol.charmony.core.enums.Side;

@ModDefinition(
    id = GlintColorsMod.ID,
    sides = {Side.Client, Side.Common},
    name = "Color Glints",
    description = "Change the glint color of an enchanted item.")
public final class GlintColorsMod extends Mod {
    public static final String ID = "charmony-glint-colors";
    private static GlintColorsMod instance;

    private GlintColorsMod() {}

    public static GlintColorsMod instance() {
        if (instance == null) {
            instance = new GlintColorsMod();
        }
        return instance;
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }
}