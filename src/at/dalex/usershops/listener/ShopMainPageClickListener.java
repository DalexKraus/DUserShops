package at.dalex.usershops.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import at.dalex.usershops.Main;
import at.dalex.usershops.manager.InventoryManager;
import at.dalex.usershops.manager.ShopStorage.ShopItemEntry;
import at.dalex.usershops.manager.UserShop;

public class ShopMainPageClickListener implements Listener {

	public ShopMainPageClickListener() {
		Bukkit.getServer().getPluginManager().registerEvents(this, Main.getInstance());
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (Main.getInstance().getShopManager().isShopInventory(e.getInventory().getTitle())) {
			Player p = (Player) e.getWhoClicked();
			UserShop shop = Main.getInstance().getShopManager().getShopByInventory(e.getInventory().getTitle());
			
			/* Items in der Auslage aktualisieren */
//			updateShopOffers(shop, e.getInventory());
			
			if (e.getCurrentItem() != null) {
				if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
					String itemName = e.getCurrentItem().getItemMeta().getDisplayName();
					if (itemName.equals(" ") || itemName.equals("§4⇦ §cZurück") || itemName.equals("§6Shop Lager") || itemName.contains("§aVerdientes Geld:")) {
						/* GUI Ausgewählt */
						e.setCancelled(true);
						if (itemName.equals("§6Shop Lager")) {
							Inventory storageInventory = InventoryManager.createStorageInventory(shop);
							p.openInventory(storageInventory);
						}
						else if (itemName.equals("§4⇦ §cZurück")) {
							if (p.getOpenInventory() != null) {
								p.closeInventory();
							}
						}
					}
				}
				/* Item zum Verkauf angeklickt */
				else {
					/* Shopbesitzer verändert Inhalt */
					if (shop.getShopOwnerUUID().equals(p.getUniqueId())) {
						
					}
					/* Jemand kauft etwas */
				}
			}
		}
	}
	
	private void updateShopOffers(UserShop shop, Inventory inv) {
		List<ItemStack> addedItems = new ArrayList<ItemStack>();
		for (ShopItemEntry offer : shop.getStoredItems()) {
			if (!addedItems.contains(offer.getItem())) {
				addedItems.add(offer.getItem());
			}
		}
		for (ItemStack item : addedItems) {
			item.setAmount(1);
		}
		for (int i = 0; i < 18; i++) {
			inv.setItem(i, new ItemStack(Material.AIR));
		}
		for (ItemStack offer : addedItems) {
			System.out.println("ItemName: " + offer.getItemMeta().getDisplayName());
			inv.addItem(offer);
		}
	}
}
