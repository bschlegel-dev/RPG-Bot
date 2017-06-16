import java.util.Arrays;

import org.json.simple.JSONObject;

import com.darichey.discord.api.Command;
import com.darichey.discord.api.CommandRegistry;
import com.vdurmont.emoji.EmojiManager;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.util.RequestBuffer;

public class Main {
	final static int cmdAmount = 6;
	public static IDiscordClient cli;	
	public static Command[] cmd = new Command[0];	
	public static IGuild iG;
	
	public static void main(String[] args){		
		if(args.length != 1){
			System.out.println("Please enter the bots token as the first argument e.g java -jar thisjar.jar tokenhere");
			return;
		}			
		//Initialising Client
		cli = BotUtils.getBuiltDiscordClient(args[0]);		
		cli.login();
		
		//Initialising Commands		
		Commands c = new Commands();		
		Request r = new Request();		
		Help h = new Help();		
	}
	
	
	/**
	 * Gets a command from the Command array
	 * @param name the name of the command
	 * @return the Command with the name of the parameter (null if not found)
	 */
	public static Command getCommand(String name) {
		for(int i = 0; i < cmd.length; i++) {
			if(cmd[i].getName().equalsIgnoreCase(name)) {
				return cmd[i];
			}
		}
		return null;
	}
	
	/**
	 * Adds a Command to the command Array and dynamically increases the arrays size if there is not enough
	 * @param newCmd the command that gets added to the Array (appends it at the end of the array)
	 */
	public static void addCmd(Command newCmd) {
		if(cmd.length == 0) {
			cmd = new Command[1];
			cmd[0] = newCmd;
		}else {
			boolean full = false;
			int tempInt = 0;
			
			for(int i = 0; i < cmd.length; i++) {
				if(cmd[i] != null) {
					tempInt++;
				}
			}
			
			if(tempInt == cmd.length) {
				full = true;
			}
			
			if(!full) {
				for(int i = 0; i < cmd.length; i++) {
					if(cmd[i] != null) {
						cmd[i] = newCmd;
					}
				}
			}else {				
				Command[] newcmd = new Command[cmd.length+1];				
				for(int i = 0; i < cmd.length; i++) {
					newcmd[i] = cmd[i];
				}
				newcmd[newcmd.length-1] = newCmd;
				
				cmd = newcmd;
			}
		}
		CommandRegistry.getForClient(cli).register(newCmd);
	}

}