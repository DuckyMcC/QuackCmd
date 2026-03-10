package io.turbo.random.quackCmd.menu;

import io.turbo.random.quackCmd.QuackCmd;
import io.turbo.random.quackCmd.config.Config;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import io.turbo.random.quackCmd.item.ItemBuilder;
import io.turbo.random.quackCmd.util.FastInv;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QuackMenu extends FastInv {
    @Getter
    private int clickCount = 0;

    int[] slots = {27, 28, 29, 33, 34};

    public QuackMenu() {
        super(36, ChatColor.GOLD + "ǫᴜᴀᴄᴋ ᴍᴇɴᴜ");
        setItem(13, new ItemBuilder(Material.YELLOW_SHULKER_BOX).name(ChatColor.BOLD + "" + ChatColor.YELLOW + "ǫᴜᴀᴄᴋ!").lore(ChatColor.WHITE + "Press me!").build(),
                e -> e.getWhoClicked()
                        .sendMessage(Component.text("Quack!")
                                .color(TextColor.color(0xFFF914))));

        setItems(slots, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build());
        setItem(31, new ItemBuilder(Material.CLOCK).name(ChatColor.BLUE + "ѕᴛᴀᴛѕ").lore(ChatColor.WHITE + "You have clicked 0 times!").build());
        setItem(35, new ItemBuilder(Material.NETHER_STAR).name(ChatColor.RED + "ᴄʟᴏsᴇ").build(), e -> {
            e.getWhoClicked().closeInventory();
        });
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "Opened duck menu!");

        setItem(30, new ItemBuilder(Material.LIME_CANDLE).name(ChatColor.GRAY + "ᴜѕᴇʀɴᴀᴍᴇ").lore(ChatColor.DARK_GRAY + event.getPlayer().getName()).build());
        setItem(32, new ItemBuilder(Material.RED_CANDLE).name(ChatColor.GRAY + "ᴜᴜɪᴅ").lore(ChatColor.DARK_GRAY + event.getPlayer().getUniqueId().toString()).build());
        setItem(27, Config.getInstance().initLbItem().build());
    }

    @Override
    public void onClick(InventoryClickEvent event) {

        if (event.getSlot() == 13) {
            clickCount++;
            String grammar = (clickCount == 1) ? "time" : "times";
            setItem(31, new ItemBuilder(Material.CLOCK).name(ChatColor.BLUE + "ѕᴛᴀᴛѕ").lore(ChatColor.WHITE + "You clicked: " + clickCount + " " + grammar + "!\n" + "User: " + event.getWhoClicked().getName()).build());
            QuackCmd.getInstance().saveConfig();
        }
    }

    @Override
    public void onClose(InventoryCloseEvent event) {

        FileConfiguration config = QuackCmd.getInstance().getConfig();
        Config configManager = Config.getInstance();
        int finalclicks = clickCount;

        int configclicks = config.getInt("entries." + event.getPlayer().getName() + ".clicks");
        if (finalclicks > configclicks) {
            config.set("entries." + event.getPlayer().getName() + ".clicks", clickCount);
            QuackCmd.getInstance().saveConfig();
            configManager.updateCache();
        }
    }
}