package github.totorewa.playervisibilityhz.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
	@Inject(at = @At("HEAD"), method = "render(Lnet/minecraft/client/player/AbstractClientPlayer;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", cancellable = true)
	private void render(AbstractClientPlayer player, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo info) {
		if (player != Minecraft.getInstance().player && !player.isSleeping() && player.distanceTo(Minecraft.getInstance().player) < 2.0) {
			info.cancel();
		}
	}
}