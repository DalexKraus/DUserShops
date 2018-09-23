package at.dalex.usershops.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import at.dalex.usershops.Main;
import at.dalex.usershops.manager.ShopStorage.ShopItemEntry;
import at.dalex.usershops.manager.UserShop;

public class ShopStorageSaveListener implements Listener {

	public ShopStorageSaveListener() {
		Bukkit.getServer().getPluginManager().registerEvents(this, Main.getInstance());
	}

	@EventHandler
	public void onCloseInventory(InventoryCloseEvent e) {
		String inventoryTitle = e.getInventory().getName();
		if (inventoryTitle.contains(" §7- §6Lager")) {
			String shopName = inventoryTitle.replaceAll(" §7- §6Lager", "");
			if (Main.getInstance().getShopManager().isShopInventory(shopName)) {
				/* Lager überschreiben */
				UserShop shop = Main.getInstance().getShopManager().getShopByInventory(shopName);
				shop.getStoredItems().clear();
				for (int i = 9; i < 27; i++) {
					ItemStack item = e.getInventory().getItem(i);
					if (item != null && item.getType() != Material.AIR) {
						shop.getStoredItems().add(new ShopItemEntry(item, 200, 220));
					}
				}
			}
		}
	}
}
