package org.hiedacamellia.wedocopyright.client.event;

import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.hiedacamellia.wedocopyright.WeDoCopyRight;
import org.hiedacamellia.wedocopyright.client.gui.screen.CopyRightScreen;

@Mod.EventBusSubscriber
public class SetScreenHandler {

    @SubscribeEvent
    public static void onSetScreen(ScreenEvent.Opening event){
        if(event.getNewScreen() instanceof TitleScreen screen && !WeDoCopyRight.isShown()){
            event.setNewScreen(new CopyRightScreen(screen));
        }
    }
}
