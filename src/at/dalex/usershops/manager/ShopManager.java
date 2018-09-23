package at.dalex.usershops.manager;

import java.util.ArrayList;
import java.util.UUID;

public class ShopManager {

	private ArrayList<UserShop> userShops = new ArrayList<>();

	public void addShop(UserShop shop) {
		userShops.add(shop);
		System.out.println("added shop");
	}

	public void removeShop(UserShop shop) {
		if (userShops.contains(shop)) {
			userShops.remove(shop);
		}
	}

	public UserShop getShop(UUID villagerId) {
		UserShop targetShop = null;
		for (UserShop shop : userShops) {
			if (shop.getVillagerUUID().equals(villagerId)) {
				targetShop = shop;
			}
		}
		return targetShop;
	}

	public UserShop getShopByInventory(String inventoryTitle) {
		for (UserShop shop : userShops) {
			if (shop.getTitle().equals(inventoryTitle)) {
				return shop;
			}
		}
		return null;
	}

	public boolean isShopInventory(String inventoryTitle) {
		return (getShopByInventory(inventoryTitle) != null);
	}

	public boolean isShop(UUID villagerId) {
		return (getShop(villagerId) != null);
	}
}
