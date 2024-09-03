package net.mordgren.gtca.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.worldgen.GTLayerPattern;
import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator;
import com.gregtechceu.gtceu.api.data.worldgen.generator.veins.DikeVeinGenerator;
import com.gregtechceu.gtceu.common.data.GTOres;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.UniformInt;

import java.util.function.Consumer;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTOres.END_RULES;
import static com.gregtechceu.gtceu.common.data.GTOres.OVERWORLD_RULES;

public class GTCAOres {
    public static void init(){}

    public static final GTOreDefinition NEUTRONIUM_VEIN = create("neutronium_vein", vein -> vein
            .clusterSize(UniformInt.of(50, 64)).density(0.7f).weight(20)
            .layer(WorldGenLayers.ENDSTONE)
            .heightRangeUniform(20, 60)
            .biomes(BiomeTags.IS_END)
            .dikeVeinGenerator(generator -> generator
                    .withBlock(new DikeVeinGenerator.DikeBlockDefinition(Tungstate, 3, 20, 60))
                    .withBlock(new DikeVeinGenerator.DikeBlockDefinition(Chromite, 2, 35, 55))
                    .withBlock(new DikeVeinGenerator.DikeBlockDefinition(GTCAMaterials.Neutronite, 1, 20, 40)))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTCAMaterials.Neutronite)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));



    private static GTOreDefinition create(String name, Consumer<GTOreDefinition> config) {
        return GTOres.create(GTCEu.id(name), config);
    }
}
