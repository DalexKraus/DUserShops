package at.dalex.usershops.command;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import at.dalex.chunk5.Chunk5;
import at.dalex.usershops.Main;
import at.dalex.usershops.manager.UserShop;

public class CommandCreate implements ICommand {

	@Override
	public void execute(Player p, String[] args) {
		Location playerLocation = p.getLocation();
		//Prüfe, ob der User auf seinem Land steht
		if (Chunk5.getInstance().config.landManager.isBoughtByPlayer(playerLocation.getChunk(), p)) {
			//Villager erstellen und spawnen
			Villager villager = (Villager) playerLocation.getWorld().spawnEntity(playerLocation, EntityType.VILLAGER);
			villager.setCustomName("§7[§bShop] §c§oNeuer Shop");
			villager.setCustomNameVisible(true);
			
			//UserShop registrieren
			UserShop shop = new UserShop(villager.getUniqueId(), p.getUniqueId());
			shop.setTitle("§c§oNeuer Shop");
			Main.getInstance().getShopManager().addShop(shop);
		}
		else {
			p.sendMessage("§4ERROR: §cDas Land, auf dem du stehst, geh§rt dir nicht.");
			p.playSound(playerLocation, Sound.BLOCK_NOTE_BASS, 1f, 1f);
		}
	}
}
