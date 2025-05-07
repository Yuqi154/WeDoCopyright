package org.hiedacamellia.wedocopyright.api.event;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.Event;
import org.hiedacamellia.wedocopyright.client.gui.widget.CopyRightComponentWidget;
import org.hiedacamellia.wedocopyright.client.gui.widget.CopyRightImageWidget;
import org.hiedacamellia.wedocopyright.client.gui.widget.CopyRightWidget;

import java.util.List;

public class AddCopyRightPageEvent extends Event {

    private final List<CopyRightWidget> widgets;

    public AddCopyRightPageEvent(List<CopyRightWidget> widgets) {
        this.widgets = widgets;
    }
    public void add(ResourceLocation location, int w, int h) {
        widgets.add(CopyRightImageWidget.create(location, w, h));
    }
    public void add(List<Component> components) {
        widgets.add(CopyRightComponentWidget.create(components));
    }
    public void add(Component... components) {
        widgets.add(CopyRightComponentWidget.create(components));
    }
}
