package org.sct.core.service;

import org.sct.core.util.TellrawUtil;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;


public interface Service {

    abstract void sendTellraw(TellrawUtil tellraw, Player player);
    abstract TellrawUtil createTellraw(String message);
    abstract void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle);
    abstract Entity[] getNearbyEntitiesArrays(Location loc, double radius);

}
