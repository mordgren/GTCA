package net.mordgren.gtca.data;


import com.tterrag.registrate.providers.ProviderType;
import net.mordgren.gtca.common.registry.GTCARegistration;
import net.mordgren.gtca.data.lang.LangHandler;
import net.mordgren.gtca.data.recipe.blockdatagen.BlockTagLoader;

public class GTCADataGen {
    public static void init() {
    GTCARegistration.REGISTRATE.addDataGenerator(ProviderType.LANG, LangHandler::init);
    GTCARegistration.REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, BlockTagLoader::init);
    }
}
