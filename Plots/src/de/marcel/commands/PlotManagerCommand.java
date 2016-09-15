package de.marcel.commands;

import de.marcel.plots.Plots;
import de.marcel.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Marcel on 06.09.16.
 */

public class PlotManagerCommand implements CommandExecutor {

    private Plots plots;

    public PlotManagerCommand(Plots plots) {
        this.plots = plots;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("plotmanager")) {
                if(p.hasPermission("*")) {
                    if(args.length != 0) {
                        if(args.length == 1) {
                            if(args[0].equalsIgnoreCase("worldList")) {
                                plots.plotManager.worldList(p);
                            } else {
                                p.sendMessage("§cUsage: /plotmanager createWorld <worldName>");
                                p.sendMessage("§cUsage: /plotmanager worldTp <worldName> [<Player>]");
                                p.sendMessage("§cUsage: /plotmanager worldList");
                                p.sendMessage("§cUsage: /plotmanager createVoid <size>");
                                p.sendMessage("§cUsage: /plotmanager createPlots <size> <way> <ppw>");
                            }
                        } else if(args.length == 2) {
                            if(args[0].equalsIgnoreCase("createWorld")) {
                                String worldName = args[1];
                                plots.plotManager.createWorld(p, worldName);
                            } else if(args[0].equalsIgnoreCase("worldTp")) {
                                String worldName = args[1];
                                plots.plotManager.worldTp(p, worldName);
                            } else if(args[0].equalsIgnoreCase("createVoid")) {
                                int size = 0;
                                try {
                                    size = Integer.valueOf(args[1]);
                                } catch(NumberFormatException e) {
                                    e.printStackTrace();
                                    p.sendMessage("Du musst eine Zahl angeben!");
                                    p.sendMessage("§cUsage: /plotmanager createVoid <size>");
                                }
                                plots.plotManager.createVoid(p, size);
                            } else {
                                p.sendMessage("§cUsage: /plotmanager createWorld <worldName>");
                                p.sendMessage("§cUsage: /plotmanager worldTp <worldName> [<Player>]");
                                p.sendMessage("§cUsage: /plotmanager worldList");
                                p.sendMessage("§cUsage: /plotmanager createVoid <size>");
                                p.sendMessage("§cUsage: /plotmanager createPlots <size> <way> <ppw>");
                            }
                        } else if(args.length == 3) {
                            if(args[0].equalsIgnoreCase("worldTp")) {
                                String worldName = args[1];
                                Player target = Bukkit.getPlayer(args[2]);
                                if(target != null) {
                                    plots.plotManager.worldTp(target, worldName);
                                } else {
                                    p.sendMessage(Messages.unknownPlayer);
                                }
                            } else {
                                p.sendMessage("§cUsage: /plotmanager createWorld <worldName>");
                                p.sendMessage("§cUsage: /plotmanager worldTp <worldName> [<Player>]");
                                p.sendMessage("§cUsage: /plotmanager worldList");
                                p.sendMessage("§cUsage: /plotmanager createVoid <size>");
                                p.sendMessage("§cUsage: /plotmanager createPlots <size> <way> <ppw>");
                            }
                        } else if(args.length == 4) {
                            if(args[0].equalsIgnoreCase("createPlots")) {
                                int size = 0;
                                int way = 0;
                                int ppw = 0;
                                try {
                                    size = Integer.valueOf(args[1]);
                                    way = Integer.valueOf(args[2]);
                                    ppw = Integer.valueOf(args[3]);
                                } catch(NumberFormatException e) {
                                    e.printStackTrace();
                                    p.sendMessage("Du musst eine Zahl angeben!");
                                    p.sendMessage("§cUsage: /plotmanager createPlots <size> <way> <ppw>");
                                }
                                plots.plotManager.createPlots(p, size, way, ppw);
                            } else {
                                p.sendMessage("§cUsage: /plotmanager createWorld <worldName>");
                                p.sendMessage("§cUsage: /plotmanager worldTp <worldName> [<Player>]");
                                p.sendMessage("§cUsage: /plotmanager worldList");
                                p.sendMessage("§cUsage: /plotmanager createVoid <size>");
                                p.sendMessage("§cUsage: /plotmanager createPlots <size> <way> <ppw>");
                            }
                        } else {
                            p.sendMessage("§cUsage: /plotmanager createWorld <worldName>");
                            p.sendMessage("§cUsage: /plotmanager worldTp <worldName> [<Player>]");
                            p.sendMessage("§cUsage: /plotmanager worldList");
                            p.sendMessage("§cUsage: /plotmanager createVoid <size>");
                            p.sendMessage("§cUsage: /plotmanager createPlots <size> <way> <ppw>");
                        }
                    } else {
                        p.sendMessage("§cUsage: /plotmanager createWorld <worldName>");
                        p.sendMessage("§cUsage: /plotmanager worldTp <worldName> [<Player>]");
                        p.sendMessage("§cUsage: /plotmanager worldList");
                        p.sendMessage("§cUsage: /plotmanager createVoid <size>");
                        p.sendMessage("§cUsage: /plotmanager createPlots <size> <way> <ppw>");
                    }
                }

                return true;
            }

        } else {
            sender.sendMessage(Messages.mustPlayer);
        }
        return false;
    }

}
