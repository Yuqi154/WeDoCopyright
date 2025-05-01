package org.hiedacamellia.wedocopyright.client.config;

import net.minecraftforge.common.ForgeConfigSpec;


public class CRClientConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();


    public static final ForgeConfigSpec.BooleanValue ShowModLogos = BUILDER
            .comment("Show mod logos in the copyright page")
            .define("showModLogos", true);

    public static final ForgeConfigSpec SPEC = BUILDER.build();


}
