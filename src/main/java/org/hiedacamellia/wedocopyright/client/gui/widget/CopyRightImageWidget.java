package org.hiedacamellia.wedocopyright.client.gui.widget;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public final class CopyRightImageWidget extends CopyRightWidget{

    private final ResourceLocation location;

    private CopyRightImageWidget(int x, int y, int width, int height, Component message, ResourceLocation location) {
        super(x, y, width, height, message);
        this.location = location;
    }

    public static CopyRightImageWidget create(ResourceLocation location,int w,int h) {
        Window window = Minecraft.getInstance().getWindow();
        int guiScaledWidth = window.getGuiScaledWidth();
        int guiScaledHeight = window.getGuiScaledHeight();
        int x = (guiScaledWidth - w) / 2;
        int y = (guiScaledHeight - h) / 2;
        return new CopyRightImageWidget(x, y, w, h, Component.empty(),location);
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int i, int i1, float v) {
        guiGraphics.blit(location,
                0,
                0,
                Minecraft.getInstance().getWindow().getGuiScaledWidth(),
                Minecraft.getInstance().getWindow().getGuiScaledHeight(),
                0,
                0,
                getWidth(),
                getHeight(),
                getWidth(),
                getHeight());
    }
}
