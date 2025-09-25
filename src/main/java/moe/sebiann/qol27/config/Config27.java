package moe.sebiann.qol27.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.SectionHeader;

@Modmenu(modId = "qol27")
@Config(name = "qol27-config", wrapperName = "QoL27Config")
public class Config27 {
    @SectionHeader("noSilkTouchDetection")
    public boolean noSilkDetectionEnabled = true;
    public boolean sneakOverridesDetection = true;

    @SectionHeader("woodStrippingDetection")
    public boolean noStrippingEnabled = true;
    public boolean sneakOverridesStripping = true;

    // @SectionHeader("saveCoordsOnLogout")
    // public boolean saveCoordsOnLogout = true;

    @SectionHeader("noCarpetsOnCarpets")
    public boolean noCarpetsOnCarpetsEnabled = true;
    public boolean sneakOverridesCarpetPlacing = true;

}
