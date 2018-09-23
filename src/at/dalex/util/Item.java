package at.dalex.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {

	public static ItemStack getItem(Material mat, int amount, int damage, String title) {
		ItemStack item = new ItemStack(mat, amount, (short) damage);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(title);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getItem(Material mat, int amount, String title) {
		ItemStack item = new ItemStack(mat, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(title);
		item.setItemMeta(meta);
		return item;
	}
	
}
