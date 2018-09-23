package at.dalex.usershops.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import at.dalex.usershops.Main;
import at.dalex.usershops.manager.InventoryManager;
import at.dalex.usershops.manager.UserShop;

public class ShopStorageClickListener implements Listener {

	public ShopStorageClickListener() {
		Bukkit.getServer().getPluginManager().registerEvents(this, Main.getInstance());
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getInventory().getName().contains(" §7- §6Lager")) {
			String shopName = e.getInventory().getName().replaceAll(" §7- §6Lager", "");
			if (Main.getInstance().getShopManager().isShopInventory(shopName)) {
				if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
					Player p = (Player) e.getWhoClicked();
					UserShop shop = Main.getInstance().getShopManager().getShopByInventory(shopName);
					if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§4⇦ §cZurück")) {
						Inventory inv = InventoryManager.createShopInventory(p, shop);
						p.openInventory(inv);
					}
				}
			}
		}
	}
}
