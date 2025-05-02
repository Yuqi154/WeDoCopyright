package org.hiedacamellia.wedocopyright.api.kubejs;


import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.hiedacamellia.wedocopyright.api.event.AddCopyRightPageEvent;

import java.util.List;

public  class AddCopyRightPageEventJS extends EventJS {

    public final AddCopyRightPageEvent event;

    public AddCopyRightPageEventJS(AddCopyRightPageEvent event) {
        this.event = event;
    }
    public void addImage(ResourceLocation location, int w, int h) {
        event.add(location, w, h);
    }
    public void add(List<Component> components) {
        event.add(components);
    }
    public void add(Component... components) {
        event.add(components);
    }
}
