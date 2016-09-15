package de.marcel.commands;

import de.marcel.plots.Plots;
import de.marcel.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Marcel on 07.09.16.
 */

public class PlotCommand implements CommandExecutor {

    private Plots plots;

    public PlotCommand(Plots plots) {
        this.plots = plots;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("plot")) {
                p.sendMessage("Plot");

                return true;
            }

        } else {
            sender.sendMessage(Messages.mustPlayer);
        }
        return false;
    }

}
