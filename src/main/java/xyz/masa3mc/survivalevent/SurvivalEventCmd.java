package xyz.masa3mc.survivalevent;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SurvivalEventCmd implements Listener, CommandExecutor {

    SurvivalEvent plugin = JavaPlugin.getPlugin(SurvivalEvent.class);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("event")) {
            Player p = (Player) sender;
            gui(p);
        }
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("§c§lバレンタイン§a§l納品§e§lクエスト")) {
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            if (e.getCurrentItem() != null)
                if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
                    p.closeInventory();
                } else if (e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                    File file = new File(plugin.getDataFolder(), "players" + File.separator + p.getUniqueId() + ".yml");
                    FileConfiguration playerConf = YamlConfiguration.loadConfiguration(file);
                    int playerCount = playerConf.getInt("count");
                    contentsGui(p, playerCount);
                }
        } else if (e.getView().getTitle().contains("箇所目の納品リスト")) {
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            if (e.getCurrentItem() != null && e.getCurrentItem().getType().equals(Material.BARRIER))
                gui(p);
        }
    }

    private void gui(Player p) {
        File file = new File(plugin.getDataFolder(), "players" + File.separator + p.getUniqueId() + ".yml");
        FileConfiguration playerConf = YamlConfiguration.loadConfiguration(file);
        int playerCount = playerConf.getInt("count");
        SurvivalEventListener survivalEventListener = new SurvivalEventListener();
        Inventory inv = Bukkit.createInventory(null, 18, "§c§lバレンタイン§a§l納品§e§lクエスト");
        for (int i = 0; i <= playerCount; i++) {
            ItemStack item;
            if (i < playerCount) item = survivalEventListener.returnItem(i);
            else {
                item = notOpened(i);
            }
            if (i != 10)
                inv.setItem(i, item);
        }
        inv.setItem(17, close(1));

        p.openInventory(inv);
    }

    ItemStack notOpened(int i) {
        ItemStack noEntry = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = noEntry.getItemMeta();
        meta.setDisplayName("§a" + (i + 1) + "箇所目");
        List<String> lore = new ArrayList<>();
        switch (i) {
            case 0:
                lore.add("buriburi村のｸｿﾈﾐ丸さんに届けよう");
                break;
            case 1:
                lore.addAll(Arrays.asList("fugafugaさんに届けよう。", "彼はhogehoge町にいる。"));
                break;
        }
        lore.add("§6クリックして納品リストを表示");
        meta.setLore(lore);
        noEntry.setItemMeta(meta);
        return noEntry;
    }

    //未開放アイコンをクリックした際の納品アイテムリストを表示
    private void contentsGui(Player p, int i) {
        Inventory inv = Bukkit.createInventory(null, 18, (i + 1) + "箇所目の納品リスト");
        SurvivalEventListener survivalEventListener = new SurvivalEventListener();
        int index = 0;
        for (ItemStack item : survivalEventListener.items(i)) {
            inv.setItem(index, item);
            index++;
        }
        inv.setItem(17, close(0));

        p.openInventory(inv);
    }

    ItemStack close(int i) {
        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta meta = barrier.getItemMeta();
        switch (i) {
            case 0:
                meta.setDisplayName("§c戻る");
                break;
            case 1:
                meta.setDisplayName("§c閉じる");
                break;
        }
        barrier.setItemMeta(meta);
        return barrier;
    }
}
