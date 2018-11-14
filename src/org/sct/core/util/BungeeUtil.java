package org.sct.core.util;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.sct.core.Core;

/**
 * @author SCT_Alchemy
 * @date 2018/11/14 6:00 PM
 */

public class BungeeUtil {
    public static void sendPluginMessage(Player player, String tag, String msg) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(tag);
        out.writeUTF(msg);
        player.sendPluginMessage(Core.getPlugin(), "MessageAPIBukkitData", out.toByteArray());
    }
}
