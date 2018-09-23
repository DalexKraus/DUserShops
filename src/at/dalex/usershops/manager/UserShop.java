package at.dalex.usershops.manager;

import java.util.UUID;

public class UserShop extends ShopStorage {

	private UUID villagerUUID;
	private UUID shopOwner;
	private String title;
	private int earnedCash = 0;
	
	public UserShop(UUID villagerUUID, UUID shopOwner) {
		this.villagerUUID = villagerUUID;
		this.shopOwner = shopOwner;
		title = "Default UserShop";
	}
	
	public UUID getVillagerUUID() {
		return this.villagerUUID;
	}
	
	public UUID getShopOwnerUUID() {
		return this.shopOwner;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getEarnedCash() {
		return this.earnedCash;
	}

}
