package com.theendercore.halfed_life.mixin;

import com.theendercore.halfed_life.HalfedLife;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(MinecraftClient.class)
public class Template {

    @Inject(at = @At("HEAD"), method = "run")
    private void run(CallbackInfo info) {
        HalfedLife.LOGGER.info("Hello from Mixin");
    }
}
