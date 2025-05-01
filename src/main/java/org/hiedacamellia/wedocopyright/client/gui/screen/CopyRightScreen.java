package org.hiedacamellia.wedocopyright.client.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.MinecraftForge;
import org.hiedacamellia.wedocopyright.WeDoCopyRight;
import org.hiedacamellia.wedocopyright.api.event.AddCopyRightPageEvent;
import org.hiedacamellia.wedocopyright.api.kubejs.CREventPoster;
import org.hiedacamellia.wedocopyright.client.config.CRClientConfig;
import org.hiedacamellia.wedocopyright.client.gui.widget.CopyRightModsWidget;
import org.hiedacamellia.wedocopyright.client.gui.widget.CopyRightWidget;

import java.util.ArrayList;
import java.util.List;

public class CopyRightScreen extends Screen {

    private final Screen title;
    private final List<CopyRightWidget> widgets;
    private int page = 0;
    private float time=0;

    public CopyRightScreen(Screen title) {
        super(Component.literal("Copyright"));
        this.title = title;
        this.widgets = new ArrayList<>();
        AddCopyRightPageEvent event = new AddCopyRightPageEvent(widgets);
        MinecraftForge.EVENT_BUS.post(event);
        if(WeDoCopyRight.kubeJsLoaded)
            CREventPoster.INSTANCE.post(event);

        if (CRClientConfig.ShowModLogos.get()){
            this.widgets.add(CopyRightModsWidget.create());
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(time > 20f){
            page++;
            time = 0;
        }
        if(page >= widgets.size()){
            onClose();
            WeDoCopyRight.setShown();
            Minecraft.getInstance().setScreen(title);
        }
        return true;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.fill(0, 0, width, height, 0xFF000000);
        if(page<widgets.size())
            widgets.get(page).render(guiGraphics, mouseX, mouseY, partialTick);
        time += partialTick;
    }
}
