package xyz.masa3mc.survivalevent.archive;

import de.tr7zw.nbtapi.NBTItem;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.masa3mc.survivalevent.SurvivalEvent;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SurvivalEventListener implements Listener {

    SurvivalEvent plugin = SurvivalEvent.getPlugin(SurvivalEvent.class);
    String prefix = "§7[§2Event§7]";

    @EventHandler
    public void onRegionEnter(RegionEnteredEvent e) {
        Player p = e.getPlayer();
        File file = new File(plugin.getDataFolder(), "players" + File.separator + p.getUniqueId() + ".yml");
        FileConfiguration playerConf = YamlConfiguration.loadConfiguration(file);
        //入った領域がリストに含まれている領域、かつ初めて入った場合
        if (plugin.config.getStringList("regions").contains(e.getRegionName()))
            if (!playerConf.getStringList("entries").contains(e.getRegionName())) {
                List<String> entry = new ArrayList<>();
                entry.add(e.getRegionName());
                entry.addAll(playerConf.getStringList("entries"));
                playerConf.set("entries", entry);
                //アイテム付与とメッセージ
                if (!playerConf.contains("count")) playerConf.set("count", 0);
                int count = playerConf.getInt("count");
                count += 1;
                playerConf.set("count", count);
                if (count == 14) {
                    RandomOrnament(p);
                } else {
                    p.getInventory().addItem(giveItem(count));
                    p.sendMessage(prefix + "§6" + count + "箇所目§aのスタンプを回収！イベントメニューを確認しよう！");
                    if (giveItem(count).getType().equals(Material.DIAMOND)) {
                        p.sendMessage(prefix + "§a獲得: ダイヤモンド§6" + giveItem(count).getAmount() + "§a個");
                    } else if (giveItem(count).getType().equals(Material.NETHERITE_INGOT)) {
                        p.sendMessage(prefix + "§a獲得: ネザライトインゴット§6" + giveItem(count).getAmount() + "§a個");
                    } else {
                        p.sendMessage(prefix + "§a獲得: " + giveItem(count).getItemMeta().getDisplayName() + "§6" + giveItem(count).getAmount() + "§a個");
                    }
                }
                try {
                    playerConf.save(file);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
    }

    public ItemStack giveItem(int i) {
        ItemStack item = null;
        ItemMeta meta;
        switch (i) {
            case 1:
                item = new ItemStack(Material.CAKE);
                meta = item.getItemMeta();
                meta.setDisplayName("§c§lク§r§lリ§c§lス§r§lマ§c§lス§r§lケ§c§lー§r§lキ");
                meta.setLore(Collections.singletonList("とってもあまくておいしい"));
                meta.addEnchant(Enchantment.LUCK, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.setItemMeta(meta);
                break;
            case 2:
                item = new ItemStack(Material.DIAMOND, 3);
                break;
            case 3:
                item = new ItemStack(Material.RED_CANDLE);
                meta = item.getItemMeta();
                meta.setDisplayName("§a§k|§e§lケーキ用のろうそく§a§k|");
                meta.setLore(Collections.singletonList("ケーキに飾ろう。"));
                meta.addEnchant(Enchantment.LUCK, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.setItemMeta(meta);
                break;
            case 4:
                item = new ItemStack(Material.DIAMOND, 3);
                break;
            case 5:
                item = new ItemStack(Material.COOKED_CHICKEN);
                meta = item.getItemMeta();
                meta.setDisplayName("§6§l七面鳥の丸焼き");
                List<String> turkeyLore = new ArrayList<>();
                turkeyLore.add("甘辛いタレによって");
                turkeyLore.add("おいしそうに照り焼かれている。");
                meta.addEnchant(Enchantment.LUCK, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                meta.setLore(turkeyLore);
                item.setItemMeta(meta);
                break;
            case 6:
                item = new ItemStack(Material.DIAMOND, 3);
                break;
            case 7:
                item = new ItemStack(Material.BIRCH_BOAT);
                meta = item.getItemMeta();
                meta.setDisplayName("§b§lトナカイのソリ");
                meta.setLore(Collections.singletonList("耳をすませば鈴の音が聞こえる。"));
                meta.addEnchant(Enchantment.LUCK, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.setItemMeta(meta);
                break;
            case 8:
                item = new ItemStack(Material.DIAMOND, 5);
                break;
            case 9:
                item = new ItemStack(Material.LEATHER_BOOTS);
                meta = item.getItemMeta();
                meta.setDisplayName("§c§lサ§r§lン§c§lタ§r§lク§c§lロ§r§lー§c§lス§r§lの§c§l靴");
                meta.setLore(Collections.singletonList("これで雪の上もへっちゃらさ。"));
                meta.addEnchant(Enchantment.LUCK, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.setItemMeta(meta);
                break;
            case 10:
                item = new ItemStack(Material.CHEST);
                meta = item.getItemMeta();
                meta.setDisplayName("§6§lクリスマスプレゼント");
                meta.addEnchant(Enchantment.LUCK, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.setItemMeta(meta);
                //nbt関係
                NBTItem nbtItem = new NBTItem(item);
                nbtItem.setString("Chest", "Chest");
                nbtItem.applyNBT(item);
                break;
            case 11:
                item = new ItemStack(Material.NETHERITE_INGOT);
                break;
            case 12:
                item = SpruceSapling();
                break;
            case 13:
                item = new ItemStack(Material.NETHERITE_INGOT);
                break;
            case 15:
                item = new ItemStack(Material.NETHERITE_INGOT, 2);
                break;
            case 16:
                item = new ItemStack(Material.GLOWSTONE, 3);
                meta = item.getItemMeta();
                meta.setDisplayName("§e§lイルミネーション");
                meta.setLore(Collections.singletonList("クリスマスツリーに飾ろう"));
                meta.addEnchant(Enchantment.LUCK, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.setItemMeta(meta);
                break;
            case 17:
                item = new ItemStack(Material.NETHERITE_INGOT, 2);
                break;
            case 18:
                item = new ItemStack(Material.GOLD_BLOCK);
                meta = item.getItemMeta();
                meta.setDisplayName("§e§lツリーの星");
                List<String> lore = new ArrayList<>();
                lore.add("クリスマスツリーに飾ろう。");
                lore.add("正式名称はベツレヘムの星なんだって。");
                meta.setLore(lore);
                meta.addEnchant(Enchantment.LUCK, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.setItemMeta(meta);
                break;
            case 19:
                item = new ItemStack(Material.NETHERITE_INGOT, 3);
                break;
            case 20:
                item = new ItemStack(Material.NETHERITE_PICKAXE);
                meta = item.getItemMeta();
                meta.setDisplayName("§c§k| §6§lX'mas Pickaxe §c§k|");
                meta.setLore(Collections.singletonList("サンタさんからの贈り物"));
                meta.addEnchant(Enchantment.DIG_SPEED, 12, true);
                meta.addEnchant(Enchantment.DURABILITY, 2, true);
                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 5, true);
                meta.addEnchant(Enchantment.MENDING, 1, true);
                item.setItemMeta(meta);
                break;
        }
        return item;
    }

    private static ItemStack SpruceSapling() {
        ItemStack item = new ItemStack(Material.SPRUCE_SAPLING);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§c§lク§r§lリ§c§lス§r§lマ§c§lス§r§lツ§c§lリ§r§lー");
        List<String> lore = new ArrayList<>();
        lore.add("クリスマス用にNameKunが");
        lore.add("クリエ鯖から引っ張ってきてくれた。");
        lore.add("土ブロックに植えると");
        lore.add("一瞬で成長する。");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public void RandomOrnament(Player p) {
        List<Material> wools = new ArrayList<>();
        wools.add(Material.WHITE_WOOL);
        wools.add(Material.ORANGE_WOOL);
        wools.add(Material.MAGENTA_WOOL);
        wools.add(Material.LIGHT_BLUE_WOOL);
        wools.add(Material.YELLOW_WOOL);
        wools.add(Material.LIME_WOOL);
        wools.add(Material.PINK_WOOL);
        wools.add(Material.GRAY_WOOL);
        wools.add(Material.LIGHT_GRAY_WOOL);
        wools.add(Material.CYAN_WOOL);
        wools.add(Material.PURPLE_WOOL);
        wools.add(Material.BLUE_WOOL);
        wools.add(Material.BROWN_WOOL);
        wools.add(Material.GREEN_WOOL);
        wools.add(Material.RED_WOOL);
        wools.add(Material.BLACK_WOOL);
        ItemStack wool;
        ItemMeta woolMeta;
        Random ran = new Random();
        for (int j = 0; j < 5; j++) {
            int random = ran.nextInt(15);
            wool = new ItemStack(wools.get(random));
            woolMeta = wool.getItemMeta();
            woolMeta.setDisplayName("§e§lオーナメント");
            woolMeta.setLore(Collections.singletonList("クリスマスツリーに飾ろう。"));
            woolMeta.addEnchant(Enchantment.LUCK, 1, true);
            woolMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            wool.setItemMeta(woolMeta);
            p.getInventory().addItem(wool);
        }
        p.sendMessage(prefix + "§614箇所目§aのスタンプを回収！イベントメニューを確認しよう！");
        p.sendMessage(prefix + "§a獲得: オーナメント§65§a個");
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onSaplingPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().getItemInMainHand().equals(SpruceSapling())) {
            e.getBlock().setType(Material.AIR);
            Location location = e.getBlock().getLocation();
            location.getWorld().generateTree(location, TreeType.REDWOOD);
        } else if (p.getInventory().getItemInMainHand().equals(giveItem(10))) {
            Block block = e.getBlock();
            Chest chest = (Chest) block.getState();
            chest.getInventory().setItem(11, new ItemStack(Material.IRON_INGOT, 3));
            chest.getInventory().setItem(12, new ItemStack(Material.EMERALD, 3));
            chest.getInventory().setItem(13, new ItemStack(Material.HEART_OF_THE_SEA));
            chest.getInventory().setItem(14, new ItemStack(Material.GOLD_INGOT, 3));
            chest.getInventory().setItem(15, new ItemStack(Material.DIAMOND, 3));

        }
    }

}
