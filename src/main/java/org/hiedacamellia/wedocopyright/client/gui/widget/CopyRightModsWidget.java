package org.hiedacamellia.wedocopyright.client.gui.widget;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraftforge.common.util.Size2i;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;
import net.minecraftforge.resource.PathPackResources;
import net.minecraftforge.resource.ResourcePackLoader;
import org.apache.commons.lang3.tuple.Pair;
import org.hiedacamellia.wedocopyright.WeDoCopyRight;
import org.joml.Vector2i;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class CopyRightModsWidget extends CopyRightWidget {

    private final List<Logo> logos = new ArrayList<>();

    private static int MAX_HEIGHT = 30;

    private CopyRightModsWidget(int x, int y, int width, int height, Component message, List<Logo> logos) {
        super(x, y, width, height, message);
        this.logos.addAll(logos);
    }

    record Logo(ResourceLocation texture, Size2i size, Vector2i position) {
        public void offsetX(int x) {
            position.x += x;
        }

        public void offsetY(int y) {
            position.y += y;
        }

        public void draw(GuiGraphics guiGraphics){
            guiGraphics.blit(texture, position.x, position.y, MAX_HEIGHT *size.width/size.height,MAX_HEIGHT,0,0, size.width, size.height,size.width, size.height);
        }
    }

    public static CopyRightModsWidget create() {
        Window window = Minecraft.getInstance().getWindow();
        int guiScaledWidth = window.getGuiScaledWidth();
        int guiScaledHeight = window.getGuiScaledHeight();
        List<Pair<ResourceLocation, Size2i>> logos = new ArrayList<>();
        FMLLoader.getLoadingModList().getMods().forEach(modInfo -> {
                    Pair<ResourceLocation, Size2i> pair = modInfo.getLogoFile().map(logoFile ->
                    {
                        TextureManager tm = Minecraft.getInstance().getTextureManager();
                        final PathPackResources resourcePack = ResourcePackLoader.getPackFor(modInfo.getModId())
                                .orElse(ResourcePackLoader.getPackFor("forge").
                                        orElseThrow(() -> new RuntimeException("Can't find forge, WHAT!")));
                        try {
                            NativeImage logo = null;
                            IoSupplier<InputStream> logoResource = resourcePack.getRootResource(logoFile);
                            if (logoResource != null)
                                logo = NativeImage.read(logoResource.get());
                            if (logo != null) {
                                return Pair.of(tm.register("modlogo", new DynamicTexture(logo) {
                                    @Override
                                    public void upload() {
                                        this.bind();
                                        NativeImage td = this.getPixels();
                                        // Use custom "blur" value which controls texture filtering (nearest-neighbor vs linear)
                                        this.getPixels().upload(0, 0, 0, 0, 0, td.getWidth(), td.getHeight(), modInfo.getLogoBlur(), false, false, false);
                                    }
                                }), new Size2i(logo.getWidth(), logo.getHeight()));
                            }
                        } catch (IOException ignored) {
                        }
                        return Pair.<ResourceLocation, Size2i>of(null, new Size2i(0, 0));
                    }).orElse(Pair.of(null, new Size2i(0, 0)));
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

    private static List<Logo> cal(List<Pair<ResourceLocation, Size2i>> logos,int guiScaledWidth,int guiScaledHeight){
        int w=0;
        int h=0;
        List<Logo> all = new ArrayList<>();
        List<Logo> line = new ArrayList<>();
        for (int i = 0; i < logos.size(); i++) {
            ResourceLocation left = logos.get(i).getLeft();
            Size2i size = logos.get(i).getRight();
            int logowidth = MAX_HEIGHT*size.width/size.height ;
            WeDoCopyRight.LOGGER.error("Logo: "+left+" "+size.width+" "+size.height);
            if(logowidth+w<=guiScaledWidth){
                WeDoCopyRight.LOGGER.error("width: "+w+" height: "+h+" logowidth: "+logowidth);
                line.add(new Logo(left, size, new Vector2i(w, h)));
                w+=logowidth;
            }else{
                int offset = (guiScaledWidth-w)/2;
                line.forEach(offsetLogo -> offsetLogo.offsetX(offset));
                all.addAll(line);
                line.clear();
                w=0;
                h+=MAX_HEIGHT;
                WeDoCopyRight.LOGGER.error("width: "+w+" height: "+h+" logowidth: "+logowidth);
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
}
