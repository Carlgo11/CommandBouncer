package com.carlgo11.CommandBouncer;

import com.carlgo11.CommandBouncer.commands.*;
import com.carlgo11.CommandBouncer.updater.Updater;
import com.carlgo11.CommandBouncer.player.CommandListener;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.logging.Level;

public class CommandBouncer extends JavaPlugin {
    
    public static CommandBouncer plugin;
    
    public void onEnable()
    {
        this.reloadConfig();
        checkcmd();
        checkConfig();
        checkUpdate();
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);
        commands();
    }
    
    public void onDisable()
    {
    }
    
    public void checkConfig()
    {
        File config = new File(this.getDataFolder(), "config.yml");
        if (!config.exists()) {
            this.saveDefaultConfig();
            this.getConfig().options().copyHeader(true);
            
            System.out.println("[" + getDescription().getName() + "] No config.yml detected. Creating config.yml...");
        }
    }
    
    public void checkUpdate()
    {
        if (getConfig().getBoolean("auto-update") == true) {
            Updater updater = new Updater(this, 59012, this.getFile(), Updater.UpdateType.DEFAULT, true);
        } else {
        }
    }
    
    public static int a = 1; // cmd checker
    public static int b = 0; // Error checker
    public static int c = 3; // Max errors allowed int

    public void commands(){
        getCommand("CommandBouncer").setExecutor(new CommandBouncerCommand(this));
    }
    
    public void checkcmd()
    {
        a = 1;
        b = 0;
        c = 3;
        for (a = a; b != c; a++) {
            if (getConfig().contains("cmd" + a)) {
            } else {
                b++;
            }
        }
        
        if (b == c) {
            if (getConfig().getBoolean("debug") == true) {
                System.out.println("While loop closed");
            }
            
            getLogger().info("Loaded " + a + " cmds from the config!");
        }
    }
    
    public void debug(String s)
    {
        this.getLogger().log(Level.INFO, "[Debug] " + s);
    }
}
