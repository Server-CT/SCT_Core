package org.sct.core.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.sct.core.api.*;

public class example implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        SctApi.sendTellraw(SctApi.createTellraw("显示信息").addHover("测试", "点击执行命令").excuteCommand("/test"), e.getPlayer());
       // SctApi.sendTellraw(SctApi.createTellraw("测试").addHover("t");
        SctApi.sendTellraw(SctApi.createTellraw("显示信息").addHover("测试", "点击补全命令").showCommandInChatBar("/test"), e.getPlayer());
    }

    public void t(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

    }
}
