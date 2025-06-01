package moe.sebiann.qol27.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

/** Every option in a Config27 class has to be public and static, so we can access it from other classes.
 * The config class also has to extend Config27*/

@Config(name = "QoL27")
public class Config27 implements ConfigData {
    public boolean noSilkDetectionEnabled = true;
    public boolean sneakOverridesDetection = true;
    public boolean saveCoordsOnLogout = true;
}
