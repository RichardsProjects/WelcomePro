package net.richardsprojects.welcomepro;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ServerListener implements Listener {

	private static WelcomePro plugin;

	public ServerListener(WelcomePro plugin) {
		ServerListener.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH)
	private void playerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();

		// Remove the join message
		String message = WelcomePro.serverJoinMessage.replace("PLAYER",
				player.getName());
		e.setJoinMessage("");

		// Display the join message for all the players except the one who
		// joined
		for (Player player2 : plugin.getServer().getOnlinePlayers()) {
			String playerName = player.getName();
			if (!(player2.getName().equals(playerName))) {
				player2.sendMessage(message);
			}
		}

		if (player.hasPermission("WelcomePro.welcome")) {
			WelcomePro.playersThatShouldNotSeeChat.add(player);
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + ""
					+ WelcomePro.serverName + ":");
			player.sendMessage("");
			player.sendMessage(ChatColor.YELLOW + "    Welcome back, "
					+ ChatColor.BLUE + "" + player.getName() + "!");
			player.sendMessage("");
			if (!(WelcomePro.line1.equals(""))
					|| !(WelcomePro.line2.equals(""))
					|| !(WelcomePro.line3.equals(""))) {
				if (WelcomePro.messageType == MessageType.MOTD) {
					player.sendMessage("    " + ChatColor.RED + ""
							+ ChatColor.BOLD + "Message of the Day: ");
				} else {
					player.sendMessage("");
				}
				player.sendMessage("    " + WelcomePro.line1);
				player.sendMessage("    " + WelcomePro.line2);
				player.sendMessage("    " + WelcomePro.line3);
				player.sendMessage("");
			} else {
				player.sendMessage("    There is no Message of the Day");
				player.sendMessage("");
				player.sendMessage("");
				player.sendMessage("");
			}
			player.sendMessage(ChatColor.DARK_GREEN + ""
					+ ChatColor.STRIKETHROUGH
					+ "=====================================================");
			WelcomePro.playersThatShouldNotSeeChat.remove(player);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	private void playerLeave(PlayerQuitEvent e) {
		Player player = e.getPlayer();

		String message = WelcomePro.serverLeaveMessage.replace("PLAYER",
				player.getName());
		e.setQuitMessage(message);
	}

	@EventHandler(priority = EventPriority.HIGH)
	private void playerChat(AsyncPlayerChatEvent e) {
		// Remove any player from seeing chat who is in the list
		for (Player player : WelcomePro.playersThatShouldNotSeeChat) {
			e.getRecipients().remove(player);
		}
	}

}
