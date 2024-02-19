package ximronno.vanishplugin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ximronno.vanishplugin.VanishPlugin;

public class EventListener implements Listener {

    private final VanishPlugin plugin;

    public EventListener(VanishPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        System.out.println("PLAYER JOINED");
        for(int i = 0; i < plugin.invisible_players.size(); i++) {
            player.hidePlayer(plugin, plugin.invisible_players.get(i));
        }
    }

}
