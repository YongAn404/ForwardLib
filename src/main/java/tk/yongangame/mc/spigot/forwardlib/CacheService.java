package tk.yongangame.mc.spigot.forwardlib;

import tk.yongangame.mc.data.PlayerData;

import java.util.LinkedHashMap;
import java.util.UUID;

public class CacheService {

    private static CacheService instance;

    private CacheService() {
    }

    public static CacheService getInstance() {
        if (instance == null) {
            instance = new CacheService();
        }
        return instance;
    }

    public final Forward<PlayerData> forwardPlayerData = new Forward<>("forward_lib:player_data");
}
