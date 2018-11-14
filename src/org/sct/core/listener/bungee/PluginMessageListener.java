package org.sct.core.listener.bungee;


import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.common.io.ByteStreams;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.sct.core.util.BasicUtil;
import org.sct.core.util.TitleUtil;

import java.util.List;

/**
 * @author SCT_Alchemy
 * @date 2018/11/14 5:55 PM
 */

public class PluginMessageListener implements org.bukkit.plugin.messaging.PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("MessageAPIBungeeData")) {
            return;
        }
        //从输入流读数据
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String tag = in.readUTF();
        String msg = in.readUTF();

        // 聊天栏
        if (tag.equals("ChatBar")) {
            List<Player> onlinePlayers = BasicUtil.getOnlinePlayers();
            for (int i = 0; i < onlinePlayers.size(); i++) {
                Player playerTemp = onlinePlayers.get(i);
                if (playerTemp == null) {
                    continue;
                }
                playerTemp.sendMessage(msg);
            }
        }

        // 聊天栏
        if (tag.equals("Title")) {
            List<Player> onlinePlayers = BasicUtil.getOnlinePlayers();
            for (int i = 0; i < onlinePlayers.size(); i++) {
                Player playerTemp = onlinePlayers.get(i);
                if (playerTemp == null) {
                    continue;
                }
                // 设置PlaceholderAPI变量
                String text = PlaceholderAPI.setPlaceholders(playerTemp, msg);

                String[] titles = text.split("[MessageAPI]");
                TitleUtil.sendTitle(playerTemp, 2, 20, 2, titles[0], titles[1]);
            }
        }
    }
}
