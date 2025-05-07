package org.hiedacamellia.wedocopyright.client.mixin;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import org.hiedacamellia.wedocopyright.WeDoCopyRight;
import org.hiedacamellia.wedocopyright.client.gui.screen.CopyRightScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow @Nullable public Screen screen;

    @Inject(method = "setScreen",at = @At("HEAD"), cancellable = true)
    private void onSetScreen(Screen screen, CallbackInfo ci) {
        if(screen instanceof TitleScreen titleScreen && !WeDoCopyRight.isShown()){
            ci.cancel();
            ((Minecraft)(Object)this).setScreen(new CopyRightScreen(titleScreen));
        }
    }

}
