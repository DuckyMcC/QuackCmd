package io.turbo.random.quackCmd.commands.subcommand;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import io.turbo.random.quackCmd.QuackCmd;
import io.turbo.random.quackCmd.config.Config;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Map;

@Command(name = "quack leaderboard")
@Permission("quackcmd.quack.leaderboard")
public class Leaderboard {
    @Execute()
    void execute(@Context CommandSender sender) {
        List<Map.Entry<String, Integer>> lb = Config.getInstance().initLb();
        sender.sendMessage(Component.text(" ʟᴇᴀᴅᴇʀʙᴏᴀʀᴅ.").color(TextColor.color(0xFFF914)));
        if (lb.isEmpty()) {
            sender.sendMessage(Component.text("No stats recorded yet!").color(TextColor.color(0xAAAAAA)));
        }

        int rank = 1;
        for (Map.Entry<String, Integer> entry : lb) {
            sender.sendMessage(Component.text(rank + ". ")
                    .append(Component.text(entry.getKey()).color(TextColor.color(0xFFFFFF)))
                    .append(Component.text(" - ").color(TextColor.color(0x777777)))
                    .append(Component.text(entry.getValue() + " clicks").color(TextColor.color(0xFFDD00))));
            rank++;
        }
    }
}
