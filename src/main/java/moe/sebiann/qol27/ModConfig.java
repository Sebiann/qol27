package moe.sebiann.qol27;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.SectionHeader;

@Modmenu(modId = "qol27")
@Config(name = "qol27-config", wrapperName = "QoL27Config")
public class ModConfig {
//    @SectionHeader("No Silk Touch Detection")
    public boolean sneakOverridesDetection = true;
}
