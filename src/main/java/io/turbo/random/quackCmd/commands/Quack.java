package io.turbo.random.quackCmd.commands;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import io.turbo.random.quackCmd.menu.QuackMenu;

/*
 * @author TurboMaxe
 * @since 2/5/2026
 *
 */

@Command(name = "quack")
@Permission("quackcmd.quack")
public class Quack {
       @Execute()
        void oncommand(@Context CommandSender sender) {

            if (sender instanceof Player player) {
                new QuackMenu().open(player);
            }
        }
    }
