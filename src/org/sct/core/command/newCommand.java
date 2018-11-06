package org.sct.core.command;

import org.sct.core.Core;
import org.sct.core.file.FileTool;
import org.sct.core.api.SctApi;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class newCommand implements CommandExecutor {

    public static Core plugin = Core.plugin;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player;

        if (args.length == 0) {
            helpMsg(sender);
            return true;
        }

        if (sender instanceof Player) {
            player = (Player) sender;
            if (player.isOp()) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("info")) {
                        SCT(sender);
                    } else if (args[0].equalsIgnoreCase("reload")) {
                        plugin.load();
                        plugin.info("§a§l重载完成");
                    } else {
                        helpMsg(player);
                    }
                }
                if (args.length == 2 && args[0].equalsIgnoreCase("get")) {
                    if (sender instanceof Player) FileTool.giveItem(player, args[1]);
                    plugin.info("§a成功发送物品" + args[1]);
                }

                if (args.length == 3 && args[0].equalsIgnoreCase("give")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (!target.isOnline()) {
                        plugin.info("§c§l玩家不在线");
                    }
                    FileTool.giveItem(player, args[2]);
                    plugin.info("§a发送成功");
                }
            } else {
                plugin.info("§c§l没有权限");
            }

            if (args.length == 7 && args[0].equalsIgnoreCase("title")) {
                SctApi.sendTitle(player, Integer.valueOf(args[4]), Integer.valueOf(args[5]), Integer.valueOf(args[6]), args[2], args[3]);
            }
            return true;
        }
        return true;
    }

    private void helpMsg(CommandSender sender) {
        sender.sendMessage("§f/sctitem get [物品名字]  §e-> §6给自己一个[物品]");
        sender.sendMessage("§f/sctitem give [玩家] [物品名字] §e-> §6给[玩家] [数量] 个 [物品]");
        sender.sendMessage("§f/sctitem title [player] [Title] [subTitle] [进入时间] [停滞时间] [淡出时间] ");
        sender.sendMessage("§f/sctitem reload  §e-> §6重载插件");
        sender.sendMessage("§f/sctitem info 查看SCT小组相关事宜/Q群");
    }


    private void SCT(CommandSender sender) {
        sender.sendMessage("§fSCT小组审核群: 711743820");
        sender.sendMessage("§fBUG反馈、建议: Q2457043027");
        sender.sendMessage(" ");
        sender.sendMessage("§7========§aSCT全家桶计划进度§7========\"");
        sender.sendMessage("§f基础类");
        sender.sendMessage("§71. SCTItem(本插件) §a完成");
        sender.sendMessage("§72. SCTLevel §310%");
        sender.sendMessage("§73. SCTLogin §7筹备中");
        sender.sendMessage("§74. SCTMenu §7未开始");
        sender.sendMessage("§75. SCTPoint §7未开始");
        sender.sendMessage("§7敬请期待");
        sender.sendMessage("§f进阶类");
        sender.sendMessage("§7敬请期待");
    }

}
