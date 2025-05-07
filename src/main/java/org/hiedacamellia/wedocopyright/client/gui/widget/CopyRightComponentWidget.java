package org.hiedacamellia.wedocopyright.client.gui.widget;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import java.util.List;

public final class CopyRightComponentWidget extends CopyRightWidget{

    private final Component[] message;
    private static final Font font = Minecraft.getInstance().font;;

    private CopyRightComponentWidget(int x, int y, int width, int height, Component message, Component[] components) {
        super(x, y, width, height, message);
        this.message = components;
    }

    public static CopyRightComponentWidget create(List<Component> message) {
        return create(message.toArray(new Component[0]));
    }
    public static CopyRightComponentWidget create(Component... message) {
        Window window = Minecraft.getInstance().getWindow();
        int guiScaledWidth = window.getGuiScaledWidth();
        int guiScaledHeight = window.getGuiScaledHeight();
        return new CopyRightComponentWidget(0, 0, guiScaledWidth, guiScaledHeight,Component.empty(),message);
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int length = message.length;
        int h = length*font.lineHeight;
        int w = getWidth()/2;
        int startY = (getHeight() - h) / 2;
        for (int i = 0; i < message.length; i++) {
            int y = startY + i * font.lineHeight;
            guiGraphics.drawCenteredString(font, message[i], getX() + w, y, 0xFFFFFF);
        }
    }
}
