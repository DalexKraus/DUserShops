package at.dalex.usershops.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import at.dalex.usershops.manager.ShopStorage.ShopItemEntry;
import at.dalex.util.Item;

public class InventoryManager {

	private static ItemStack spacer = Item.getItem(Material.STAINED_GLASS_PANE, 1, 7, " "); //Hellgraue Glasscheibe
	private static ItemStack back = Item.getItem(Material.BARRIER, 1, 0, "§4⇦ §cZurück"); 
	
	/**
	 * Erstellt die Hauptseite eines UserShops.
	 * @param p {@link Player}
	 * @param shop Der UserShop, welcher geöffnet wurde
	 * @return Das erstellte Inventar
	 */
	public static Inventory createShopInventory(Player p, UserShop shop) {
		Inventory inv = Bukkit.getServer().createInventory(null, 27, shop.getTitle());
		
		for (int i = 18; i < 27; i++) {
			inv.setItem(i, spacer);
		}
		if (shop.getShopOwnerUUID().equals(p.getUniqueId())) {
			ItemStack storage = Item.getItem(Material.CHEST, 1, "§6Shop Lager");
			ItemStack earnedCash = Item.getItem(Material.EMERALD, 1, "§aVerdientes Geld: §7" + shop.getEarnedCash());
			inv.setItem(26, back);
			inv.setItem(24, earnedCash);
			inv.setItem(25, storage);
		}
		
		for (ShopItemEntry offer : shop.getStoredItems()) {
			List<String> lore = new ArrayList<>();
			lore.add("§2Kaufen: §7" + offer.getBuyPrice());
			lore.add("§4Verkaufen: §7" + offer.getSellPrice());
			lore.add(" ");
			lore.add("§eItems verfügbar: §6" + offer.getItem().getAmount());
			
			ItemStack item = offer.getItem();
			ItemMeta offerMeta = item.getItemMeta();
			List<String> offerLore = item.getItemMeta().getLore();
			if (offerLore != null) {
				lore.add("");
				lore.add("§7Item-Beschreibung:");
				for (String line : offerLore) {
					lore.add(line);
				}
			}
			offerMeta.setLore(lore);
			item.setItemMeta(offerMeta);
			inv.addItem(item);
		}
		return inv;
	}
	
	/**
	 * Erstellt das Shop Lager eines UserShops mit Inhalt des angegebenen UserShops
	 * @param shop UserShop, von welchem die Daten gelesen werden sollen
	 * @return Das erstellte Inventar, welches den aktuellen Lagerbestand des Shops anzeigt.
	 */
	public static Inventory createStorageInventory(UserShop shop) {
		Inventory inv = Bukkit.createInventory(null, 27, shop.getTitle() + " §7- §6Lager");
		for (int i = 0; i < 9; i++) {
			inv.setItem(i, spacer);
		}
		inv.setItem(8, back);
		for (ShopItemEntry offer : shop.getStoredItems()) {
			inv.addItem(offer.getItem());
		}
		return inv;
	}
}
