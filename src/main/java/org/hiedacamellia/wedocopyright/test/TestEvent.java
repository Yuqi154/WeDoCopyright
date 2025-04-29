package org.hiedacamellia.wedocopyright.test;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.hiedacamellia.wedocopyright.api.event.AddCopyRightPageEvent;

@Mod.EventBusSubscriber
public class TestEvent {

    @SubscribeEvent
    public static void onAddPage(AddCopyRightPageEvent event){
//        event.add(CopyRight.rl("textures/gui/test.png"),907,447);
//        event.add(Component.literal("Copyright @ Houraisan Kaguya"));
    }
}
