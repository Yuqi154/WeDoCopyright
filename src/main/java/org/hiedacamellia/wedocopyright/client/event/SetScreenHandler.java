package org.hiedacamellia.wedocopyright.client.event;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import org.hiedacamellia.wedocopyright.WeDoCopyRight;
import org.hiedacamellia.wedocopyright.client.gui.screen.CopyRightScreen;
import org.hiedacamellia.wedocopyright.client.util.ValueHolder;

public class SetScreenHandler {

    public static void onSetScreen(ValueHolder<Screen> event){
        if(event.get() instanceof TitleScreen screen && !WeDoCopyRight.isShown()){
            event.set(new CopyRightScreen(screen));
        }
    }
}
