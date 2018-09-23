package at.dalex.usershops;

import org.bukkit.plugin.java.JavaPlugin;

import at.dalex.usershops.command.Commands;
import at.dalex.usershops.listener.ShopInteractListener;
import at.dalex.usershops.listener.ShopMainPageClickListener;
import at.dalex.usershops.listener.ShopStorageClickListener;
import at.dalex.usershops.listener.ShopStorageSaveListener;
import at.dalex.usershops.manager.InventoryManager;
import at.dalex.usershops.manager.ShopManager;

public class Main extends JavaPlugin {
	
	private static Main instance;
	private ShopManager shopManager;
	
	@Override
	public void onEnable() {
		instance = this;
		shopManager = new ShopManager();
		
		/* Listeners */
		new ShopInteractListener();
		new ShopMainPageClickListener();
		new ShopStorageClickListener();
		new ShopStorageSaveListener();
		
		getCommand("shop").setExecutor(new Commands());
		new InventoryManager();
	}
	
	@Override
	public void onDisable() {

	}
	
	public ShopManager getShopManager() {
		return this.shopManager;
	}
	
	public static Main getInstance() {
		return instance;
	}
}
