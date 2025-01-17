package me.bananentoast.stickstaffs.command;

import me.bananentoast.stickstaffs.StickStaffs;
import me.bananentoast.stickstaffs.util.ListUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StaffCommand implements CommandExecutor, TabCompleter {

    private final StickStaffs instance;

    public StaffCommand() {
        instance = StickStaffs.getInstance();
        instance.getCommand("staff").setExecutor(this);
        instance.getCommand("staff").setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cThis command is player-only!");
            return true;
        }
        if (!player.isOp()) {
            return true;
        }
        if (args.length == 0) {
            player.sendMessage("§cPlease use /staff <staff name>!");
            return true;
        }
        String staffName = args[0];
        if (!instance.getStaffManager().isStaff(staffName)) {
            player.sendMessage("§cInvalid Staff Name! Please use /staff <staff name>!");
            return true;
        }
        player.getInventory().addItem(instance.getStaffManager().getStaff(staffName).getItem());
        player.sendMessage("§aYou received the " + staffName + " staff");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String name, String[] args) {
        List<String> list = new ArrayList<>();

        if (!sender.isOp()) {
            return list;
        }

        if (args.length == 1) {
            list.addAll(instance.getStaffManager().getCasedStaffNames());
            return ListUtil.getShorterList(list, args[0]);
        }

        return list;

    }
}
