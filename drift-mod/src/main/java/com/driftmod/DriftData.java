package com.driftmod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DriftData {

    private static final Map<UUID, Boolean> playerDriftState = new HashMap<>();

    /**
     * 设置玩家的飞行惯性状态
     * @param playerId 玩家UUID
     * @param driftEnabled true=开启飞行惯性, false=关闭飞行惯性
     */
    public static void setDriftEnabled(UUID playerId, boolean driftEnabled) {
        playerDriftState.put(playerId, driftEnabled);
    }

    /**
     * 获取玩家的飞行惯性状态，默认为 true（开启）
     * @param playerId 玩家UUID
     * @return true=开启飞行惯性, false=关闭
     */
    public static boolean isDriftEnabled(UUID playerId) {
        return playerDriftState.getOrDefault(playerId, true);
    }

    /**
     * 切换玩家的飞行惯性状态
     * @param playerId 玩家UUID
     * @return 切换后的状态
     */
    public static boolean toggleDrift(UUID playerId) {
        boolean current = isDriftEnabled(playerId);
        boolean newState = !current;
        setDriftEnabled(playerId, newState);
        return newState;
    }
}