package org.hiedacamellia.wedocopyright;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(WeDoCopyRight.MODID)
public class WeDoCopyRight {

    private static boolean shown = false;

    public static void setShown(){
        shown = true;
    }
    public static boolean isShown(){
        return shown;
    }

    public static final String MODID = "wedocopyright";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final boolean kubeJsLoaded = ModList.get().isLoaded("kubejs");

    public WeDoCopyRight() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
    }

}
