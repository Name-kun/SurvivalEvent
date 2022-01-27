package xyz.masa3mc.survivalevent;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.UUID.randomUUID;

public class SurvivalEventListener implements Listener {

    SurvivalEvent plugin = JavaPlugin.getPlugin(SurvivalEvent.class);

    @EventHandler
    public void onNPCRightClick(NPCRightClickEvent e) {
        List<Integer> npc = new ArrayList<>(plugin.config.getIntegerList("npc"));
        //クリックしたnpcがリストに含まれている場合
        if (npc.contains(e.getNPC().getId())) {
            Player p = e.getClicker();
            File file = new File(plugin.getDataFolder(), "players" + File.separator + p.getUniqueId() + ".yml");
            FileConfiguration playerConf = YamlConfiguration.loadConfiguration(file);
            //プレイヤーがセットされていない場合0を代入
            if (!playerConf.isSet("count")) {
                playerConf.set("count", 0);
            }
            int playerCount = playerConf.getInt("count");
            //納品n回目の時のnpcが正しい場合
            try {
                if (e.getNPC().getId() == npc.get(playerCount)) {
                    boolean itemPossession = true;
                    //所持しているアイテムが正しいときにはtrueになるやーつelse {
                    for (ItemStack item : items(playerCount)) {
                        if (itemPossession) {
                            if (!p.getInventory().containsAtLeast(item, item.getAmount())) {
                                itemPossession = false;
                            }
                        }
                    }
                    //所持しているアイテムが正しいとき
                    Translator translator = new Translator();
                    if (itemPossession) {
                        //当該アイテムの削除
                        for (ItemStack item : items(playerCount)) {
                            p.getInventory().removeItem(item);
                        }
                        p.sendMessage((playerCount + 1) + "箇所目の納品を完了しました！");
                        String returnItemName = returnItem(playerCount).getItemMeta().hasDisplayName() ? returnItem(playerCount).getItemMeta().getDisplayName() : translator.dictionary(returnItem(playerCount).getType());
                        p.sendMessage(returnItemName + org.bukkit.ChatColor.RESET + returnItem(playerCount).getAmount() + "個を入手しました！");
                        response(p, playerCount);
                        if (playerCount == 9)
                            p.sendMessage("§6§l全ての納品クエストが完了しました！");
                        p.getInventory().addItem(returnItem(playerCount));
                        //カウントを1追加
                        playerConf.set("count", playerCount + 1);
                        try {
                            playerConf.save(file);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    } else {
                        //必要アイテムの通知
                        StringBuilder stringBuilder = new StringBuilder();
                        for (ItemStack item : items(playerCount)) {
                            stringBuilder.append(translator.dictionary(item.getType())).append(item.getAmount()).append("個 ");
                        }
                        p.sendMessage("§c" + stringBuilder.append("を少なくとも所持している必要があります。").toString());
                    }
                }
            } catch (IndexOutOfBoundsException ex) {
                //ウンコロナウイルス デルデルデルタ株
                //よくわからんけどヨシ！
            }
        }
    }

    public List<ItemStack> items(int index) {
        List<ItemStack> itemList = new ArrayList<>();
        switch (index) {
            case 0:
                itemList.add(new ItemStack(Material.COCOA_BEANS, 32));
                break;
            case 1:
                itemList.addAll(Arrays.asList(new ItemStack(Material.COCOA_BEANS, 32), new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.LAVA_BUCKET),
                        new ItemStack(Material.MILK_BUCKET), new ItemStack(Material.POWDER_SNOW_BUCKET)));
                break;
            case 2:
                itemList.addAll(Arrays.asList(new ItemStack(Material.COCOA_BEANS, 32), new ItemStack(Material.HONEY_BOTTLE, 3), new ItemStack(Material.SWEET_BERRIES, 3),
                        new ItemStack(Material.GLOW_BERRIES, 3), new ItemStack(Material.APPLE, 3), new ItemStack(Material.COOKIE, 8), new ItemStack(Material.CAKE)));
                break;
            case 3:
                itemList.addAll(Arrays.asList(new ItemStack(Material.COCOA_BEANS, 32), new ItemStack(Material.IRON_INGOT, 3), new ItemStack(Material.COPPER_INGOT, 3),
                        new ItemStack(Material.GOLD_INGOT, 3)));
                break;
            case 4:
                itemList.addAll(Arrays.asList(new ItemStack(Material.COCOA_BEANS, 32), new ItemStack(Material.DIAMOND, 3), new ItemStack(Material.EMERALD, 3),
                        new ItemStack(Material.LAPIS_LAZULI, 3), new ItemStack(Material.AMETHYST_SHARD, 3), new ItemStack(Material.QUARTZ, 3)));
                break;
            case 5:
                itemList.addAll(Arrays.asList(new ItemStack(Material.COCOA_BEANS, 32), new ItemStack(Material.WHITE_DYE, 3), new ItemStack(Material.ORANGE_DYE, 3),
                        new ItemStack(Material.MAGENTA_DYE, 3), new ItemStack(Material.LIGHT_BLUE_DYE, 3), new ItemStack(Material.YELLOW_DYE, 3),
                        new ItemStack(Material.LIME_DYE, 3), new ItemStack(Material.PINK_DYE, 3), new ItemStack(Material.GRAY_DYE, 3),
                        new ItemStack(Material.LIGHT_GRAY_DYE, 3), new ItemStack(Material.CYAN_DYE, 3), new ItemStack(Material.PURPLE_DYE, 3),
                        new ItemStack(Material.BLUE_DYE, 3), new ItemStack(Material.BROWN_DYE, 3), new ItemStack(Material.GREEN_DYE, 3),
                        new ItemStack(Material.RED_DYE, 3), new ItemStack(Material.BLACK_DYE, 3)));
                break;
            case 6:
                itemList.addAll(Arrays.asList(new ItemStack(Material.COCOA_BEANS, 32), new ItemStack(Material.EGG, 3), new ItemStack(Material.TURTLE_EGG, 3)));
                break;
            case 7:
                itemList.addAll(Arrays.asList(new ItemStack(Material.COCOA_BEANS, 32), new ItemStack(Material.PRISMARINE_SHARD, 8), new ItemStack(Material.PRISMARINE_CRYSTALS, 8),
                        new ItemStack(Material.PRISMARINE, 3), new ItemStack(Material.PRISMARINE_BRICKS, 3), new ItemStack(Material.DARK_PRISMARINE, 3), new ItemStack(Material.SEA_LANTERN, 3)));
                break;
            case 8:
                itemList.addAll(Arrays.asList(new ItemStack(Material.COCOA_BEANS, 32), new ItemStack(Material.NETHER_WART), new ItemStack(Material.SUGAR), new ItemStack(Material.RABBIT_FOOT),
                        new ItemStack(Material.GOLDEN_CARROT), new ItemStack(Material.GLISTERING_MELON_SLICE), new ItemStack(Material.MAGMA_CREAM), new ItemStack(Material.FERMENTED_SPIDER_EYE),
                        new ItemStack(Material.GHAST_TEAR), new ItemStack(Material.BLAZE_POWDER), new ItemStack(Material.PHANTOM_MEMBRANE)));
                break;
            case 9:
                itemList.addAll(Arrays.asList(new ItemStack(Material.COCOA_BEANS, 32), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE),
                        new ItemStack(Material.MUSIC_DISC_PIGSTEP), new ItemStack(Material.MUSIC_DISC_OTHERSIDE), new ItemStack(Material.ELYTRA)));
                break;
        }
        return itemList;
    }

    public ItemStack returnItem(int index) {
        ItemStack item = null;
        ItemMeta meta;
        switch (index) {
            case 0:
                item = new ItemStack(Material.NETHERITE_SCRAP);
                break;
            case 1:
                item = new ItemStack(Material.NETHERITE_SCRAP, 3);
                break;
            case 2:
                item = new ItemStack(Material.NETHERITE_INGOT);
                break;
            case 3:
                item = new ItemStack(Material.NETHER_BRICK);
                meta = item.getItemMeta();
                meta.setDisplayName("§4§lチョコレートインゴット");
                meta.setLore(Arrays.asList("チョコレートで出来た延べ棒。", "硬すぎて食べることはできない。"));
                meta.addEnchant(Enchantment.LUCK, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.setItemMeta(meta);
                break;
            case 4:
                item = new ItemStack(Material.GLOWSTONE_DUST);
                meta = item.getItemMeta();
                meta.setDisplayName("§e光るチョコレート");
                meta.setLore(Collections.singletonList("手に持つと発光が付与される。"));
                meta.addEnchant(Enchantment.LUCK, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.setItemMeta(meta);
                break;
            case 5:
                item = new ItemStack(Material.PRISMARINE_CRYSTALS);
                meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.of(new java.awt.Color(230, 0, 18)) + "ゲ" + ChatColor.of(new java.awt.Color(243, 152, 0)) + "ー" + ChatColor.of(new java.awt.Color(255, 241, 0)) + "ミ" +
                        ChatColor.of(new java.awt.Color(57, 168, 105)) + "ン" + ChatColor.of(new java.awt.Color(0, 255, 0)) + "グ" + ChatColor.of(new java.awt.Color(71, 132, 191)) + "ス" +
                        ChatColor.of(new java.awt.Color(0, 90, 160)) + "ト" + ChatColor.of(new java.awt.Color(29, 32, 128)) + "ー" + ChatColor.of(new java.awt.Color(146, 7, 131)) + "ン");
                meta.setLore(Collections.singletonList("NameKunの頭もこんな感じらしい。"));
                meta.addEnchant(Enchantment.LUCK, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                meta.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier(randomUUID(), "generic_attribute", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
                item.setItemMeta(meta);
                break;
            case 6:
                item = new ItemStack(Material.SPAWNER);
                break;
            case 7:
                item = new ItemStack(Material.HEART_OF_THE_SEA);
                break;
            case 8:
                item = new ItemStack(Material.POTION);
                meta = item.getItemMeta();
                PotionMeta potionMeta = (PotionMeta) meta;
                potionMeta.setDisplayName("§5§o§l猛烈なチョコレート");
                potionMeta.setLore(Arrays.asList("飲むと色々体にヤバいお薬。", "§mなんだ、なんともないじゃないか。"));
                potionMeta.setColor(Color.fromRGB(119, 68, 17));
                potionMeta.addEnchant(Enchantment.LUCK, 1, true);
                potionMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 3600, 0), false);
                potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 400, 3), false);
                potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 0), false);
                potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 3600, 0), false);
                potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 900, 0), false);
                potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 2), false);
                potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 0), false);
                potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 3600, 0), false);
                potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 3600, 0), false);
                potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 3600, 0), false);
                potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1800, 0), false);
                potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 900, 0), false);
                potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 1800, 0), false);
                item.setItemMeta(potionMeta);
                break;
            case 9:
                item = new ItemStack(Material.COCOA_BEANS);
                meta = item.getItemMeta();
                meta.setDisplayName("§5§o§lTRUE OF CHOCOLATE");
                meta.setLore(Arrays.asList("貴方に真実の愛を...。", "2022.2.14"));
                meta.addEnchant(Enchantment.LUCK, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(randomUUID(), "generic_attribute", 0.3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
                meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(randomUUID(), "generic_attribute", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
                meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(randomUUID(), "generic_attribute", 0.05, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
                item.setItemMeta(meta);
                break;
        }
        return item;
    }

    //返答
    void response(Player p, int i) {
        List<String> responses = new ArrayList<>();
        switch (i) {
            case 0:
                responses.add("§aありがとねー");
                responses.add("§a今年もチョコめっちゃ貰えたらええなー");
                break;
            case 1:
                responses.add("§aありがとう～");
                responses.add("§aあれ、そういえば淡路島って人住めるの？");
                break;
            case 2:
                responses.add("§aあっ、ありがと～");
                responses.add("§aうちのキツネたちにもチョコあげないとな...");
                break;
            case 3:
                responses.add("§aどもー");
                responses.add("§a腹が減っては露天は出来ぬ、なんちって");
                break;
            case 4:
                responses.add("§aありがたや～");
                responses.add("§aチョコ欲しいけど無理はせんでや～");
                break;
            case 5:
            case 7:
                responses.add("§athx");
                break;
            case 6:
                responses.add("§aあっ、どうも～");
                responses.add("§aチョコってなんだっけ...？");
                break;
            case 8:
                responses.add("§aありがとー");
                responses.add("§aチョコくれるんも嬉しいんやけど、それよかFF一緒にやらへん？");
                break;
            case 9:
                responses.add("§aありがとう");
                responses.add("§aチョコ１個につき髪の毛が１本生える鯖主はこちらです");
        }
        for (String response : responses) p.sendMessage(response);
    }

}
