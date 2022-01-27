package xyz.masa3mc.survivalevent;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.io.IOException;

public final class SurvivalEvent extends JavaPlugin implements Listener {

    public File configFile;
    public FileConfiguration config;

    @Override
    public void onEnable() {
        xyz.masa3mc.survivalevent.SurvivalEventCmd survivalEventCmd = new SurvivalEventCmd();
        getServer().getPluginManager().registerEvents(new SurvivalEventListener(), this);
        getServer().getPluginManager().registerEvents(survivalEventCmd, this);
        getCommand("event").setExecutor(survivalEventCmd);
        createFiles();
        chocolateIgnition();
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

    void chocolateIgnition() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            final SurvivalEventListener listener = new SurvivalEventListener();
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getInventory().getItemInMainHand().getItemMeta() != null && p.getInventory().getItemInMainHand().equals(listener.returnItem(4))) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40, 0));
                    }
                }
            }
        }, 0L, 20L);
    }
}
