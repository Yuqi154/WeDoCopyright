package org.hiedacamellia.wedocopyright;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import org.hiedacamellia.wedocopyright.client.config.CRClientConfig;
import org.slf4j.Logger;


@Mod(WeDoCopyRight.MODID)
public class WeDoCopyRight
{

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

    public WeDoCopyRight(IEventBus modEventBus, ModContainer modContainer)
    {

        if(FMLEnvironment.dist.isClient()){
            modContainer.registerConfig(ModConfig.Type.CLIENT, CRClientConfig.SPEC);
        }
    }

}
