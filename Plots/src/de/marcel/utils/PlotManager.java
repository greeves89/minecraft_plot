package de.marcel.utils;

import de.marcel.plots.Plots;
import org.bukkit.*;
import org.bukkit.entity.Player;

/**
 * Created by Marcel on 06.09.16.
 */

public class PlotManager {

    private Plots plots;

    public PlotManager(Plots plots) {
        this.plots = plots;
    }

    public void createWorld(Player p, String worldname) {

        if(Bukkit.getWorld(worldname) != null) {
            p.sendMessage("§cDiese Welt existiert schon!");
            return;
        }

        p.sendMessage("§cWelt wird erstellt!");

        WorldCreator wc = new WorldCreator(worldname)
                .environment(World.Environment.NORMAL)
                .type(WorldType.FLAT)
                .generateStructures(false);
        World w = Bukkit.createWorld(wc);

        plots.fileManager.put(worldname);

        p.sendMessage("§cWelt erstellt!");
    }

    public void worldTp(Player p, String worldname) {

        World w = Bukkit.getWorld(worldname);

        if(w == null) {
            p.sendMessage("§cDiese Welt existiert nicht!");
            return;
        }

        Location loc = new Location(w, 0, 50, 0);

        p.setGameMode(GameMode.CREATIVE);
        p.setFlying(true);
        p.teleport(loc);
    }

    public void worldList(Player p) {
        if(Plots.worldNames.size() == 0) {
            p.sendMessage(Messages.prefix + "Es gibt noch keine PlotWelten!");
            return;
        }

        p.sendMessage(Messages.prefix + "PlotWelten:");
        for(String names : Plots.worldNames) {
            p.sendMessage(" §e- " + names);
        }
    }

    public void createVoid(Player p, int size) {

        p.sendMessage("§cVoid wird erstellt!");

        for(int y = 0; y <= 5; y++) {
            for(int x = 0; x <= size; x++) {
                for(int z = 0; z <= size; z++) {
                    if(!p.getWorld().getBlockAt(x, y, z).getType().equals(Material.AIR)) {
                        p.getWorld().getBlockAt(x, y, z).setType(Material.AIR);
                    }
                }
            }
        }

        p.sendMessage("§cVoid erstellt!");
    }

    public void createPlots(Player p, int plotSize, int waySize, int ppw) {

        if(plotSize < 16 || plotSize > 128) {
            p.sendMessage("§cFehler! Die PlotSize muss zwischen 16 und 128 liegen.");
            return;
        }

        if(waySize < 2 || waySize > 8) {
            p.sendMessage("§cFehler! Die WaySize muss zwischen 2 und 8 liegen.");
            return;
        }

        if(waySize % 2 == 1) {
            p.sendMessage("§cFehler! Die WaySize muss eine gerade Zahl beinhalten.");
            return;
        }

        if(ppw < 2 || ppw > 10) {
            p.sendMessage("§cFehler! Die PlotsPerWorld müssen zwischen 2 und 10 liegen.");
            return;
        }

        p.sendMessage("§cPlots werden erstellt!");

        int lengthPlot = (plotSize + waySize + 2) * 10 + 100;

        for(int y = 0; y <= 66; y++) {
            for(int x = 100; x <= lengthPlot; x++) {
                for(int z = 100; z <= lengthPlot; z++) {
                    p.getWorld().getBlockAt(x, y, z).setType(Material.STONE);
                }
            }
        }

        p.sendMessage("§cPlots erstellt!");
    }

}
