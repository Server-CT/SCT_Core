package org.sct.core.service;

import org.sct.core.event.PlayerReceiveTellrawEvent;
import org.sct.core.event.PlayerReceiveTitleEvent;
import org.sct.core.dto.Title;
import org.sct.core.Core;
import org.sct.core.util.EntityUtil;
import org.sct.core.util.TellrawUtil;
import org.sct.core.util.TitleUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;


public class SCTService implements Service {

    @Override
    public void sendTellraw(TellrawUtil tellraw, Player player) {
        if (!player.isOnline()) {
            throw new NullPointerException("错误： 玩家不在线!");
        }
        if (tellraw == null) {
            throw new NullPointerException("错误： Tellraw不能为Null!");
        }
        TellrawUtil tellRaw = tellraw;
        Bukkit.getPluginManager().callEvent(new PlayerReceiveTellrawEvent(player, tellRaw));

        if (Bukkit.getVersion().contains("Paper") && !Bukkit.isPrimaryThread()) {
            Bukkit.getScheduler().runTask(Core.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getName() + " " + tellraw.toJsonString());
                }
            });
        }else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getName() + " " + tellRaw.toJsonString());
        }
    }

    @Override
    public TellrawUtil createTellraw(String message) {
        return new TellrawUtil(message.replaceAll("&", "§"));
    }

    @Override
    public Entity[] getNearbyEntitiesArrays(Location loc, double radius) {
        return EntityUtil.getNearbyEntitiesArrays(loc, radius);
    }

    @Override
    public void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        if (!player.isOnline()) {
            throw new NullPointerException("错误： 玩家不在线!");
        }
        Title t = new Title(fadeIn, stay, fadeOut, title, subtitle);
        Bukkit.getPluginManager().callEvent(new PlayerReceiveTitleEvent(player, t));
        TitleUtil.sendTitle(player, t.getFadeIn(), t.getStay(), t.getFadeOut(), t.getTitle(), t.getSubtitle());
    }

}
