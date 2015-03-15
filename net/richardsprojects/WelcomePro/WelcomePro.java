package net.richardsprojects.welcomepro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class WelcomePro extends JavaPlugin {
	
	public static ArrayList<Player> playersThatShouldNotSeeChat = new ArrayList<Player>();
	
	public static String line1 = "";
	public static String line2 = "";
	public static String line3 = "";
	public static String serverName = "";
	public static String serverLeaveMessage = "";
	public static String serverJoinMessage = "";
	public static MessageType messageType = MessageType.MOTD;
	
	public File dataFolder;
	
	public static File line1File;
	public static File line2File;
	public static File line3File;
	public static File serverNameFile;
	public static File serverLeaveMessageFile;
	public static File serverJoinMessageFile;
	public static File messageTypeFile;
	
	@Override
	public void onEnable()
	{
		//Credits
		getLogger().info("WelcomePro developed by Glorfindel22");
		
		//Startup
		try {
			startup();
		} catch (FileNotFoundException e1) {
			//Shouldn't ever happen
		}
		
		//Register events
		getServer().getPluginManager().registerEvents(new ServerListener(this), this);
		
		//Register Commands
		getCommand("welcomepro").setExecutor(new WelcomeProCommand(this));
		
		//Load MotD values
		try {
			loadMotdValues();
		} catch (IOException e) {
			//Weird error that should never happen
		}
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
	private void startup() throws FileNotFoundException
	{
		dataFolder = getServer().getPluginManager().getPlugin("WelcomePro").getDataFolder();
		line1File = new File(dataFolder + File.separator + "line1.txt");
		line2File = new File(dataFolder + File.separator + "line2.txt");
		line3File  = new File(dataFolder + File.separator + "line3.txt");
		serverNameFile = new File(dataFolder + File.separator + "serverName.txt");
		serverJoinMessageFile = new File(dataFolder + File.separator + "joinMessage.txt");
		serverLeaveMessageFile = new File(dataFolder + File.separator + "leaveMessage.txt");
		messageTypeFile = new File(dataFolder + File.separator + "messageType.txt");
		
		if(!(dataFolder.exists()))
		{
			//Create the data folder
			getLogger().info("Creating the folder for this plugin");
			dataFolder.mkdirs();
			
			//Create the three files that will store information

			//TODO: Make the command give a success message when it is successfully updated
			//TODO: Change character limit
			//TODO: Allow them to reference the player by name
			//TODO: Allow them to set whether it is a message or a motd
			//TODO: Add welcomepro command
			
			try {
				line1File.createNewFile();
			} catch (IOException e) {
				//Shouldn't occur
			}
			try {
				line2File.createNewFile();
			} catch (IOException e) {
				//Shouldn't occur
			}
			try {
				line3File.createNewFile();
			} catch (IOException e) {
				//Shouldn't occur
			}
			try {
				serverNameFile.createNewFile();
			} catch (IOException e) {
				//Shouldn't occur
			}
			try {
				serverJoinMessageFile.createNewFile();
			} catch (IOException e) {
				//Shouldn't occur
			}
			try {
				serverLeaveMessageFile.createNewFile();
			} catch (IOException e) {
				//Shouldn't occur
			}
			try {
				messageTypeFile.createNewFile();
			} catch (IOException e) {

			}
			
			PrintWriter out = new PrintWriter(serverNameFile);
			out.print("Server Name");
			out.close();
			
			PrintWriter out2 = new PrintWriter(messageTypeFile);
			out2.print("MOTD");
			out2.close();
			
			PrintWriter out3 = new PrintWriter(serverLeaveMessageFile);
			out3.print("§ePLAYER has left the game.");
			out3.close();
			
			PrintWriter out4 = new PrintWriter(serverJoinMessageFile);
			out4.print("§ePLAYER has joined the game.");
			out4.close();
		}
		else
		{
			//Check to make sure all files are intact
		
			if((!line1File.exists()) || (!line2File.exists()) || (!line3File.exists()))
			{
				if(!line1File.exists())
				{
					try
					{
						line1File.createNewFile();
					} catch (IOException e) {
						//Shouldn't occur
					}
				}
				
				if(!line2File.exists())
				{
					try
					{
						line2File.createNewFile();
					} catch (IOException e) {
						//Shouldn't occur
					}
				}
				
				if(!line3File.exists())
				{
					try
					{
						line3File.createNewFile();
					} catch (IOException e) {
						//Shouldn't occur
					}
				}
				
			}
			
			if(!serverNameFile.exists())
			{
				try
				{
					serverNameFile.createNewFile();
					PrintWriter out = new PrintWriter(serverNameFile);
					out.print("Server Name");
					out.close();
				} catch (IOException e) {
					//Shouldn't occur
				}
			}
			
			if(!serverJoinMessageFile.exists())
			{
				try
				{
					serverJoinMessageFile.createNewFile();
					PrintWriter out = new PrintWriter(serverJoinMessageFile);
					out.print("§ePLAYER has joined the game.");
					out.close();
				} catch (IOException e) {
					//Shouldn't occur
				}
			}
			
			if(!serverLeaveMessageFile.exists())
			{
				try
				{
					serverLeaveMessageFile.createNewFile();
					PrintWriter out = new PrintWriter(serverLeaveMessageFile);
					out.print("§ePLAYER has left the game.");
					out.close();
				} catch (IOException e) {
					//Shouldn't occur
				}
			}
			if(!messageTypeFile.exists())
			{
				try {
					messageTypeFile.createNewFile();
				} catch (IOException e) {
					PrintWriter out = new PrintWriter(messageTypeFile);
					out.print("MOTD");
					out.close();
				}
			}			
		}
		
	}
	
	private void loadMotdValues() throws IOException
	{
		//Read the Contents of line one of the MotD:	
		if(line1File.length() > 0)
		{
			BufferedReader reader1 = new BufferedReader(new FileReader(line1File));
			String motd1 = reader1.readLine();
			if(line1File.length() > 35)
			{
				line1 = "";
			}
			else
			{
				line1 = motd1;
			}
			reader1.close();
		}	
		
		//Read the Contents of line two of the MotD:
		if(line2File.length() > 0)
		{
			BufferedReader reader2 = new BufferedReader(new FileReader(line2File));
			String motd2 = reader2.readLine();
			if(motd2.length() > 35)
			{
				line2 = "";
			}
			else
			{
				line2 = motd2;
			}	
			reader2.close();
		}
		
		//Read the Contents of line three of the MotD:
		if(line3File.length() > 0)
		{
			BufferedReader reader3 = new BufferedReader(new FileReader(line3File));
			String motd3 = reader3.readLine();
			if(motd3.length() > 35)
			{
				line3 = "";
			}
			else
			{
				line3 = motd3;
			}
			reader3.close();
		}
		
		//Read the contents of the ServerName file
		if(serverNameFile.length() > 0)
		{
			BufferedReader reader4 = new BufferedReader(new FileReader(serverNameFile));
			String string = reader4.readLine();
			if(string.length() > 15) {
				serverName = "";
			}
			else
			{
				serverName = string;
			}
			reader4.close();
		}
		
		//Read the contents of the joinMessage file
		if(serverJoinMessageFile.length() > 0)
		{
			BufferedReader reader5 = new BufferedReader(new FileReader(serverJoinMessageFile));
			String string = reader5.readLine();
			serverJoinMessage = string;
			reader5.close();
		}
		
		//Read the contents of the leaveMessage file
		if(serverLeaveMessageFile.length() > 0)
		{
			BufferedReader reader6 = new BufferedReader(new FileReader(serverLeaveMessageFile));
			String string = reader6.readLine();
			serverLeaveMessage = string;
			reader6.close();
		}
		//Read the contents of the message type file
		if(messageTypeFile.length() > 0)
		{
			BufferedReader reader7 = new BufferedReader(new FileReader(messageTypeFile));
			String string = reader7.readLine();
			if(string.equals("MESSAGE")) {
				messageType = MessageType.MESSAGE;
			}
			reader7.close();
		}
		
	}

}