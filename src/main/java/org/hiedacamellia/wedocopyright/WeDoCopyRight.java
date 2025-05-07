package org.hiedacamellia.wedocopyright;

import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import org.hiedacamellia.wedocopyright.client.config.CRClientConfig;
import org.slf4j.Logger;

public class WeDoCopyRight implements ClientModInitializer {

    private static boolean shown = false;

    public static void setShown(){
        shown = true;
    }
    public static boolean isShown(){
        return shown;
    }

    public static final String MODID = "wedocopyright";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final boolean kubeJsLoaded = FabricLoader.getInstance().isModLoaded("kubejs");

    @Override
    public void onInitializeClient() {
        AutoConfig.register(CRClientConfig.class, Toml4jConfigSerializer::new);
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
    }
}
