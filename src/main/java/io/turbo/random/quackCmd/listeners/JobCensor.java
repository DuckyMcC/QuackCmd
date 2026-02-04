package io.turbo.random.quackCmd.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import io.turbo.random.quackCmd.QuackCmd;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachment;

public class JobCensor implements Listener {
    public QuackCmd plugin;

    @EventHandler
    public void onPlayerSwear(AsyncChatEvent event) {

        Player player = event.getPlayer();
        PermissionAttachment attachment = player.addAttachment(plugin.getInstance());
        String playername = player.getName();
        /*
         * Converts the component
         * message sent by player to
         * a string so it can be read
         */

        Component msg = event.message();
        final String legacy = LegacyComponentSerializer.legacyAmpersand().serialize(msg);

        final TextComponent JobComponent = Component.text()
                .append(
                        Component.text().content(" ----------------------------\n")
                                .color(NamedTextColor.GRAY)
                                .decorate(TextDecoration.BOLD)
                )
                .append(
                        Component.text().content(" [")
                                .color(NamedTextColor.DARK_GRAY)
                )
                .append(
                        Component.text().content("✘")
                                .color(NamedTextColor.RED)
                                .decorate(TextDecoration.BOLD)
                )
                .append(
                        Component.text().content("]").color(NamedTextColor.DARK_GRAY)
                                .build()
                )
                .appendSpace()
                .append(
                        Component.text(" Please censor the word").color(TextColor.color(0xFFFFFF))
                )
                .appendSpace()
                .append(
                        Component.text("'job'\n").color(TextColor.color(0xE90800))
                                .decorate(TextDecoration.ITALIC)
                )
                .append(
                        Component.text(" Do not mention empl*yment.\n").color(TextColor.color(0xB4B4B4))
                                .decorate(TextDecoration.ITALIC)
                )
                .append(
                        Component.text(" ----------------------------").color(NamedTextColor.GRAY)
                )
                .build();

        if (legacy.toLowerCase().contains("job") && !player.hasPermission("testplugin.jobcensor")) {
            player.getServer().getGlobalRegionScheduler().run(plugin.getInstance(), (task) -> {
                player.sendMessage(JobComponent);
                Bukkit.getConsoleSender().sendMessage(playername + " " + "has said the forbidden word! (J*b)!");
                event.setCancelled(true);
            });
        }
    }
}
