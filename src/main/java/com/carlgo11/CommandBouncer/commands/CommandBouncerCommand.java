package com.carlgo11.CommandBouncer.commands;

import com.carlgo11.CommandBouncer.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandBouncerCommand implements CommandExecutor {

    private CommandBouncer plugin;

    public CommandBouncerCommand(CommandBouncer plug)
    {
        this.plugin = plug;
    }

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        String prefix = ChatColor.GREEN + "[" + plugin.getDescription().getName() + "] " + ChatColor.RESET;
        String badperm = ChatColor.RED + "Error: You don't have permission to perform that action!";

        if (cmd.getName().equalsIgnoreCase("CommandBouncer")) {
            if (args.length == 0) { // EXAMPLE: /cmd arg0 arg1 arg2
                if (sender.hasPermission("commandbouncer.cmd.commandbouncer") || sender.hasPermission("CommandBouncer.*")) {
                    sender.sendMessage(ChatColor.YELLOW + "============ " + ChatColor.GREEN + plugin.getDescription().getName() + ChatColor.YELLOW + " ============");
                    sender.sendMessage("");
                    sender.sendMessage(ChatColor.GRAY + "/" + ChatColor.RED + cmd.getName() + ChatColor.YELLOW + " Shows all avible commands");
                    sender.sendMessage(ChatColor.GRAY + "/" + ChatColor.RED + cmd.getName() + " Reload" + ChatColor.YELLOW + " Reload the config.yml");
                    sender.sendMessage(ChatColor.GRAY + "/" + ChatColor.RED + cmd.getName() + " List" + ChatColor.YELLOW + " List all the commands that the plugin listens on");
                } else {
                    sender.sendMessage(prefix + badperm);
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("commandbouncer.cmd.commandbouncer.reload") || sender.hasPermission("CommandBouncer.*")) {
                        plugin.getServer().getPluginManager().disablePlugin(plugin);
                        plugin.getServer().getPluginManager().enablePlugin(plugin);
                        sender.sendMessage(prefix + ChatColor.GREEN + "Plugin reloaded!");
                    } else {
                        sender.sendMessage(prefix + badperm);
                    }
                }

                if (args[0].equalsIgnoreCase("list")) {
                    if (sender.hasPermission("commandbouncer.cmd.commandbouncer.list") || sender.hasPermission("CommandBouncer.*")) {
                        sender.sendMessage(ChatColor.YELLOW + "============ " + ChatColor.GREEN + prefix + ChatColor.YELLOW + " ============");
                        sender.sendMessage(ChatColor.GRAY + "Gray = Command to listen to.");
                        sender.sendMessage(ChatColor.BLUE + "Blue = Console command.");
                        sender.sendMessage(ChatColor.RED + "Red = Player command.");
                        int d = 1;
                        int e = 0;
                        int f = 1;
                        StringBuilder message = new StringBuilder();
                        for (d = d; e != f; d++) {
                            if (plugin.getConfig().contains("cmd" + d)) {
                                message.append("\n" + ChatColor.YELLOW + "cmd" + d + ": " + ChatColor.GRAY);
                                message.append("/" + plugin.getConfig().getString("cmd" + d) + "");
                                if (plugin.getConfig().contains("console" + d)) {
                                    message.append(ChatColor.RESET + " " + ChatColor.BLUE + "/" + plugin.getConfig().getString("console" + d));
                                }
                                if (plugin.getConfig().contains("player" + d)) {
                                    message.append(ChatColor.RESET + " " + ChatColor.RED + "/" + plugin.getConfig().getString("player" + d));
                                }
                            } else {
                                e++;
                            }
                        }
                        sender.sendMessage("" + message);
                    } else {
                        sender.sendMessage(prefix + badperm);
                    }
                    // more 1 arg commands here:
                }
            } else if (args.length == 2) {
                // Add code here?
                // nope
            }
        }
        return true;
    }

}
