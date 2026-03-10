package io.turbo.random.quackCmd.config;

import io.turbo.random.quackCmd.QuackCmd;
import io.turbo.random.quackCmd.item.ItemBuilder;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.*;

public class Config {

    private FileConfiguration config;
    private static QuackCmd quackCmd;
    @Getter private List<Map.Entry<String, Integer>> leaderboardCache = new ArrayList<>();
    @Getter private static Config instance;
    public ItemBuilder leaderboard;
    private FileConfiguration cfg;
    public static List<String> lblore;

    public Config(QuackCmd plugin) {
        this.quackCmd = plugin;
        instance = this;
    }

    public void reloadConfig() {
        YamlConfiguration.loadConfiguration(new File(QuackCmd.getInstance().getDataFolder(), "config.yml"));
        quackCmd.reloadConfig();
        this.cfg = quackCmd.getConfig();
    }

    public String getEntrybyName(String name) {
        this.config = quackCmd.getInstance().getConfig();
        FileConfiguration conf = quackCmd.getInstance().getConfig();
        String username = String.valueOf(conf.getString("entries." + name + ".clicks"));
        int clicks = config.getInt("entries." + name + ".clicks", 0);
        String clickstostr = Integer.toString(clicks);

        if (clicks == 0) {
            return "This player has no ";
        }

        return clickstostr;
    }

    public void clearCache() {
        if (this.leaderboardCache != null) {
            this.leaderboardCache = new ArrayList<>();
        }
        reloadConfig();
        updateCache();
    }

    public void updateCache() {
        ConfigurationSection section = cfg.getConfigurationSection("entries");

        if (section == null) {
            this.leaderboardCache = new ArrayList<>();
            return;
        }
        section.getConfigurationSection("entries");
        if (section == null) return;

        Map<String, Integer> data = new HashMap<>();
        for (String user : section.getKeys(false)) {

            int clicks = section.getInt(user + ".clicks");
            data.put(user, clicks);
        }

        this.leaderboardCache = new ArrayList<>(data.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .toList());
    }

    public void injectUpdate(String player, int clicks) {
        updateCache();
    }

    public List<Map.Entry<String, Integer>> initLb() {
        reloadConfig();
        this.cfg = QuackCmd.getInstance().getConfig();

        ConfigurationSection section = cfg.getConfigurationSection("entries");
        if (section == null) return Collections.emptyList();

        Map<String, Integer> mappedEntries = new HashMap<>();
        for (String user : section.getKeys(false)) {
            int clicks = section.getInt(user + ".clicks");
            mappedEntries.put(user, clicks);
        }

        return mappedEntries.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .toList();
    }
    public ItemBuilder initLbItem() {
        List<Map.Entry<String, Integer>> leaderboard = initLb();
        lblore = new ArrayList<>();
        int rank = 1;
        ItemBuilder lb = new ItemBuilder(Material.REDSTONE_TORCH)
                .name(ChatColor.LIGHT_PURPLE + "ʟᴇᴀᴅᴇʀʙᴏᴀʀᴅ");
        for (Map.Entry<String, Integer> entry : leaderboard) {
            if (rank > 5) break;
                    lblore.add("§f" + rank + "." +
                            " §e" + entry.getKey() +
                            " §7- §f" + entry.getValue() + " clicks");
            rank++;
        }
        return new ItemBuilder(Material.REDSTONE_TORCH)
                .name("§dLeaderboard")
                .lore(lblore);
    }
}


