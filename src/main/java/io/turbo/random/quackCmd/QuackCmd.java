package io.turbo.random.quackCmd;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import io.papermc.paper.event.player.AsyncChatEvent;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import io.turbo.random.quackCmd.commands.Quack;
import io.turbo.random.quackCmd.util.FastInvManager;

/*
* @author TurboMaxe
* @since 2/5/2026
*/

public class QuackCmd extends JavaPlugin implements Listener {
    private LiteCommands<CommandSender> liteCommands;
    @Getter
    public static QuackCmd instance;


    @Override
    public void onEnable() {
        this.liteCommands = LiteBukkitFactory.builder("my-plugin", this)
                .commands(new Quack())
                .build();

        FastInvManager.register(this);
    }

    @Override
    public void onDisable() {
        if (this.liteCommands != null) {
            this.liteCommands.unregister();
        }
    }
}