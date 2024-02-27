package org.qwertygaming.timerreboot;

import org.bukkit.plugin.java.JavaPlugin;

public final class TimerReboot extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
        getCommand("trestart").setExecutor(new RestartCommand(this));
        getLogger().info("RestartPlugin has been enabled!");
        } catch (Exception e) {
            // Send error to console
            getLogger().severe("Error during plugin initialization:");
            e.printStackTrace();
            // Disable the plugin
            setEnabled(false);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("RestartPlugin has been disabled!");
    }
}
