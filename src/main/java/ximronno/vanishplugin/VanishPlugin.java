package ximronno.vanishplugin;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ximronno.vanishplugin.commands.VanishCommand;
import ximronno.vanishplugin.listeners.EventListener;

import java.util.ArrayList;

public final class VanishPlugin extends JavaPlugin {


    public ArrayList<Player> invisible_players = new ArrayList<>();

    @Override
    public void onEnable() {

        getCommand("vanish").setExecutor(new VanishCommand(this));

        getServer().getPluginManager().registerEvents(new EventListener(this), this);
    }

    @Override
    public void onDisable() {

    }
}
