package at.dalex.usershops.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	private CommandCreate commandCreate = new CommandCreate();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length < 0) {
				p.sendMessage("");
				p.sendMessage("§8---===[§1UserShops§8]===---");
				p.sendMessage("§8Folgende Befehle sind akzeptabel:");
				p.sendMessage("");
				p.sendMessage("§8/§1shop §5create");
			}
			else {
				if (args[0].equalsIgnoreCase("create")) {
					commandCreate.execute(p, args);
				}
			}
		} else sender.sendMessage("Du musst ein Spieler sein!");
		return true;
	}

}
