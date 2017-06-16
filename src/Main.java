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
		cli = BotUtils.getBuiltDiscordClient(args[0]);
		
		//A test command structure
		cmd[0] = new Command("test");
		cmd[0].withDescription("A command for testing");
		cmd[0].onExecuted(context ->	context.getMessage().getChannel().sendMessage("Test"));
		CommandRegistry.getForClient(Main.cli).register(Main.cmd[0]);
		
		cmd[1] = new Command("request");
		cmd[1].withDescription("Sends a request to another Player");
		cmd[1].withUsage("request @bob");			
		
		//Command for Changing Hotkeys
		cmd[2] = new Command("prefix");
		cmd[2].withDescription("Change the hotkeys of commands");
		cmd[2].onExecuted(context -> {			
			if(context.getArgs().length >= 1) {				
				CommandRegistry.getForClient(cli).setPrefix(context.getArgs()[0]);				
				context.getMessage().getChannel().sendMessage("Prefix changed to '"+context.getArgs()[0]+"'");
				BotUtils.BOT_PREFIX = context.getArgs()[0];
			}
		});
		CommandRegistry.getForClient(Main.cli).register(Main.cmd[2]);
		
		cmd[3] = new Command("help");
		cmd[3].withDescription("Help page for commands");
		cmd[3].onExecuted(context -> {	
			String[] names = new String[cmd.length];
			String[] description = new String[cmd.length];
			for(int i = 0; i < cmd.length; i++) {
				names[i] = cmd[i].getName();
				description[i] = cmd[i].getDescription();
			}
			
			for (int n = 0; n < cmd.length; n++) {
		        for (int m = 0; m < 4 - n; m++) {
		            if ((names[m].compareTo(names[m + 1])) > 0) {
		                String swapString = names[m];
		                names[m] = names[m + 1];
		                names[m + 1] = swapString;
		                String swapString2 = description[m];
		                description[m] = description[m + 1];
		                description[m + 1] = swapString2;
		            }
		        }
		    }
			String oP = "```diff\n";
			for(int i = 0; i < cmd.length; i++) {				
				oP += "- "+names[i]+"\n+ "+description[i]+"\n";
			}
			oP += "\n\nCurrent prefix is '"+BotUtils.BOT_PREFIX+"'```";
			context.getMessage().getChannel().sendMessage(oP);
		});
		CommandRegistry.getForClient(Main.cli).register(Main.cmd[3]);
		
		cmd[4] = new Command("example");
		cmd[4].withDescription("An example for syntax highlighting");
		cmd[4].onExecuted(context -> {	
			String oP = "```diff\n+ test\n- test```";
			context.getMessage().getChannel().sendMessage(oP);
		});
		CommandRegistry.getForClient(Main.cli).register(Main.cmd[4]);
		cli.login();
		
		Request r = new Request();		
	}

}