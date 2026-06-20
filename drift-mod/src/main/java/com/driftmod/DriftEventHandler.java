package com.driftmod;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DriftMod.MOD_ID)
public class DriftEventHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        // 只在服务端处理，且只在 tick 结束时处理
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide()) return;

        Player player = event.player;

        // 检查玩家是否在飞行
        if (!player.getAbilities().flying) return;

        // 检查玩家是否关闭了飞行惯性
        if (DriftData.isDriftEnabled(player.getUUID())) return;

        // 检查玩家是否有主动输入（按下了移动键）
        boolean hasInput = Math.abs(player.xxa) > 0.001f  // 左右移动
                        || Math.abs(player.zza) > 0.001f; // 前后移动

        if (!hasInput) {
            // 没有输入时，立即停止所有移动
            player.setDeltaMovement(0, 0, 0);
        }
    }
}