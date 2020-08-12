package Leees.tps.monitor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerGeter {
    public static String p;
    public static String getPlayers() {
        for (Player pl : Bukkit.getOnlinePlayers()) {
            p = pl.getName();
        }
        return p;
    }

}