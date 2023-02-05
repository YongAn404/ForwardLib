package tk.yongangame.mc.data;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {

    public UUID uuid;
    public String name;
    public double maxHealth;
    public double health;

    public PlayerData(Player player){
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.maxHealth =player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        this.health = player.getHealth();
    }
}
