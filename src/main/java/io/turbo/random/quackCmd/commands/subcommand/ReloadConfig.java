package io.turbo.random.quackCmd.commands.subcommand;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import io.turbo.random.quackCmd.config.Config;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;


@Command(name = "quack reloadconfig")
@Permission("quackcmd.admin.reloadconfig")
public class ReloadConfig {
        @Execute()
        void execute(@Context CommandSender sender) {
            Config.getInstance().reloadConfig();
            sender.sendMessage(Component.text("Successfully reloaded config!").color(TextColor.color(0x52FF00)));

    }
}
