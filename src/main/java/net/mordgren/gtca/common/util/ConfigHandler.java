package net.mordgren.gtca.common.util;
import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.format.ConfigFormats;
import net.mordgren.gtca.GTCA;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;

@Config(id = GTCA.MOD_ID)
public final class ConfigHandler {

    public static ConfigHandler INSTANCE;
    private static final Object LOCK = new Object();

    public static void init() {
        synchronized (LOCK) {
            if (INSTANCE == null) {
                INSTANCE = Configuration.registerConfig(ConfigHandler.class, ConfigFormats.yaml()).getConfigInstance();
            }
        }
    }
    @Configurable
    @Configurable.Synchronized
    @Configurable.Comment({"Enable Bastnasite Line", "Default: true"})
    public boolean enableBastnasiteLine = true;
}

