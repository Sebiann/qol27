package moe.sebiann.qol27.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;

public class Config {
    public static ConfigClassHandler<Config> HANDLER = ConfigClassHandler.createBuilder(Config.class)
            .id(new ResourceLocation("qol27", "config"))
                    .serializer(config -> GsonConfigSerializerBuilder.create(config)
                            .setPath(FabricLoader.getInstance().getConfigDir().resolve("my_mod.json5"))
                            .setJson5(true)
                            .build())
                    .build();

    @SerialEntry
    public boolean noSilkDetectionEnabled = true;

    @SerialEntry
    public boolean sneakOverridesDetection = true;

    @SerialEntry
    public boolean noStrippingEnabled = true;

    @SerialEntry
    public boolean sneakOverridesStripping = true;

    @SerialEntry
    public boolean noCarpetsOnCarpetsEnabled = true;

    @SerialEntry
    public boolean sneakOverridesCarpetPlacing = true;
}
