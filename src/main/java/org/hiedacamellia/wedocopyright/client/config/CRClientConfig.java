package org.hiedacamellia.wedocopyright.client.config;

import net.minecraftforge.common.ForgeConfigSpec;


public class CRClientConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();


    public static final ForgeConfigSpec.BooleanValue ShowModLogos = BUILDER
            .comment("Show mod logos in the copyright page")
            .define("showModLogos", true);

    public static final ForgeConfigSpec.BooleanValue IgnoreFabricApi = BUILDER
            .comment("Ignore fabric api logos")
            .define("ignoreFabricApi", true);

    public static final ForgeConfigSpec.BooleanValue FadeIn = BUILDER
            .comment("Enable fade in effect for the copyright page")
            .define("fadeIn", true);

    public static final ForgeConfigSpec.IntValue LeastTime = BUILDER
            .comment("The least time to show the copyright page, in seconds")
            .defineInRange("leastTime", 1, 1, Integer.MAX_VALUE);

    public static final ForgeConfigSpec.IntValue AutoNextTime = BUILDER
            .comment("The time to auto next the copyright page, in seconds")
            .defineInRange("autoNextTime", 4, 1, Integer.MAX_VALUE);

    public static final ForgeConfigSpec SPEC = BUILDER.build();


}
