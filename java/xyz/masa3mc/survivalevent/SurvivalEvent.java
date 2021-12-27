package xyz.masa3mc.survivalevent;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class SurvivalEvent extends JavaPlugin implements Listener {

    public File configFile;
    public FileConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        SurvivalEventCmd survivalEventCmd = new SurvivalEventCmd();
        getServer().getPluginManager().registerEvents(new SurvivalEventListener(), this);
        getServer().getPluginManager().registerEvents(survivalEventCmd, this);
        getCommand("event").setExecutor(survivalEventCmd);
        createFiles();
    }

    public void createFiles() {
        configFile = new File(this.getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            this.saveResource("config.yml", false);
        }
        config = new YamlConfiguration();

        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
