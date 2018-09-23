package at.dalex.usershops.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import at.dalex.usershops.Main;
import at.dalex.usershops.manager.InventoryManager;
import at.dalex.usershops.manager.UserShop;

public class ShopInteractListener implements Listener {

	public ShopInteractListener() {
		Bukkit.getServer().getPluginManager().registerEvents(this, Main.getInstance());
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Villager) {
			Villager villager = (Villager) e.getRightClicked();
			//Ist ein gültiger UserShop
			if (Main.getInstance().getShopManager().isShop(villager.getUniqueId())) {
				e.setCancelled(true);
				UserShop shop = Main.getInstance().getShopManager().getShop(villager.getUniqueId());
				Inventory shopInventory = InventoryManager.createShopInventory(e.getPlayer(), shop);
				e.getPlayer().openInventory(shopInventory);
			}
		}
	}
}
