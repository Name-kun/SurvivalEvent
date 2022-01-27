package xyz.masa3mc.survivalevent;

import org.bukkit.Material;

import java.util.HashMap;

public class Translator {

    public String dictionary(Material material) {
        HashMap<Material, String> dictionary = new HashMap<>();
        //辞書登録
        dictionary.put(Material.COCOA_BEANS, "カカオ豆");
        dictionary.put(Material.NETHERITE_SCRAP, "ネザライトの欠片");
        dictionary.put(Material.WATER_BUCKET, "水入りバケツ");
        dictionary.put(Material.LAVA_BUCKET, "溶岩入りバケツ");
        dictionary.put(Material.MILK_BUCKET, "ミルク入りバケツ");
        dictionary.put(Material.POWDER_SNOW_BUCKET, "粉雪入りバケツ");
        dictionary.put(Material.HONEY_BOTTLE, "ハチミツ入りの瓶");
        dictionary.put(Material.SWEET_BERRIES, "スイートベリー");
        dictionary.put(Material.GLOW_BERRIES, "グロウベリー");
        dictionary.put(Material.APPLE, "リンゴ");
        dictionary.put(Material.COOKIE, "クッキー");
        dictionary.put(Material.CAKE, "ケーキ");
        dictionary.put(Material.NETHERITE_INGOT, "ネザライトインゴット");
        dictionary.put(Material.IRON_INGOT, "鉄インゴット");
        dictionary.put(Material.COPPER_INGOT, "銅インゴット");
        dictionary.put(Material.GOLD_INGOT, "金インゴット");
        dictionary.put(Material.DIAMOND, "ダイヤモンド");
        dictionary.put(Material.EMERALD, "エメラルド");
        dictionary.put(Material.LAPIS_LAZULI, "ラピスラズリ");
        dictionary.put(Material.AMETHYST_SHARD, "アメジストの欠片");
        dictionary.put(Material.QUARTZ, "ネザークォーツ");
        dictionary.put(Material.WHITE_DYE, "白色の染料");
        dictionary.put(Material.ORANGE_DYE, "橙色の染料");
        dictionary.put(Material.MAGENTA_DYE, "赤紫色の染料");
        dictionary.put(Material.LIGHT_BLUE_DYE, "空色の染料");
        dictionary.put(Material.YELLOW_DYE, "黄色の染料");
        dictionary.put(Material.LIME_DYE, "黄緑色の染料");
        dictionary.put(Material.PINK_DYE, "桃色の染料");
        dictionary.put(Material.GRAY_DYE, "灰色の染料");
        dictionary.put(Material.LIGHT_GRAY_DYE, "薄灰色の染料");
        dictionary.put(Material.CYAN_DYE, "青緑色の染料");
        dictionary.put(Material.PURPLE_DYE, "紫色の染料");
        dictionary.put(Material.BLUE_DYE, "青色の染料");
        dictionary.put(Material.BROWN_DYE, "茶色の染料");
        dictionary.put(Material.GREEN_DYE, "緑色の染料");
        dictionary.put(Material.RED_DYE, "赤色の染料");
        dictionary.put(Material.BLACK_DYE, "黒色の染料");
        dictionary.put(Material.EGG, "卵");
        dictionary.put(Material.NETHER_WART, "ネザーウォート");
        dictionary.put(Material.SUGAR, "砂糖");
        dictionary.put(Material.RABBIT_FOOT, "ウサギの足");
        dictionary.put(Material.GOLDEN_CARROT, "金のニンジン");
        dictionary.put(Material.GLISTERING_MELON_SLICE, "きらめくスイカの薄切り");
        dictionary.put(Material.MAGMA_CREAM, "マグマクリーム");
        dictionary.put(Material.FERMENTED_SPIDER_EYE, "発酵したクモの目");
        dictionary.put(Material.GHAST_TEAR, "ガストの涙");
        dictionary.put(Material.BLAZE_POWDER, "ブレイズパウダー");
        dictionary.put(Material.PHANTOM_MEMBRANE, "ファントムの皮膜");
        dictionary.put(Material.TURTLE_EGG, "カメの卵");
        dictionary.put(Material.SPAWNER, "スポナー");
        dictionary.put(Material.PRISMARINE_SHARD, "プリズマリンの欠片");
        dictionary.put(Material.PRISMARINE_CRYSTALS, "プリズマリンクリスタル");
        dictionary.put(Material.PRISMARINE, "プリズマリン");
        dictionary.put(Material.PRISMARINE_BRICKS, "プリズマリンレンガ");
        dictionary.put(Material.DARK_PRISMARINE, "ダークプリズマリン");
        dictionary.put(Material.SEA_LANTERN, "シーランタン");
        dictionary.put(Material.HEART_OF_THE_SEA, "海洋の心");
        dictionary.put(Material.ENCHANTED_GOLDEN_APPLE, "エンチャントされた金のリンゴ");
        dictionary.put(Material.MUSIC_DISC_PIGSTEP, "レコード(Pigstep)");
        dictionary.put(Material.MUSIC_DISC_OTHERSIDE, "レコード(otherside)");
        dictionary.put(Material.ELYTRA, "エリトラ");

        //辞書登録されていない場合にはアイテムの英名をそのままreturn、登録されていたら辞書から抜き取った値をreturn
        return dictionary.get(material) == null ? material.toString() : dictionary.get(material);
    }
}
