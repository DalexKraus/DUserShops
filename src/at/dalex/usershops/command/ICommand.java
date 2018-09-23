package at.dalex.usershops.command;

import org.bukkit.entity.Player;

public interface ICommand {

	void execute(Player p, String[] args);
}
