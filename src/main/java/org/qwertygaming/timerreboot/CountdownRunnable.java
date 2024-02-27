package org.qwertygaming.timerreboot;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CountdownRunnable implements Runnable {
    private final Player player;
    private int secondsRemaining;
    private final TimerReboot plugin;

    public CountdownRunnable(Player player, int seconds, TimerReboot plugin) {
        this.player = player;
        this.secondsRemaining = seconds;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (secondsRemaining > 0) {
            player.sendMessage("Rebooting server in " + secondsRemaining + " seconds...");
            secondsRemaining--;
            // Schedule the next iteration after a delay of 1 second
            Bukkit.getScheduler().runTaskLater(plugin, this, 20L);
        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
            // Cancel the task
            Bukkit.getScheduler().cancelTasks(plugin);
        }
    }
}