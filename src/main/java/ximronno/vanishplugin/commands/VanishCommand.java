package ximronno.vanishplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ximronno.vanishplugin.VanishPlugin;

public class VanishCommand implements CommandExecutor {

    private final VanishPlugin plugin;

    public VanishCommand(VanishPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                toggleVanish(p);
            }
            else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("sender-not-player-error")));
            }
            return true;
        }
        else if(args.length == 1) {
            if(sender.hasPermission("simpleVanishPlugin.others")) {
                Player target;
                try {
                    target = Bukkit.getPlayer(args[0]);
                } catch (NullPointerException e) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-not-found-error")));
                    return true;
                }
                toggleVanish(target);
                if (plugin.getConfig().getBoolean("display-target-name")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("give-to-message")) + " " + ChatColor.YELLOW + target.getDisplayName());
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("give-to-message")));
                }
                return true;
            }
            else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("permission-message")));
            }
        }
        return false;


    }

    private void toggleVanish(Player p) {
        if(!plugin.invisible_players.contains(p)) {
            for(Player people : Bukkit.getOnlinePlayers()) {
                people.hidePlayer(plugin, p);
            }
            plugin.invisible_players.add(p);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("vanish-activated")));
        }

        else if(plugin.invisible_players.contains(p)) {
            for(Player people : Bukkit.getOnlinePlayers()) {
                people.showPlayer(plugin, p);
            }
            plugin.invisible_players.remove(p);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("vanish-deactivated")));
        }
    }
}
