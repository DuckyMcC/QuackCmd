package io.turbo.random.quackCmd.commands.subcommand;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import io.turbo.random.quackCmd.config.Config;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;

@Command(name = "quack clearcache")
@Permission("quackcmd.admin.clearcache")
public class ClearCache {
    @Execute()
    void execute(@Context CommandSender sender) {
        Config.getInstance().clearCache();
        sender.sendMessage(Component.text("Successfully cleared the cache!").color(TextColor.color(0x52FF00)));
    }
}
