package de.marcel.utils;

import de.marcel.plots.Plots;
import org.bukkit.plugin.Plugin;

/**
 * Created by Marcel on 07.09.16.
 */

public class FileManager {

    private Plugin plugin;

    public FileManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void readConfig() {
        if(Plots.worldNames.size() != 0) {
            Plots.worldNames.clear();
        }
        if(plugin.getConfig().getString("worlds") != null) {
            String worlds = plugin.getConfig().getString("worlds");
            String[] worldnames = worlds.split(",");
            for(int i = 0; i < worldnames.length; i++) {
                Plots.worldNames.add(worldnames[i]);
            }
        }
    }

    public void put(String worldname) {
        if(plugin.getConfig().getString("worlds") != null) {
            String worlds = plugin.getConfig().getString("worlds");
            worlds += "," + worldname;
            plugin.getConfig().set("worlds", worlds);
        } else {
            plugin.getConfig().set("worlds", worldname);
        }
        plugin.saveConfig();
        readConfig();
    }

}
