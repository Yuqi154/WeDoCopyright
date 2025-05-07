package org.hiedacamellia.wedocopyright.api.event;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.hiedacamellia.wedocopyright.client.gui.widget.CopyRightComponentWidget;
import org.hiedacamellia.wedocopyright.client.gui.widget.CopyRightImageWidget;
import org.hiedacamellia.wedocopyright.client.gui.widget.CopyRightWidget;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AddCopyRightPageEvent {


    private static final List<Consumer<AddCopyRightPageEvent>> HANDLERS = new ArrayList<>();

    public static void register(Consumer<AddCopyRightPageEvent> handler) {
        HANDLERS.add(handler);
    }

    public static void post(AddCopyRightPageEvent event) {
        for (Consumer<AddCopyRightPageEvent> handler : HANDLERS) {
            handler.accept(event);
        }
    }

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
