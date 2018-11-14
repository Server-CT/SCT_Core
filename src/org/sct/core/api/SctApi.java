package org.sct.core.api;

import org.bukkit.ChatColor;
import org.sct.core.file.FileTool;
import org.sct.core.service.SCTService;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import org.sct.core.util.BungeeUtil;
import org.sct.core.util.TellrawUtil;
import org.bukkit.inventory.ItemStack;


public class SctApi {

    private static SCTService SCTService;
    private static FileTool filetool;

    static {
        SCTService = new SCTService();
    }

    /**
     * 给一名玩家发送Tellraw
     * @param tellraw Tellraw的对象
     * @param player 玩家
     */
    public static void sendTellraw(TellrawUtil tellraw, Player player) {
        SCTService.sendTellraw(tellraw, player);
    }

    /**
     * 创建一个Tellraw对象
     * @param message 信息
     * @return {@link TellrawUtil}
     */
    public static TellrawUtil createTellraw(String message) {
        return SCTService.createTellraw(message);
    }

    /**
     * 取该坐标附近的所有实体
     * @param loc 坐标
     * @param radius 半径
     * @return {@link Entity}
     */
    public static Entity[] getNearbyEntitiesArrays(Location loc, double radius) {
        return SCTService.getNearbyEntitiesArrays(loc, radius);
    }

    /**
     * 将SCTItem里面的道具发送给玩家
     * @param player 收到物品的玩家
     * @param itemKey 发送的指定物品
     */
    public static void sendPlayerItem(Player player, String itemKey) {
        if (filetool.itemMap.containsKey(itemKey)) {
            player.getInventory().addItem((ItemStack) filetool.itemMap.get(itemKey));
            player.sendMessage("§a你收到了一个" + itemKey);
        } else {
            player.sendMessage("§c物品不存在");
        }
    }

    /**
     * 给玩家发送Title
     * @param player 玩家
     * @param fadeIn 淡入时间
     * @param stay 停留时间
     * @param fadeOut 淡出时间
     * @param title 标题[可用Null]
     * @param subtitle 副标题[可用Null]
     */
    public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        SCTService.sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
    }

    /**
     * 发送道具给玩家
     * @param p
     * @param itemkey
     */
    public static void giveItem(Player p, String itemkey) {
        if (filetool.itemMap.containsKey(itemkey)) {
            p.getInventory().addItem((ItemStack) filetool.itemMap.get(itemkey));
            p.sendMessage("§a你收到了一个" + itemkey);
        } else {
            p.sendMessage("§c物品不存在");
        }
    }

    /**
     * 给Bungee玩家发送信息
     *
     * @param player 玩家TCP连接
     * @param text 信息
     */
    public static void sendMessageToBungeePlayers(Player player, String text) {

        text = ChatColor.translateAlternateColorCodes('&', text);

        BungeeUtil.sendPluginMessage(player, "ChatBar", text);

    }

    /**
     * 给Bungee玩家发送Title
     *
     * @param player   玩家TCP连接
     * @param title    标题
     * @param subTitle 副标题
     */
    public static void sendTitleToBungeePlayers(Player player, String title, String subTitle) {

        title = ChatColor.translateAlternateColorCodes('&', title);
        subTitle = ChatColor.translateAlternateColorCodes('&', subTitle);

        StringBuilder builder = new StringBuilder(title);
        builder.append("[MessageAPI]");
        builder.append(subTitle);

        BungeeUtil.sendPluginMessage(player, "Title", builder.toString());

    }

}
