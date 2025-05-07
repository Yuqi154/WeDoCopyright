package org.hiedacamellia.wedocopyright.client.event;

import net.minecraft.client.gui.screens.TitleScreen;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;
import org.hiedacamellia.wedocopyright.WeDoCopyRight;
import org.hiedacamellia.wedocopyright.client.gui.screen.CopyRightScreen;

@EventBusSubscriber
public class SetScreenHandler {

    @SubscribeEvent
    public static void onSetScreen(ScreenEvent.Opening event){
        if(event.getNewScreen() instanceof TitleScreen screen && !WeDoCopyRight.isShown()){
            event.setNewScreen(new CopyRightScreen(screen));
        }
    }
}
