package net.richardsprojects.welcomepro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MotdCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(args.length > 1)
		{
			//Begin Checking parameters
			
			//Check first parameter
			int motdfile = 0;
			try
			{
				motdfile = Integer.parseInt(args[0]);
			}catch(NumberFormatException e)
			{
				sender.sendMessage("The first parameter must be an integer value 1-3. This parameter represents the line of the Message of the Day you are setting.");
				return true;
			}
			if(motdfile > 3 || motdfile < 1) {
				sender.sendMessage("The first parameter must be an integer value 1-3. This parameter represents the line of the Message of the Day you are setting.");
				return true;
			}
			
			//Check second Parameter
			if(args[1].equals("EMPTY"))
			{
				File fileToPrintTo = new File("");
				if(motdfile == 1)
				{
					fileToPrintTo = WelcomePro.motd1;
					WelcomePro.motd_line1 = "";
				}
				if(motdfile == 2)
				{
					fileToPrintTo = WelcomePro.motd2;
					WelcomePro.motd_line2 = "";
				}	
				if(motdfile == 3)
				{
					fileToPrintTo = WelcomePro.motd3;
					WelcomePro.motd_line3 = "";
				}
			
				try
				{
					PrintWriter out = new PrintWriter(fileToPrintTo);
					out.print("");
					out.close();
				}
				catch (FileNotFoundException e) {
					//Super weird error that shouldn't happen because everything should have been checked beforehand
				}

			}
			else
			{
				String message = "";
				boolean firstValue = true;
				for (String word: args) {
					if(firstValue)
					{
						firstValue = false;
					}
					else
					{
						message += word + ' ';
					}		    	
				}
		   	
				if(message.length() > 30)
				{
					sender.sendMessage("The message is too long, at max it must be 30 characters on each line.");
					return true;
				}
			
				//At this point all parameters have been checked and it should be safe to save them to the file
				File fileToPrintTo = new File("");
				if(motdfile == 1)
				{
					fileToPrintTo = WelcomePro.motd1;
					WelcomePro.motd_line1 = message;
				}
				if(motdfile == 2)
				{
					fileToPrintTo = WelcomePro.motd2;
					WelcomePro.motd_line2 = message;
				}	
				if(motdfile == 3)
				{
					fileToPrintTo = WelcomePro.motd3;
					WelcomePro.motd_line3 = message;
				}
			
				try
				{
					PrintWriter out = new PrintWriter(fileToPrintTo);
					out.print(message);
					out.close();
				}
				catch (FileNotFoundException e) {
					//Super weird error that shouldn't happen because everything should have been checked beforehand
				}
			
			}
		}
		else
		{
			sender.sendMessage("Not enough parameters");
			return false;
		}
		
		return true;
	}
	
}
