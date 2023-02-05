package tk.yongangame.mc.spigot.forwardlib;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import tk.yongangame.mc.data.PlayerData;

import java.util.LinkedHashMap;
import java.util.UUID;

public final class ForwardLib extends JavaPlugin {
    @Override
    public void onEnable() {
        CacheService.getInstance().forwardPlayerData.Register((channel, player, message)->{
            /* PlayerData playerData = forwardPlayerData.Read(message);
            if (StringUtils.isBlank(playerData.name)) {
                forwardPlayerData.Send(Bukkit.getServer(), );
            }*/
            Bukkit.getLogger().info("123");
        });
        getServer().getPluginManager().registerEvents(new ListenerService(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
