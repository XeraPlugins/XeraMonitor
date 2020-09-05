package Xera.Monitor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerGeter {
    public static String getPlayers() {
        StringBuilder p = new StringBuilder();
        List<String> players = new ArrayList<>();

        for (Player pl : Bukkit.getOnlinePlayers()) {
            players.add(pl.getName());
        }

        for (int i = 0; i < players.size(); i++) {
            if (i == (players.size() - 1)) {
                p.append(players.get(i));
            } else {
                p.append(players.get(i)).append(", ");
            }
        }
        
        return p.toString();
    }
}