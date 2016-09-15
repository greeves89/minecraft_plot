package de.marcel.plots;

import de.marcel.commands.PlotCommand;
import de.marcel.commands.PlotManagerCommand;
import de.marcel.utils.FileManager;
import de.marcel.utils.Messages;
import de.marcel.utils.PlotManager;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Created by Marcel on 06.09.16.
 */

public class Plots extends JavaPlugin {

    String prefix = Messages.prefix;

    public PlotManager plotManager = null;
    public FileManager fileManager = null;

    public static ArrayList<String> worldNames = new ArrayList<>();

    @Override
    public void onEnable() {
        ConsoleCommandSender console = Bukkit.getConsoleSender();

        //Dummes Kommentar
        
        console.sendMessage(prefix + "§aPlugin enabled!");

        plotManager = new PlotManager(this);
        fileManager = new FileManager(this);

        saveConfig();
        fileManager.readConfig();

        registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable() {
        ConsoleCommandSender console = Bukkit.getConsoleSender();

        console.sendMessage(prefix + "§aPlugin disabled!");
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();

    }

    private void registerCommands() {
        this.getCommand("plotmanager").setExecutor(new PlotManagerCommand(this));
        this.getCommand("plot").setExecutor(new PlotCommand(this));
    }

}
