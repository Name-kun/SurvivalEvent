package xyz.masa3mc.survivalevent.archive;

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
import org.jetbrains.annotations.NotNull;
import xyz.masa3mc.survivalevent.SurvivalEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SurvivalEventCmd implements CommandExecutor, Listener {

    SurvivalEvent plugin = SurvivalEvent.getPlugin(SurvivalEvent.class);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("event")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cコンソールからは実行できません。");
                return true;
            }
            if (args.length == 0) {
                Player p = (Player) sender;
                gui(p);
            } else {
                if (sender.hasPermission("untiburiburi"))
                try {
                    Player p = (Player) sender;
                    SurvivalEventListener listener = new SurvivalEventListener();
                    if (args[0].equals("14")) {
                        listener.RandomOrnament(p);
                        return true;
                    }
                    p.getInventory().addItem(listener.giveItem(Integer.parseInt(args[0])));
                } catch (NumberFormatException e) {
                    sender.sendMessage("値が不正です。");
                }
            }
        }
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("§cク§rリ§cス§rマ§cス§rス§cタ§rン§cプ§rラ§cリ§rー")) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null && e.getCurrentItem().getType().equals(Material.BARRIER)) {
                Player p = (Player) e.getWhoClicked();
                p.closeInventory();
            }
        }
    }

    private void gui(Player p) {
        File file = new File(plugin.getDataFolder(), "players" + File.separator + p.getUniqueId() + ".yml");
        FileConfiguration playerConf = YamlConfiguration.loadConfiguration(file);
        Inventory inv = Bukkit.createInventory(null, 27, "§cク§rリ§cス§rマ§cス§rス§cタ§rン§cプ§rラ§cリ§rー");
        ItemStack noEntry = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta noEntryMeta = noEntry.getItemMeta();
        noEntryMeta.setDisplayName("§c未開放");
        //えいとま第一拠点
        if (playerConf.getStringList("entries").contains("8ma8")) {
            ItemStack item = new ItemStack(Material.JACK_O_LANTERN);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§68MA8の第1拠点");
            List<String> lore = new ArrayList<>();
            lore.add("§aサーバークラッシャーえいとまの第1拠点。");
            lore.add("§a噂ではここにいるだけでtpsとfpsが落ちるそうな...？");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(0, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7遠い辺境に位置する。");
            lore.add("§7地面に空いた大穴を好んで住処にする住民がいるらしい...。");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(0, noEntry);
        }
        //えいとま第二拠点
        if (playerConf.getStringList("entries").contains("8ma8_dai2")) {
            ItemStack item = new ItemStack(Material.SEA_LANTERN);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§68MA8の第2拠点");
            List<String> lore = new ArrayList<>();
            lore.add("§a大量生産により巨万の富を得たえいとまの工場。");
            lore.add("§aなめ§6「おーん...」");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(1, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7遠い辺境に位置する。");
            lore.add("§7地下の大工場だがなぜか地上から丸見え。");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(1, noEntry);
        }
        //びーるくんの農場
        if (playerConf.getStringList("entries").contains("falled_can_farm")) {
            ItemStack item = new ItemStack(Material.WHEAT);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6ビール缶の農場");
            List<String> lore = new ArrayList<>();
            lore.add("§a大農場ながらも鉄道のアクセスが無駄に良い。");
            lore.add("§aなめ§6「銘柄はあきたこまちかな？」");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(2, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7広い農地が大きな一軒家を取り囲むように広がっている。");
            lore.add("§7しかし家主は年中不在のようだ...。");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(2, noEntry);
        }
        //ピラミッド
        if (playerConf.getStringList("entries").contains("pyramid")) {
            ItemStack item = new ItemStack(Material.SANDSTONE);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6ピラミッド");
            List<String> lore = new ArrayList<>();
            lore.add("§a二つ隣り合った巨大なピラミッド。中にも何かあるようだ。");
            lore.add("§aなめ§6「いつか俺も入るのかな...」");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(3, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7エジプト文明と同時期に建てられたのだろうか。");
            lore.add("§7大きな三角錐は当時の高度な文明を象徴している。");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(3, noEntry);
        }
        //ポケ村
        if (playerConf.getStringList("entries").contains("pokemura")) {
            ItemStack item = new ItemStack(Material.BRICKS);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6ポケ村");
            List<String> lore = new ArrayList<>();
            lore.add("§a村長の名前から取って名付けられた港町。");
            lore.add("§aかつての住民たちが住んでいた形跡が残っている。");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(4, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7海に面している小さな港町。");
            lore.add("§7色鮮やかな町並みと島に跨る橋がトレードマーク。");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(4, noEntry);
        }
        //SkyTower
        if (playerConf.getStringList("entries").contains("yuzu_skytower")) {
            ItemStack item = new ItemStack(Material.IRON_BLOCK);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6ゆずさんのSkyTower");
            List<String> lore = new ArrayList<>();
            lore.add("§aゆずもちさんが大量の鉄ブロックを使って作った鉄塔。");
            lore.add("§aどこからこんな大量の鉄を用意したんだろう...。");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(5, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7スポーンの近くに位置する。");
            lore.add("§7とんでもない量の鉄が使われている...？");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(5, noEntry);
        }
        //icex_dai2
        if (playerConf.getStringList("entries").contains("icex_dai2")) {
            ItemStack item = new ItemStack(Material.RAIL);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6ICEX第二駅");
            List<String> lore = new ArrayList<>();
            lore.add("§aゆずもちさんが建設したICEXという路線の駅の一つ。");
            lore.add("§a第二スポーンを見守るような存在。");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(6, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7第二スポーンの近くに位置する。");
            lore.add("§7初期スポーンとの架け橋...？");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(6, noEntry);
        }
        //日向神宮
        if (playerConf.getStringList("entries").contains("hyuga_shrine")) {
            ItemStack item = new ItemStack(Material.STRIPPED_OAK_LOG);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6日向神宮");
            List<String> lore = new ArrayList<>();
            lore.add("§aまさ鯖随一の神宮の一つ。");
            lore.add("§aクリスマスが終わったら初詣はここで決まり！");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(7, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7スポーンからやや遠くに位置する。");
            lore.add("§7みなとまち線を利用して初詣に行く人もいるとか...？");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(7, noEntry);
        }
        //あにりんはうす
        if (playerConf.getStringList("entries").contains("aniline_house")) {
            ItemStack item = new ItemStack(Material.WARPED_FUNGUS);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6あにりんはうす");
            List<String> lore = new ArrayList<>();
            lore.add("§aまさ鯖屈指の錬金術師、アニリンさんの拠点。");
            lore.add("§a迷い込んだら最後、あなたもポーションの材料にされてしまうかも...？");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(8, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7遠い辺境に位置する。");
            lore.add("§7怪しいキノコや建物が立ち並ぶ誰かの拠点...？");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(8, noEntry);
        }
        //新スポ
        if (playerConf.getStringList("entries").contains("new_spawn")) {
            ItemStack item = new ItemStack(Material.STONE_BRICK_WALL);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6新スポーン予定地");
            List<String> lore = new ArrayList<>();
            lore.add("§a知る人ぞ知る新スポーン予定地。");
            lore.add("§aまさ鯖のサグラダファミリアとはこのこと（笑）");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(9, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7スポーンから南東方向のやや遠くに位置する。");
            lore.add("§7新たなスポーン地点として予定されている...？");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(9, noEntry);
        }
        //インターチェンジ
        if (playerConf.getStringList("entries").contains("imatake_ic")) {
            ItemStack item = new ItemStack(Material.SMOOTH_QUARTZ_SLAB);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6今竹くんのインターチェンジ");
            List<String> lore = new ArrayList<>();
            lore.add("§a鯖民の今竹さんが開発している高速道路のIC。");
            lore.add("§aいつかは街になるといいね。");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(10, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7スポーンからやや遠くに位置する。");
            lore.add("§7高速道路の出入り口...？");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(10, noEntry);
        }
        //まさ鯖中央駅
        if (playerConf.getStringList("entries").contains("centre_station")) {
            ItemStack item = new ItemStack(Material.WHITE_CONCRETE);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6まさ鯖中央駅");
            List<String> lore = new ArrayList<>();
            lore.add("§aおそらくまさ鯖で一番大きな駅。");
            lore.add("§aここから四方八方に鉄道が伸びている。");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(11, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7スポーンの近くに位置する。");
            lore.add("§7かなり大きな駅...？");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(11, noEntry);
        }
        //Brendon市
        if (playerConf.getStringList("entries").contains("brendon")) {
            ItemStack item = new ItemStack(Material.GRAY_CONCRETE);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6Brendon市");
            List<String> lore = new ArrayList<>();
            lore.add("§aまさ鯖屈指の大都市。");
            lore.add("§aなのに知っている人が少ないのはなぜだろう...。");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(12, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7スポーンからかなり遠くに位置する。");
            lore.add("§7ICEXの終点となる都市...？");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(12, noEntry);
        }
        //ゆずさんの空港予定地
        if (playerConf.getStringList("entries").contains("yuzumochi_airport")) {
            ItemStack item = new ItemStack(Material.POLISHED_ANDESITE);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6ゆずさんの空港予定地");
            List<String> lore = new ArrayList<>();
            lore.add("§aゆずもちさんが開発中の空港予定地。");
            lore.add("§aたくさんの鯖民が整地に駆り出されている。");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(13, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7スポーンからかなり遠くに位置する。");
            lore.add("§7まさ鯖で初めての空の玄関...？");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(13, noEntry);
        }
        //新津駅
        if (playerConf.getStringList("entries").contains("8ma8_shintsu_sta_home")) {
            ItemStack item = new ItemStack(Material.STONE_BRICKS);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6新津駅");
            List<String> lore = new ArrayList<>();
            lore.add("§aMR中央連絡線の駅の一つ。");
            lore.add("§aいつかはここで釣りでもしたいね。");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(14, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7スポーンからやや遠くに位置する。");
            lore.add("§7港町として開発中...？");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(14, noEntry);
        }
        //塵谷台駅
        if (playerConf.getStringList("entries").contains("8ma8_chiriyadai_sta_home")) {
            ItemStack item = new ItemStack(Material.CHISELED_SANDSTONE);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6塵谷台駅");
            List<String> lore = new ArrayList<>();
            lore.add("§aMR中央連絡線の駅の一つ。");
            lore.add("§a今竹さんの高速道路と合わせて大都市になる予定。");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(15, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7スポーンからやや遠くに位置する。");
            lore.add("§7海底トンネルの先には...？");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(15, noEntry);
        }
        //お花畑と鉄橋
        if (playerConf.getStringList("entries").contains("flower_bridge")) {
            ItemStack item = new ItemStack(Material.WHITE_TULIP);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6お花畑と鉄橋");
            List<String> lore = new ArrayList<>();
            lore.add("§aMR中央連絡線屈指の絶景ポイント。");
            lore.add("§a鯖主のなめくんのお気に入りポイントらしい。");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(16, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7スポーンからやや遠くに位置する。");
            lore.add("§7鉄道オタクにはたまらない絶景...？");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(16, noEntry);
        }
        //海上駅
        if (playerConf.getStringList("entries").contains("8ma8_laputa")) {
            ItemStack item = new ItemStack(Material.GRASS_BLOCK);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6海上駅と巨大ラピュタ");
            List<String> lore = new ArrayList<>();
            lore.add("§aMR中央連絡線で行くことができる人工ラピュタ。");
            lore.add("§aいつかここでサッカーでもしたいね。");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(17, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7スポーンから遠くに位置する。");
            lore.add("§7初見の威圧感はまさ鯖でもトップクラス...？");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(17, noEntry);
        }
        //西農園駅
        if (playerConf.getStringList("entries").contains("8ma8_nishinouen_sta_home")) {
            ItemStack item = new ItemStack(Material.POLISHED_BLACKSTONE_BRICKS);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6西農園駅");
            List<String> lore = new ArrayList<>();
            lore.add("§aMR中央連絡線の駅の一つ。");
            lore.add("§a保護範囲全部を畑にしようだなんてもはや狂気の沙汰。");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(18, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7スポーンからかなり遠くに位置する。");
            lore.add("§7ええ！？ここが全部畑になるんですか！？");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(18, noEntry);
        }
        //youmoukyaha
        if (playerConf.getStringList("entries").contains("youmoukyaha")) {
            ItemStack item = new ItemStack(Material.WHITE_WOOL);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§6youmoukyaha");
            List<String> lore = new ArrayList<>();
            lore.add("§aアドミンのビールさんが作った羊毛工場。");
            lore.add("§a羊毛キャハ！キャハハハハハ！！");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(19, item);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add("§7スポーンから遠くに位置する。");
            lore.add("§7今日も羊の怨嗟の声が聞こえてくる...？");
            noEntryMeta.setLore(lore);
            noEntry.setItemMeta(noEntryMeta);
            inv.setItem(19, noEntry);
        }
        //説明
        ItemStack exp = new ItemStack(Material.KNOWLEDGE_BOOK);
        ItemMeta expMeta = exp.getItemMeta();
        expMeta.setDisplayName("§aイベント説明");
        List<String> lore = new ArrayList<>();
        lore.add("§6ヒントを頼りに全20箇所に及ぶ場所を巡り歩け！");
        lore.add("§6開放するとアイテムが入手でき、20箇所目はいいお宝かも？");
        lore.add("§6開放後は/eventでアイコンと説明を確認してみよう！");
        lore.add("§6ちなみにDynmapを使えばすごく楽らしいよ...何がとは言わないけど...。");
        expMeta.setLore(lore);
        exp.setItemMeta(expMeta);
        inv.setItem(25, exp);
        //とじるやーつ
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("§c閉じる");
        close.setItemMeta(closeMeta);
        inv.setItem(26, close);

        p.openInventory(inv);
    }

}
