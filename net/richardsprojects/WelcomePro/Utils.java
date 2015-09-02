package net.richardsprojects.welcomepro;

import net.md_5.bungee.api.ChatColor;

public class Utils {

	public static String rtrim(String s) {
		int i = s.length() - 1;
		while (i >= 0 && Character.isWhitespace(s.charAt(i))) {
			i--;
		}
		return s.substring(0, i + 1);
	}
	
	public static String colorCodes(String msg) {
		msg = msg.replace("&0", ChatColor.BLACK + "");
		msg = msg.replace("&1", ChatColor.DARK_BLUE + "");
		msg = msg.replace("&2", ChatColor.DARK_GREEN + "");
		msg = msg.replace("&3", ChatColor.DARK_AQUA + "");
		msg = msg.replace("&4", ChatColor.DARK_RED + "");
		msg = msg.replace("&5", ChatColor.DARK_PURPLE + "");
		msg = msg.replace("&6", ChatColor.GOLD + "");
		msg = msg.replace("&7", ChatColor.GRAY + "");
		msg = msg.replace("&8", ChatColor.DARK_GRAY + "");
		msg = msg.replace("&9", ChatColor.BLUE + "");
		msg = msg.replace("&a", ChatColor.GREEN + "");
		msg = msg.replace("&b", ChatColor.AQUA + "");
		msg = msg.replace("&c", ChatColor.RED + "");
		msg = msg.replace("&d", ChatColor.LIGHT_PURPLE + "");
		msg = msg.replace("&e", ChatColor.YELLOW + "");
		msg = msg.replace("&f", ChatColor.WHITE + "");
		msg = msg.replace("&l", ChatColor.BOLD + "");
		msg = msg.replace("&m", ChatColor.STRIKETHROUGH + "");
		msg = msg.replace("&n", ChatColor.UNDERLINE + "");
		msg = msg.replace("&o", ChatColor.ITALIC + "");
		msg = msg.replace("&r", ChatColor.RESET + "");
		return msg;
	}

}
