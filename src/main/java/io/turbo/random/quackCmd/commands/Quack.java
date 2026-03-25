package io.turbo.random.quackCmd.commands;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import io.turbo.random.quackCmd.menu.QuackMenu;

/*
 * Created on 2/5/2026
 *
 * @author TurboMaxe
 */

@Command(name = "quack")
@Permission("quackcmd.quack")
public class Quack {
       
       @Execute()
        void on(@Context CommandSender sender) {
            if (sender instanceof Player player) { 
              new QuackMenu().open(player); 
            }
        }
    }
