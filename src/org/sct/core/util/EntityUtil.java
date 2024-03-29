package org.sct.core.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

public class EntityUtil {

    /**
     * 取目标玩家
     * @param player 玩家
     * @return 该玩家的目标玩家
     */
    public static Player getTargetPlayer(Player player) {
        List<Entity> nearbyE = player.getNearbyEntities(20.0D, 20.0D, 20.0D);
        ArrayList<Player> nearPlayers = new ArrayList<Player>();
        for (Entity e : nearbyE) {
            if ((e instanceof Player)) {
                nearPlayers.add((Player)e);
            }
        }
        Player target = null;
        BlockIterator bItr = new BlockIterator(player, 20);
        while (bItr.hasNext()) {
            Block block = bItr.next();
            int bx = block.getX();
            int by = block.getY();
            int bz = block.getZ();
            for (Player e : nearPlayers) {
                Location loc = e.getLocation();
                double ex = loc.getX();
                double ey = loc.getY();
                double ez = loc.getZ();
                if ((bx - 0.75D <= ex) && (ex <= bx + 1.75D) && (bz - 0.75D <= ez) && (ez <= bz + 1.75D) && (by - 1 <= ey) && (ey <= by + 2.5D)) {
                    target = e;
                    break;
                }
            }
        }
        return target;
    }

    /**
     * 取附近的实体[返回数组]
     * @param loc 坐标
     * @param radius 半径
     * @return 实体数组
     */
    public static Entity[] getNearbyEntitiesArrays(Location loc, double radius) {
        int Radius = (int)radius;
        int chunkRadius = Radius < 16 ? 1 : (Radius - Radius % 16) / 16;
        HashSet<Entity> radiusEntities = new HashSet<Entity>();
        for(int chX = 0 - chunkRadius; chX <= chunkRadius; ++chX) {
            for(int chZ = 0 - chunkRadius; chZ <= chunkRadius; ++chZ) {
                int x = (int)loc.getX();
                int y = (int)loc.getY();
                int z = (int)loc.getZ();
                Entity[] entitys;
                int size = (entitys = (new Location(loc.getWorld(), (double)(x + chX * 16), (double)y, (double)(z + chZ * 16))).getChunk().getEntities()).length;
                for(int i = 0; i < size; i++) {
                    Entity e = entitys[i];
                    if(e.getLocation().distance(loc) <= radius && e.getLocation().getBlock() != loc.getBlock()) {
                        radiusEntities.add(e);
                    }
                }
            }
        }
        return (Entity[])radiusEntities.toArray(new Entity[radiusEntities.size()]);
    }

    /**
     * 取附近的实体[返回List]
     * @param loc 坐标
     * @param radius 半径
     * @return 实体集合
     */
    public static List<Entity> getNearbyEntitiesList(Location loc, double radius) {
        int Radius = (int)radius;
        int chunkRadius = Radius < 16 ? 1 : (Radius - Radius % 16) / 16;
        List<Entity> radiusEntities = new ArrayList<Entity>();
        for(int chX = 0 - chunkRadius; chX <= chunkRadius; ++chX) {
            for(int chZ = 0 - chunkRadius; chZ <= chunkRadius; ++chZ) {
                int x = (int)loc.getX();
                int y = (int)loc.getY();
                int z = (int)loc.getZ();
                Entity[] entitys;
                int size = (entitys = (new Location(loc.getWorld(), (double)(x + chX * 16), (double)y, (double)(z + chZ * 16))).getChunk().getEntities()).length;
                for(int i = 0; i < size; i++) {
                    Entity e = entitys[i];
                    if(e.getLocation().distance(loc) <= radius && e.getLocation().getBlock() != loc.getBlock()) {
                        radiusEntities.add(e);
                    }
                }
            }
        }
        return radiusEntities;
    }

    /**
     * 取附近的玩家 [返回List]
     * @param loc 坐标
     * @param radius 半径
     * @return 玩家集合
     */
    public static List<Player> getNearbyPlayersList(Location loc, double radius) {
        int Radius = (int)radius;
        int chunkRadius = Radius < 16 ? 1 : (Radius - Radius % 16) / 16;
        List<Entity> radiusEntities = new ArrayList<Entity>();
        for(int chX = 0 - chunkRadius; chX <= chunkRadius; ++chX) {
            for(int chZ = 0 - chunkRadius; chZ <= chunkRadius; ++chZ) {
                int x = (int)loc.getX();
                int y = (int)loc.getY();
                int z = (int)loc.getZ();
                Entity[] entitys;
                int size = (entitys = (new Location(loc.getWorld(), (double)(x + chX * 16), (double)y, (double)(z + chZ * 16))).getChunk().getEntities()).length;
                for(int i = 0; i < size; i++) {
                    Entity e = entitys[i];
                    if(e.getLocation().distance(loc) <= radius && e.getLocation().getBlock() != loc.getBlock()) {
                        radiusEntities.add(e);
                    }
                }
            }
        }
        List<Player> players = new ArrayList<Player>();
        for (int i = 0; i < radiusEntities.size(); i++) {
            if(radiusEntities.get(i) instanceof Player) {
                players.add((Player) radiusEntities.get(i));
            }
        }
        return players;
    }
}
