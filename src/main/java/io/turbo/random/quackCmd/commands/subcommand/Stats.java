package io.turbo.random.quackCmd.commands.subcommand;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import io.turbo.random.quackCmd.QuackCmd;
import io.turbo.random.quackCmd.config.Config;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
@Command(name = "quack stats")
@Permission("quackcmd.quack.stats")
    public class Stats {


       @Execute()
        void execute(@Context CommandSender sender) {
        String clickstats = Config.getInstance().getEntrybyName(sender.getName());
        sender.sendMessage(ChatColor.WHITE + "Your statistics. " + ChatColor.YELLOW + sender.getName() + ChatColor.WHITE + "\n - " + ChatColor.GRAY + clickstats + " clicks!");
        }

        @Execute()
        void execute(@Context CommandSender sender, @Arg String username) {
            String clickstats = Config.getInstance().getEntrybyName(username);
            sender.sendMessage(ChatColor.WHITE + "Showing statistics of player. " + ChatColor.YELLOW + username + ChatColor.WHITE + "\n - " + ChatColor.GRAY + clickstats + " clicks!");
       }
}
