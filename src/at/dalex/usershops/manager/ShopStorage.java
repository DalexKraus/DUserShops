package at.dalex.usershops.manager;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public abstract class ShopStorage {
	
	private ArrayList<ShopItemEntry> storedItems;
	private final short MAX_ITEM_AMOUNT = 16;
	
	public ShopStorage() {
		storedItems = new ArrayList<>();
	}
	
	public ArrayList<ShopItemEntry> getStoredItems() {
		return this.storedItems;
	}
	
	/**
	 * Returns true, if the item has been successfully added to the storage.
	 * Returns false, if the opposite is the case.
	 * */
	public boolean addItem(ItemStack item, int sellPrice, int buyPrice) {
		if (storedItems.size() < MAX_ITEM_AMOUNT) {
			storedItems.add(new ShopItemEntry(item, sellPrice, buyPrice));
			return true;
		}
		else return false;
	}
	
	public static class ShopItemEntry {

		private ItemStack item;
		private int sellPrice;
		private int buyPrice;

		public ShopItemEntry(ItemStack item, int sellPrice, int buyPrice) {
			this.item = item;
			this.sellPrice = sellPrice;
			this.buyPrice = buyPrice;
		}
		
		public ItemStack getItem() {
			return this.item;
		}
		
		public int getSellPrice() {
			return this.sellPrice;
		}
		
		public int getBuyPrice() {
			return this.buyPrice;
		}
	}
}
