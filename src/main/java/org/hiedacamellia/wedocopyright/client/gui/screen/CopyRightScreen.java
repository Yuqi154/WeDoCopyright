package org.hiedacamellia.wedocopyright.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
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
        if(time > 20f * CRClientConfig.LeastTime.get()){
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

        float alpha = 1.0f;
        if(CRClientConfig.FadeIn.get()) {
            float v = 20f * CRClientConfig.AutoNextTime.get();
            if (time < v * 0.5f) {
                alpha = smoothStep(0, v * 0.1f, time);
            } else {
                alpha = 1 - smoothStep(v * 0.9f, v, time);
            }
        }


        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,alpha);

        if(page<widgets.size())
            widgets.get(page).render(guiGraphics, mouseX, mouseY, partialTick);
        else
            keyPressed(0,0,0);

        if(time > 20f * CRClientConfig.AutoNextTime.get()){
            keyPressed(0,0,0);
        }
        time += partialTick;

        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
    }

    private float smoothStep(float start,float end,float v){
        v = Math.max(0,Math.min(1,(v-start)/(end-start)));
        return v*v*(3-2*v);
    }
}
