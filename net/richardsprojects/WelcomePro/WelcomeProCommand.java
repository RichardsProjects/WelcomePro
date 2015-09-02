package net.richardsprojects.welcomepro;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WelcomeProCommand implements CommandExecutor {

	private WelcomePro plugin;

	public WelcomeProCommand(WelcomePro p) {
		this.plugin = p;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] args) {
		if (args.length == 0) {
			// Show help menu
			sender.sendMessage(ChatColor.BLUE
					+ "--------------- WelcomePro Commands ------------------");
			sender.sendMessage(ChatColor.GOLD
					+ "/wp set <servername|messagetype|line1|line2|line3|join|leave> <value>");
			sender.sendMessage(ChatColor.GOLD + "/wp version");
		} else {
			if (args[0].equals("set")) {
				if (args.length >= 3) {
					if (args[1].toUpperCase().equals("SERVERNAME")
							|| args[1].toUpperCase().equals("MESSAGETYPE")
							|| args[1].toUpperCase().equals("LINE1")
							|| args[1].toUpperCase().equals("LINE2")
							|| args[1].toUpperCase().equals("LINE3")
							|| args[1].toUpperCase().equals("JOIN")
							|| args[1].toUpperCase().equals("LEAVE")) {
						if (args[1].toUpperCase().equals("SERVERNAME")) {
							// Handle changing the server name

							// Set the server name to the name entered
							String serverName = "";
							boolean firstValue = true;
							boolean secondValue = true;
							for (String word : args) {
								if (firstValue) {
									firstValue = false;
								} else if (secondValue) {
									secondValue = false;
								} else {
									serverName += word + ' ';
								}
							}
							serverName = Utils.rtrim(serverName);

							if (serverName.length() > 0) {
								if (!(serverName.length() > 20)) {

									WelcomePro.serverName = serverName;

									try {
										PrintWriter out = new PrintWriter(
												WelcomePro.serverNameFile);
										out.print(serverName);
										out.close();
										sender.sendMessage(ChatColor.YELLOW
												+ "Server Name updated to \""
												+ serverName + "\"");
									} catch (FileNotFoundException e) {
										sender.sendMessage("There was an error updating the server name. Please check the file permissions.");
									}
								} else {
									int length = serverName.length();
									sender.sendMessage(ChatColor.YELLOW
											+ "The max length is 20. The name you entered is "
											+ length + " characters.");
								}
							} else {
								sender.sendMessage("You must enter a value for the server name.");
							}
						} else if (args[1].toUpperCase().equals("MESSAGETYPE")) {
							String input = args[2].toUpperCase();
							MessageType messageType = null;
							if (input.equals("MESSAGE")) {
								messageType = MessageType.MESSAGE;
								WelcomePro.messageType = messageType;

								try {
									PrintWriter out = new PrintWriter(
											WelcomePro.messageTypeFile);
									out.print("MESSAGE");
									out.close();
									sender.sendMessage(ChatColor.YELLOW
											+ "Message Type has been set to message.");
								} catch (FileNotFoundException e) {
									sender.sendMessage("There was an error updating the line. Please check the file permissions.");
								}
							} else if (input.equals("MOTD")) {
								messageType = MessageType.MOTD;
								WelcomePro.messageType = messageType;

								sender.sendMessage(ChatColor.YELLOW
										+ "Message Type has been set to Message of the Day.");
							} else {
								sender.sendMessage(ChatColor.YELLOW
										+ "That is a not a valid message type: Motd, Message");
							}
						} else if (args[1].toUpperCase().equals("LINE1")) {
							// Handle changing the message
							String line1 = "";
							boolean firstValue = true;
							boolean secondValue = true;
							for (String word : args) {
								if (firstValue) {
									firstValue = false;
								} else if (secondValue) {
									secondValue = false;
								} else {
									line1 += word + ' ';
								}
							}
							line1 = Utils.rtrim(line1);

							// Update the message
							if (line1.length() > 0) {
								if (!(line1.length() > 35)) {
									if (line1.equals("EMPTY"))
										line1 = "";
									WelcomePro.line1 = line1;

									try {
										PrintWriter out = new PrintWriter(
												WelcomePro.line1File);
										out.print(line1);
										out.close();
										sender.sendMessage(ChatColor.YELLOW
												+ "Line 1 updated to \""
												+ Utils.colorCodes(line1) + "\"");
									} catch (FileNotFoundException e) {
										sender.sendMessage("There was an error updating the line. Please check the file permissions.");
									}
								} else {
									int length = line1.length();
									sender.sendMessage(ChatColor.YELLOW
											+ "The max length is 35. The message you entered is "
											+ length + " characters.");
								}
							} else {
								sender.sendMessage("You must enter a value for line 1.");
							}
						} else if (args[1].toUpperCase().equals("LINE2")) {
							// Handle changing the message
							String line2 = "";
							boolean firstValue = true;
							boolean secondValue = true;
							for (String word : args) {
								if (firstValue) {
									firstValue = false;
								} else if (secondValue) {
									secondValue = false;
								} else {
									line2 += word + ' ';
								}
							}
							line2 = Utils.rtrim(line2);

							// Update the message
							if (line2.length() > 0) {
								if (!(line2.length() > 35)) {
									if (line2.equals("EMPTY"))
										line2 = "";
									WelcomePro.line2 = line2;

									try {
										PrintWriter out = new PrintWriter(
												WelcomePro.line2File);
										out.print(line2);
										out.close();
										sender.sendMessage(ChatColor.YELLOW
												+ "Line 2 updated to \""
												+ Utils.colorCodes(line2) + "\"");
									} catch (FileNotFoundException e) {
										sender.sendMessage("There was an error updating the line. Please check the file permissions.");
									}
								} else {
									int length = line2.length();
									sender.sendMessage(ChatColor.YELLOW
											+ "The max length is 35. The message you entered is "
											+ length + " characters.");
								}
							} else {
								sender.sendMessage("You must enter a value for line 2.");
							}
						} else if (args[1].toUpperCase().equals("LINE3")) {
							// Handle changing the message
							String line3 = "";
							boolean firstValue = true;
							boolean secondValue = true;
							for (String word : args) {
								if (firstValue) {
									firstValue = false;
								} else if (secondValue) {
									secondValue = false;
								} else {
									line3 += word + ' ';
								}
							}
							line3 = Utils.rtrim(line3);

							// Update the message
							if (line3.length() > 0) {
								if (!(line3.length() > 35)) {
									if (line3.equals("EMPTY"))
										line3 = "";
									WelcomePro.line3 = line3;

									try {
										PrintWriter out = new PrintWriter(
												WelcomePro.line3File);
										out.print(line3);
										out.close();
										sender.sendMessage(ChatColor.YELLOW
												+ "Line 3 updated to \""
												+ Utils.colorCodes(line3) + "\"");
									} catch (FileNotFoundException e) {
										sender.sendMessage("There was an error updating the line. Please check the file permissions.");
									}
								} else {
									int length = line3.length();
									sender.sendMessage(ChatColor.YELLOW
											+ "The max length is 35. The message you entered is "
											+ length + " characters.");
								}
							} else {
								sender.sendMessage("You must enter a value for line 3.");
							}
						} else if (args[1].toUpperCase().equals("JOIN")) {
							// Handle changing the message
							String joinMessage = "";
							boolean firstValue = true;
							boolean secondValue = true;
							for (String word : args) {
								if (firstValue) {
									firstValue = false;
								} else if (secondValue) {
									secondValue = false;
								} else {
									joinMessage += word + ' ';
								}
							}
							joinMessage = Utils.rtrim(joinMessage);

							// Update the message
							if (joinMessage.length() > 0) {
								if (!(joinMessage.length() > 35)) {
									if (joinMessage.equals("EMPTY"))
										joinMessage = "";
									WelcomePro.serverJoinMessage = joinMessage;

									try {
										PrintWriter out = new PrintWriter(
												WelcomePro.serverJoinMessageFile);
										out.print(joinMessage);
										out.close();
										sender.sendMessage(ChatColor.YELLOW
												+ "Join Message updated to \""
												+ Utils.colorCodes(joinMessage) + "\"");
									} catch (FileNotFoundException e) {
										sender.sendMessage("There was an" + 
											"error updating the line. Please check the file permissions.");
									}
								} else {
									int length = joinMessage.length();
									sender.sendMessage(ChatColor.YELLOW
											+ "The max length is 35. The name you entered is "
											+ length + " characters.");
								}
							} else {
								sender.sendMessage("You must enter a value for the join message.");
							}
						} else if (args[1].toUpperCase().equals("LEAVE")) {
							// Handle changing the message
							String leaveMessage = "";
							boolean firstValue = true;
							boolean secondValue = true;
							for (String word : args) {
								if (firstValue) {
									firstValue = false;
								} else if (secondValue) {
									secondValue = false;
								} else {
									leaveMessage += word + ' ';
								}
							}
							leaveMessage = Utils.rtrim(leaveMessage);

							// Update the message
							if (leaveMessage.length() > 0) {
								if (!(leaveMessage.length() > 35)) {
									if (leaveMessage.equals("EMPTY"))
										leaveMessage = "";
									WelcomePro.serverLeaveMessage = leaveMessage;

									try {
										PrintWriter out = new PrintWriter(
												WelcomePro.serverLeaveMessageFile);
										out.print(leaveMessage);
										out.close();
										sender.sendMessage(ChatColor.YELLOW
												+ "Leave Message updated to \""
												+ Utils.colorCodes(leaveMessage) + "\"");
									} catch (FileNotFoundException e) {
										sender.sendMessage("There was an error updating the line. Please check the file permissions.");
									}
								} else {
									int length = leaveMessage.length();
									sender.sendMessage(ChatColor.YELLOW
											+ "The max length is 35. The message you entered is "
											+ length + " characters.");
								}
							} else {
								sender.sendMessage("You must enter a value for the leave message.");
							}
						} else {
							// Not a value that can be set - Show Error
							sender.sendMessage(ChatColor.BLUE
									+ ""
									+ ChatColor.BOLD
									+ "That cannot be set to a value. Possible values:");
							sender.sendMessage("Servername, Messagetype, Line1, Line2, Line3, join, leave");
						}
					} else {
						// Show error - Incorrect Number of Parameters
						sender.sendMessage(ChatColor.GOLD
								+ "/wp set <servername|messagetype|line1|line2|line3|join|leave> <value>");
					}
				}
			} else if (args[0].equals("version")) {
				sender.sendMessage(ChatColor.BLUE
						+ "WelcomePro current version: "
						+ this.plugin.getDescription().getVersion());
			}
		}
		return true;
	}
}
