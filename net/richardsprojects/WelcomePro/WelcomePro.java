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
	
	public static String motd_line1 = "";
	public static String motd_line2 = "";
	public static String motd_line3 = "";
	public static String serverName = "";
	public static String serverLeaveMessage = "";
	public static String serverJoinMessage = "";
	
	public File dataFolder;
	
	public static File motd1;
	public static File motd2;
	public static File motd3;
	public static File serverNameFile;
	public static File serverLeaveMessageFile;
	public static File serverJoinMessageFile;
	
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
		getCommand("motd").setExecutor(new MotdCommandExecutor());
		getCommand("servername").setExecutor(new ServerNameCommandExecutor());
		
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
		motd1 = new File(dataFolder + File.separator + "motd1.txt");
		motd2 = new File(dataFolder + File.separator + "motd2.txt");
		motd3 = new File(dataFolder + File.separator + "motd3.txt");
		serverNameFile = new File(dataFolder + File.separator + "serverName.txt");
		serverJoinMessageFile = new File(dataFolder + File.separator + "joinMessage.txt");
		serverLeaveMessageFile = new File(dataFolder + File.separator + "leaveMessage.txt");
		
		if(!(dataFolder.exists()))
		{
			//Create the data folder
			getLogger().info("Creating the folder for this plugin");
			dataFolder.mkdirs();
			
			//Create the three files that will store information

			
			try {
				motd1.createNewFile();
			} catch (IOException e) {
				//Shouldn't occur
			}
			try {
				motd2.createNewFile();
			} catch (IOException e) {
				//Shouldn't occur
			}
			try {
				motd3.createNewFile();
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
			
			PrintWriter out = new PrintWriter(serverNameFile);
			out.print("Server Name");
			out.close();
			
		}
		else
		{
			//Check to make sure all files are intact
		
			if((!motd1.exists()) || (!motd2.exists()) || (!motd3.exists()))
			{
				if(!motd1.exists())
				{
					try
					{
						motd1.createNewFile();
					} catch (IOException e) {
						//Shouldn't occur
					}
				}
				
				if(!motd2.exists())
				{
					try
					{
						motd2.createNewFile();
					} catch (IOException e) {
						//Shouldn't occur
					}
				}
				
				if(!motd3.exists())
				{
					try
					{
						motd3.createNewFile();
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
		}
		
	}
	
	private void loadMotdValues() throws IOException
	{
		//Read the Contents of line one of the MotD:	
		if(motd1.length() > 0)
		{
			BufferedReader reader1 = new BufferedReader(new FileReader(motd1));
			String motd1 = reader1.readLine();
			if(motd1.length() > 30)
			{
				motd_line1 = "";
			}
			else
			{
				motd_line1 = motd1;
			}
			reader1.close();
		}	
		
		//Read the Contents of line two of the MotD:
		if(motd2.length() > 0)
		{
			BufferedReader reader2 = new BufferedReader(new FileReader(motd2));
			String motd2 = reader2.readLine();
			if(motd2.length() > 30)
			{
				motd_line2 = "";
			}
			else
			{
				motd_line2 = motd2;
			}	
			reader2.close();
		}
		
		//Read the Contents of line three of the MotD:
		if(motd3.length() > 0)
		{
			BufferedReader reader3 = new BufferedReader(new FileReader(motd3));
			String motd3 = reader3.readLine();
			if(motd3.length() > 30)
			{
				motd_line3 = "";
			}
			else
			{
				motd_line3 = motd3;
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
		
	}

}
