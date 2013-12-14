package com.carlgo11.CommandBouncer.player;

import com.carlgo11.CommandBouncer.CommandBouncer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 *
 * @author Carlgo11
 *
 */
public class CommandListener implements Listener {

    CommandBouncer plugin;

    public CommandListener(CommandBouncer plug)
    {
        super();
        this.plugin = plug;
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e)
    {
        plugin.debug("chat registred!");
        
        plugin.debug("a = " + CommandBouncer.a);
        
        plugin.debug("b = " + CommandBouncer.b);
        
        Player player = e.getPlayer();
        String cmd = e.getMessage();
        final String[] asd = e.getMessage().split(" ");

        for (int a = 1; a != CommandBouncer.a; a++) {
            plugin.debug("forloop started!");
            
            if (e.getMessage().equalsIgnoreCase("/" + plugin.getConfig().getString("cmd" + a))) {
                plugin.debug("matches cmd" + a);
                

                if (player.hasPermission("CommandBouncer.listen.cmd" + a) || player.hasPermission("CommandBouncer.listen.*") || player.hasPermission("CommandBouncer.*")) { // Checks if player has permission
                    if (plugin.getConfig().contains("cmd" + a + "-disable")) {
                        if (plugin.getConfig().getBoolean("cmd" + a + "-disable") == true) {
                            e.setCancelled(true);
                            System.out.println(player.getName() + " issued server command: " + cmd.toString());

                        } else {
                        }
                    } else {
                        e.setCancelled(true);
                        System.out.println(player.getName() + " issued server command: " + cmd.toString());
                    }
                    plugin.debug(player.getName() + " is not in a disabled world!");
                    
                    if (plugin.getConfig().contains("console" + a)) {
                        String dastring = plugin.getConfig().getString("console" + a);
                        String replaceinput = dastring.replaceAll("%player%", player.getName());
                        String replaceinput2 = replaceinput.replaceAll("%world%", player.getWorld().getName());
                        plugin.debug("dastring:" + replaceinput);
                        
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), replaceinput2);
                        plugin.debug("Console" + a + ": " + plugin.getConfig().getString("console" + a));
                        
                    } else {
                        plugin.debug("No console bnc string found!");
                        
                    }

                    if (plugin.getConfig().contains("player" + a)) {
                        String dastring = plugin.getConfig().getString("player" + a);
                        String replaceinput = dastring.replaceAll("%player%", player.getName());
                        String replaceinput2 = replaceinput.replaceAll("%world%", player.getWorld().getName());
                        plugin.debug("dastring:" + dastring);
                        
                        plugin.debug("player bnc string found!");
                        
                        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(e.getPlayer().getName()), replaceinput2);
                    } else {
                        plugin.debug("No player bnc string found!");
                        
                    }

                } else {
                    plugin.debug(player.getName() + " don't have permission for cmd" + a);
                    
                    if (plugin.getConfig().getBoolean("debug") == true) {
                        System.out.println("[" + plugin.getDescription().getName() + "] " + " ");
                    }
                }

            } else {
                if (plugin.getConfig().getBoolean("debug") == true) {
                    System.out.println("[" + plugin.getDescription().getName() + "] " + "No match: cmd" + a);
                    System.out.println("[" + plugin.getDescription().getName() + "] " + player.getName() + "'s-cmd:" + e.getMessage());
                    System.out.println("[" + plugin.getDescription().getName() + "] " + "cmd" + a + ":" + plugin.getConfig().getString("cmd" + a));
                    System.out.println("[" + plugin.getDescription().getName() + "] " + " ");
                }
            }
        }
    }
}
