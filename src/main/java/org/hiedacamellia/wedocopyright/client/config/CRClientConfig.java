package org.hiedacamellia.wedocopyright.client.config;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import org.hiedacamellia.wedocopyright.WeDoCopyRight;

@Config(name = WeDoCopyRight.MODID)
public class CRClientConfig implements ConfigData {

    @Comment("Show mod logos in the copyright page")
    public boolean ShowModLogos = true;

    @Comment("Ignore fabric api logos")
    public boolean IgnoreFabricApi = true;

    @Comment("Enable fade in effect for the copyright page")
    public boolean FadeIn = true;

    @Comment("The least time to show the copyright page, in seconds")
    public int LeastTime = 1;

    @Comment("The time to auto next the copyright page, in seconds")
    public int AutoNextTime = 4;

}
