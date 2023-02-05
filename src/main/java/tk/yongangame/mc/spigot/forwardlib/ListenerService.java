package tk.yongangame.mc.spigot.forwardlib;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerUnregisterChannelEvent;
import org.bukkit.plugin.messaging.PluginMessageListener;
import tk.yongangame.mc.data.PlayerData;

public class ListenerService implements Listener{

    private final CacheService cacheService = CacheService.getInstance();

    @EventHandler(priority= EventPriority.MONITOR)
    public void onEntityDamageEvent(EntityDamageEvent event){
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            PlayerData playerData = new PlayerData(player);
            playerData.health -= event.getFinalDamage();
            cacheService.forwardPlayerData.Send(player,playerData);
        }
    }
    @EventHandler(priority= EventPriority.MONITOR)
    public void onEntityRegainHealth(EntityRegainHealthEvent event){
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            PlayerData playerData = new PlayerData(player);
            playerData.health += event.getAmount();
            cacheService.forwardPlayerData.Send(player,playerData);
        }
    }
    @EventHandler(priority= EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        PlayerData playerData = new PlayerData(player);
        cacheService.forwardPlayerData.Send(player,playerData);
    }

    @EventHandler(priority= EventPriority.MONITOR)
    public void onPlayerRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        PlayerData playerData = new PlayerData(player);
        playerData.health = playerData.maxHealth;
        cacheService.forwardPlayerData.Send(player,playerData);
    }
}
