package org.sct.core.event;

import org.sct.core.util.TellrawUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerReceiveTellrawEvent extends Event implements Cancellable {
    private Player player;
    private TellrawUtil tellraw;
    private static final HandlerList handlers = new HandlerList();

    public PlayerReceiveTellrawEvent(Player player, TellrawUtil tellraw) {
        this.player = player;
        this.tellraw = tellraw;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public TellrawUtil getTellraw() {
        return this.tellraw;
    }

    public void setTellraw(TellrawUtil tellraw) {
        this.tellraw = tellraw;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    private boolean cancelled = false;

    public boolean isCancelled()
    {
        return this.cancelled;
    }

    public void setCancelled(boolean cancel)
    {
        this.cancelled = cancel;
    }
}
