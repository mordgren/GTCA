package net.mordgren.gtca.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator;
import com.gregtechceu.gtceu.api.data.worldgen.generator.veins.DikeVeinGenerator;
import com.gregtechceu.gtceu.common.data.GTOres;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.UniformInt;

import java.util.function.Consumer;

public class GTCAOres {
    public static void init(){}

//    public static final GTOreDefinition ZIRCONIUM_VEIN = create("zirconium_vein", vein -> vein
//            .clusterSize(UniformInt.of(19, 24)).density(0.4f).weight(10)
//            .layer(WorldGenLayers.ENDSTONE)
//            .heightRangeUniform(20, 60)
//            .biomes(BiomeTags.IS_END)
//            .layeredVeinGenerator(generator -> generator
//                    .withLayerPattern(() -> GTLayerPattern.builder(END_RULES)
//                            .layer(l -> l.weight(2).state(Blocks.END_STONE::defaultBlockState).size(1, 6))
//                            .layer(l -> l.weight(2).mat(GTCAMaterials.RedFuchsite).size(1, 4))
//                            .layer(l -> l.weight(2).mat(GTCAMaterials.RedZircon).size(1, 2))
//                            .layer(l -> l.weight(1).mat(GTCAMaterials.Fayalite).size(1, 5))
//                            .layer(l -> l.weight(2).mat(GTCAMaterials.GreenFuchsite).size(1, 5))
//                            .build()))
//            .surfaceIndicatorGenerator(indicator -> indicator
//                    .surfaceRock(GTCAMaterials.RedZircon)
//                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));


    public static final GTOreDefinition ZIRCONIUM_VEIN = create("zirconium_vein", vein -> vein
            .clusterSize(UniformInt.of(30, 40)).density(0.75f).weight(15)
            .layer(WorldGenLayers.ENDSTONE)
            .heightRangeUniform(20, 60)
            .biomes(BiomeTags.IS_END)
            .dikeVeinGenerator(generator -> generator
                    .withBlock(new DikeVeinGenerator.DikeBlockDefinition(GTCAMaterials.RedFuchsite, 2, 25, 60))
                    .withBlock(new DikeVeinGenerator.DikeBlockDefinition(GTCAMaterials.RedZircon, 2, 20, 45))
                    .withBlock(new DikeVeinGenerator.DikeBlockDefinition(GTCAMaterials.Fayalite, 1, 30, 60))
                    .withBlock(new DikeVeinGenerator.DikeBlockDefinition(GTCAMaterials.GreenFuchsite, 2, 25, 55)))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTCAMaterials.RedZircon)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));



    private static GTOreDefinition create(String name, Consumer<GTOreDefinition> config) {
        return GTOres.create(GTCEu.id(name), config);
    }
}
