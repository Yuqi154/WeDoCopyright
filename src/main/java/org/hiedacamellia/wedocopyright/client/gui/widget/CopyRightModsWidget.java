package org.hiedacamellia.wedocopyright.client.gui.widget;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.platform.Window;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.tuple.Pair;
import org.hiedacamellia.wedocopyright.WeDoCopyRight;
import org.hiedacamellia.wedocopyright.client.config.CRConfig;
import org.joml.Vector2i;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class CopyRightModsWidget extends CopyRightWidget {

    private final List<Logo> logos = new ArrayList<>();

    private static int MAX_HEIGHT = 30;

    private CopyRightModsWidget(int x, int y, int width, int height, Component message, List<Logo> logos) {
        super(x, y, width, height, message);
        this.logos.addAll(logos);
    }

    record Logo(ResourceLocation texture, Vector2i size, Vector2i position) {
        public void offsetX(int x) {
            position.x += x;
        }

        public void offsetY(int y) {
            position.y += y;
        }

        public void draw(GuiGraphics guiGraphics){
            guiGraphics.blit(texture, position.x, position.y, MAX_HEIGHT *size.x/size.y,MAX_HEIGHT,0,0, size.x, size.y,size.x, size.y);
        }
    }

    public static CopyRightModsWidget create() {
        Window window = Minecraft.getInstance().getWindow();
        int guiScaledWidth = window.getGuiScaledWidth();
        int guiScaledHeight = window.getGuiScaledHeight();
        List<Pair<ResourceLocation, Vector2i>> logos = new ArrayList<>();

        FabricLoader.getInstance().getAllMods().forEach(modInfo -> {
                    Pair<ResourceLocation, Vector2i> pair = modInfo.getMetadata().getIconPath(64 * Minecraft.getInstance().options.guiScale().get()).map(logoFile ->
                    {
                        if(modInfo.getMetadata().getId().contains("fabric")&& CRConfig.get().IgnoreFabricApi) {
                            return Pair.<ResourceLocation, Vector2i>of(null, new Vector2i(0, 0));
                        }
                        TextureManager tm = Minecraft.getInstance().getTextureManager();
                        try {
                            DynamicTexture texture = createIcon(modInfo, logoFile);
                            ResourceLocation resourceLocation =  WeDoCopyRight.rl(modInfo.getMetadata().getId()+"_logo");
                            tm.register(resourceLocation,texture);
                            return Pair.of(resourceLocation, new Vector2i(texture.getPixels().getWidth(), texture.getPixels().getHeight()));
                        } catch (Exception ignored) {
                        }
                        return Pair.<ResourceLocation, Vector2i>of(null, new Vector2i(0, 0));
                    }).orElse(Pair.of(null, new Vector2i(0, 0)));
                    if(pair.getLeft() != null) {
                        logos.add(pair);
                    }
                }
        );

        List<Logo> all;
        while ((all = cal(logos, guiScaledWidth, guiScaledHeight)) == null) {
            WeDoCopyRight.LOGGER.debug("Logos too big, try to reduce the size");
        }

        return new CopyRightModsWidget(0, 0, guiScaledWidth, guiScaledHeight, Component.empty(), all);
    }

    private static List<Logo> cal(List<Pair<ResourceLocation, Vector2i>> logos,int guiScaledWidth,int guiScaledHeight){
        int w=0;
        int h=0;
        List<Logo> all = new ArrayList<>();
        List<Logo> line = new ArrayList<>();
        for (int i = 0; i < logos.size(); i++) {
            ResourceLocation left = logos.get(i).getLeft();
            Vector2i size = logos.get(i).getRight();
            int logowidth = MAX_HEIGHT*size.x/size.y ;
            if(logowidth+w<=guiScaledWidth){
                line.add(new Logo(left, size, new Vector2i(w, h)));
                w+=logowidth;
            }else{
                int offset = (guiScaledWidth-w)/2;
                line.forEach(offsetLogo -> offsetLogo.offsetX(offset));
                all.addAll(line);
                line.clear();
                w=0;
                h+=MAX_HEIGHT;
                line.add(new Logo(left, size, new Vector2i(w, h)));
                w+=logowidth;
            }
        }
        if(!line.isEmpty()){
            int offset = (guiScaledWidth-w)/2;
            line.forEach(offsetLogo -> offsetLogo.offsetX(offset));
            all.addAll(line);
        }
        if(h+MAX_HEIGHT>guiScaledHeight){
            MAX_HEIGHT-=5;
            if(MAX_HEIGHT<=0){
                WeDoCopyRight.LOGGER.error("Can't fit all logos in the screen");
                return all;
            }
            return null;
        }else{
            int offset = (guiScaledHeight-h)/2 - MAX_HEIGHT/2;
            all.forEach(offsetLogo -> offsetLogo.offsetY(offset));
        }
        return all;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int i, int i1, float v) {
        logos.forEach(logo -> logo.draw(guiGraphics));
    }

    private static DynamicTexture createIcon(ModContainer iconSource, String iconPath) {
        try {
            Path path = iconSource.getPath(iconPath);
            DynamicTexture var8;
            try (InputStream inputStream = Files.newInputStream(path)) {
                NativeImage image = NativeImage.read((InputStream) Objects.requireNonNull(inputStream));
                Validate.validState(image.getHeight() == image.getWidth(), "Must be square icon", new Object[0]);
                DynamicTexture tex = new DynamicTexture(image);
                var8 = tex;
            }

            return var8;
        } catch (Throwable t) {
            if (!iconPath.equals("assets/" + iconSource.getMetadata().getId() + "/icon.png")) {
                WeDoCopyRight.LOGGER.error("Invalid mod icon for icon source {}: {}", new Object[]{iconSource.getMetadata().getId(), iconPath, t});
            }

            return null;
        }
    }
}
