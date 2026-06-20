package com.driftmod.command;

import com.driftmod.DriftData;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class DriftCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("Drift")
                .requires(source -> source.hasPermission(0))
                .then(
                    Commands.argument("enable", BoolArgumentType.bool())
                        .executes(DriftCommand::executeSet)
                )
                .executes(DriftCommand::executeToggle)
        );
    }

    private static int executeSet(CommandContext<CommandSourceStack> context) {
        boolean enable = BoolArgumentType.getBool(context, "enable");
        ServerPlayer player = context.getSource().getPlayerOrException();

        DriftData.setDriftEnabled(player.getUUID(), enable);

        String status = enable ? "§a开启" : "§c关闭";
        player.sendSystemMessage(
            Component.literal("§6[Drift] §f飞行惯性已" + status + "§f！")
        );

        return 1;
    }

    private static int executeToggle(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayerOrException();

        boolean newState = DriftData.toggleDrift(player.getUUID());

        String status = newState ? "§a开启" : "§c关闭";
        player.sendSystemMessage(
            Component.literal("§6[Drift] §f飞行惯性已切换为" + status + "§f！")
        );

        return 1;
    }
}