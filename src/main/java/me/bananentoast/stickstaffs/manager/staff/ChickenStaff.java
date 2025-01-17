package me.bananentoast.stickstaffs.manager.staff;

import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

public class ChickenStaff extends BaseStaff {

    public ChickenStaff() {
        super("chicken", "§e§lChicken Staff", Material.EGG, "§7Throws your eggs for you");
    }

    @Override
    public void onClick(Player player) {

        if (!consume(player, Material.EGG, 1, false)) {
            player.sendMessage("§4Give me eggs!");
            return;
        }

        Chicken chicken = (Chicken) player.getWorld().spawnEntity(player.getLocation(), EntityType.CHICKEN);
        chicken.setVelocity(player.getLocation().getDirection().setY(0.4));
    }

}
