import com.darichey.discord.api.Command;
import com.darichey.discord.api.CommandRegistry;

public class Commands {
	public Commands() {		

		//A test command structure
		Command test = new Command("test");
		test.withDescription("A command for testing");
		test.onExecuted(context ->	context.getMessage().getChannel().sendMessage("Test"));
		Main.addCmd(test);
		
		Command request = new Command("request");
		request.withDescription("Sends a request to another Player");
		request.withUsage("<@username>");	
		Main.addCmd(request);
		
		//Command for Changing Hotkeys
		Command prefix = new Command("prefix");
		prefix.withDescription("Change the hotkeys of commands");
		prefix.onExecuted(context -> {			
			if(context.getArgs().length >= 1) {				
				CommandRegistry.getForClient(Main.cli).setPrefix(context.getArgs()[0]);				
				context.getMessage().getChannel().sendMessage("Prefix changed to '"+context.getArgs()[0]+"'");
				BotUtils.BOT_PREFIX = context.getArgs()[0];
			}
		});
		prefix.withUsage("<new prefix>");		
		Main.addCmd(prefix);
		
		Command help = new Command("help");
		help.withDescription("Help page for commands");		
		help.withUsage("[command]");
		Main.addCmd(help);
		
		Command example = new Command("example");		
		example.withDescription("An example for syntax highlighting");
		example.onExecuted(context -> {				
			RPGBox b = new RPGBox("Test");
			b.addLine("ab");
			RPGBox b2 = new RPGBox("Once upon a time, there was a boy");						
			b2.addLine("Test");
			b2.addGreenLine("Once upon a time, there was a boy and a girl, which were good friends");			
			b2.setHeader("Once upon a time, there was a boyOnce upon a time, there was a boy");			
			String oP2 = b2.getBox();
			context.getMessage().getChannel().sendMessage(oP2);
			context.getMessage().getChannel().sendMessage(b.getBox());		
		});		
		Main.addCmd(example);
	}
}
