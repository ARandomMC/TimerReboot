package org.qwertygaming.timerreboot;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RestartCommand implements CommandExecutor {
    private final TimerReboot plugin;

    public RestartCommand(TimerReboot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check permissions
        if (!sender.hasPermission("timerreboot.restart")) {
            sender.sendMessage("You don't have permission to use this command.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("Usage: /trestart <seconds>");
            return true;
        }

        int seconds;
        try {
            seconds = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            sender.sendMessage("Invalid seconds specified.");
            return true;
        }

        if (sender instanceof Player) {
            // Player execution
            Player player = (Player) sender;
            CountdownRunnable countdownRunnable = new CountdownRunnable(player, seconds, plugin);
            Bukkit.getScheduler().runTaskTimer(plugin, countdownRunnable, 0L, 20L);
        } else {
            // Console execution
            sender.sendMessage("Rebooting server in " + seconds + " seconds...");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
        }

        return true;
    }
}
