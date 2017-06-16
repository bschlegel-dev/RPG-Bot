import com.darichey.discord.api.Command;
import com.darichey.discord.api.CommandRegistry;

public class Commands {
	public Commands() {		

		//A test command structure
		Main.cmd[0] = new Command("test");
		Main.cmd[0].withDescription("A command for testing");
		Main.cmd[0].onExecuted(context ->	context.getMessage().getChannel().sendMessage("Test"));
		CommandRegistry.getForClient(Main.cli).register(Main.cmd[0]);
		
		Main.cmd[1] = new Command("request");
		Main.cmd[1].withDescription("Sends a request to another Player");
		Main.cmd[1].withUsage("request @bob");			
		
		//Command for Changing Hotkeys
		Main.cmd[2] = new Command("prefix");
		Main.cmd[2].withDescription("Change the hotkeys of commands");
		Main.cmd[2].onExecuted(context -> {			
			if(context.getArgs().length >= 1) {				
				CommandRegistry.getForClient(Main.cli).setPrefix(context.getArgs()[0]);				
				context.getMessage().getChannel().sendMessage("Prefix changed to '"+context.getArgs()[0]+"'");
				BotUtils.BOT_PREFIX = context.getArgs()[0];
			}
		});
		CommandRegistry.getForClient(Main.cli).register(Main.cmd[2]);
		
		Main.cmd[3] = new Command("help");
		Main.cmd[3].withDescription("Help page for commands");		
		
		Main.cmd[4] = new Command("example");
		Main.cmd[4].withDescription("An example for syntax highlighting");
		Main.cmd[4].onExecuted(context -> {	
			String oP = "```diff\n- test\n+ test```";
			context.getMessage().getChannel().sendMessage(oP);
		});
		CommandRegistry.getForClient(Main.cli).register(Main.cmd[4]);
	}
}
