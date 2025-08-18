package net.mordgren.gtca.data.recipe.blockdatagen;

import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.world.level.block.Block;


public class BlockTagLoader extends com.gregtechceu.gtceu.data.tags.BlockTagLoader {



    public static void init(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {

        provider.addTag(CustomTags.MINEABLE_WITH_WRENCH)
                .addTag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH);
        provider.addTag(CustomTags.MINEABLE_WITH_WIRE_CUTTER)
                .addTag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WIRE_CUTTER);


    }

}

