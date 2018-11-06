package org.sct.core.file;

import com.google.common.collect.Maps;
import org.sct.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.inventivetalent.itembuilder.ItemBuilder;
import org.inventivetalent.itembuilder.util.FileUtil;

import java.util.Map;

/**
 * 读取items.yml内东西，itemMap.get("物品名字"); 会返回一个items.yml内的物品
 * for loading the ItemStack from items.yml. there is a static map called itemMap be provided
 * itemMap.get(item name); can return a ItemStack from this yml
 *
 * @author SCT_Alchemy
 */
public class FileTool extends FileUtil {

    private static FileTool filetool;
    public Map<String, ItemStack> itemMap;

    private static ItemStack item;

    private FileTool() {
        super(Core.getPlugin(), "items.yml");
    }

    public static void reload() {
        filetool = new FileTool();
    }

    @Override
    public void check() {
        itemMap = Maps.newHashMap();
        int total = 0;
        for (String key : getKeys(false)) {
            try {
                itemMap.put(key, new ItemBuilder().fromConfig(this.getConfigurationSection(key)).build());
                Bukkit.getServer().getConsoleSender().sendMessage("读取物品" + key);
                ++total;
            } catch (Exception e) {
                Bukkit.getServer().getConsoleSender().sendMessage("错误" + key);
            }
        }
        Bukkit.getServer().getConsoleSender().sendMessage("加载完成，读取物品" + total + "个");
    }

    public static void giveItem(Player p, String itemkey) {
        if (filetool.itemMap.containsKey(itemkey)) {
            p.getInventory().addItem((ItemStack) filetool.itemMap.get(itemkey));
            p.sendMessage("§a你收到了一个" + itemkey);
        } else {
            p.sendMessage("§c物品不存在");
        }
    }

    public static ItemStack getItem(String key) {
        return filetool.itemMap.get(key);
    }

}
