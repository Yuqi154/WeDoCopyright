package org.hiedacamellia.wedocopyright.client.config;


import net.neoforged.neoforge.common.ModConfigSpec;

public class CRClientConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();


    public static final ModConfigSpec.BooleanValue ShowModLogos = BUILDER
            .comment("Show mod logos in the copyright page")
            .define("showModLogos", true);

    public static final ModConfigSpec.BooleanValue IgnoreFabricApi = BUILDER
            .comment("Ignore fabric api logos")
            .define("ignoreFabricApi", true);

    public static final ModConfigSpec.BooleanValue FadeIn = BUILDER
            .comment("Enable fade in effect for the copyright page")
            .define("fadeIn", true);

    public static final ModConfigSpec.IntValue LeastTime = BUILDER
            .comment("The least time to show the copyright page, in seconds")
            .defineInRange("leastTime", 1, 1, Integer.MAX_VALUE);

    public static final ModConfigSpec.IntValue AutoNextTime = BUILDER
            .comment("The time to auto next the copyright page, in seconds")
            .defineInRange("autoNextTime", 4, 1, Integer.MAX_VALUE);

    public static final ModConfigSpec SPEC = BUILDER.build();


}
