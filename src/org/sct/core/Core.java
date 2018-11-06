package org.sct.core;

import org.sct.core.command.newCommand;
import org.sct.core.file.FileTool;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

/**
 * SCTItem Core
 *
 * @author SCT_鹦鹉
 * @author TangYuan
 * @author SCT_Alchemy
 * @author MeowCat_MlgmXyysd
 * Copyright (C) 2018 Server CT All Rights Reserved.
 */
public class Core extends JavaPlugin {

    public static Core plugin;
    private File folder = getDataFolder();
    public Boolean isSentConsoleMsg;
    public static int versionNum = 200;

    public Core() {
        if (plugin == null) plugin = this;
        else throw new NullPointerException("Hahhah, surprise mother fucker!");
    }


    @Override
    public void onEnable() {
        isSentConsoleMsg = getConfig().getBoolean("isSentConsoleMsg");
        load();
        Bukkit.getServer().getConsoleSender().sendMessage("SCTItem加载成功!");
        getServer().getPluginCommand("sctitem").setExecutor(new newCommand());
        try {
            Class.forName("org.bukkit.event.player.PlayerInteractAtEntityEvent");
        } catch (Exception e) {
            versionNum = 170;
        }
    }

    public void load() {
        FileTool.reload();
        saveResource("items.yml", false);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage("卸载完成，感谢使用");
    }

    public static void info(String msg) {
        plugin.getLogger().info(msg.replace("&", "§"));
    }

    private static void warn(String msg) {
        plugin.getLogger().warning(msg);
    }

    public static Core getPlugin() {
        return plugin;
    }

}
