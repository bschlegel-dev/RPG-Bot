import java.util.Arrays;

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
	final static int cmdAmount = 5;
	public static IDiscordClient cli;	
	public static Command[] cmd = new Command[cmdAmount];	
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

}