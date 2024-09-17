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

   public static final GTOreDefinition ZIRCONIUM_VEIN = create("zirconium_vein", vein -> vein
           .clusterSize(UniformInt.of(35, 45)).density(0.4f).weight(7)
           .layer(WorldGenLayers.ENDSTONE)
           .heightRangeUniform(20, 60)
           .biomes(BiomeTags.IS_END)
           .cuboidVeinGenerator(generator -> generator
                  .withBlock(new DikeVeinGenerator.DikeBlockDefinition(GTCAMaterials.RedFuchsite, 2, 20, 15))
                  .withBlock(new DikeVeinGenerator.DikeBlockDefinition(GTCAMaterials.RedZircon, 3, 20, 25))
                  .withBlock(new DikeVeinGenerator.DikeBlockDefinition(GTCAMaterials.Fayalite, 1, 5, 10))
                  .withBlock(new DikeVeinGenerator.DikeBlockDefinition(GTCAMaterials.GreenFuchsite, 2, 20, 10)))
                    .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTCAMaterials.RedZircon)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));


    private static GTOreDefinition create(String name, Consumer<GTOreDefinition> config) {
        return GTOres.create(GTCEu.id(name), config);
    }
}
