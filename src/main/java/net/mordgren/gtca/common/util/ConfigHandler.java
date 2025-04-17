package net.mordgren.gtca.common.util;
import net.mordgren.gtca.GTCA;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;

@Config(id = GTCA.MOD_ID)
public final class ConfigHandler {

    @Configurable
    public static ClientConfigs Client = new ClientConfigs();
    @Configurable
    public static ServerConfigs Server = new ServerConfigs();

    public static class ServerConfigs {

        @Configurable
        @Configurable.Synchronized
        @Configurable.Comment({ "Enable Bastnasite Line", "Default: true" })
        public boolean enableBastnasiteLine = true;

        ServerConfigs() {}
    }

    public static class ClientConfigs {

        ClientConfigs() {}
    }
}

