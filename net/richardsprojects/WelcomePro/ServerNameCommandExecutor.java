package net.richardsprojects.welcomepro;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ServerNameCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(args.length > 0)
		{
			String name = "";
			int numberOfIterations = 0;
			
			//Combine all words as parameters to create name
			for (String word: args) {
				numberOfIterations ++;
				if(numberOfIterations == args.length)
				{
					name += word;
				}
				else
				{
					name += word + ' ';
				}
		    }
			
			//Make sure the name is not more than 15 characters
			if(name.length() > 15)
			{
				sender.sendMessage("The server name cannot be longer than 15 characters.");
				return true;
			}
			
			//Set the server name to the name entered
			WelcomePro.serverName = name;
			
			
			try {
				PrintWriter out = new PrintWriter(WelcomePro.serverNameFile);
				out.print(name);
				out.close();
			} catch (FileNotFoundException e) {
				//Shouldn't happen
			}
			
			return true;
		}
		else
		{
			return false;
		}
		
	}

}
