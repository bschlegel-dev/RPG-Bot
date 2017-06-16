import com.darichey.discord.api.Command;
import com.darichey.discord.api.CommandRegistry;

public class Help {
	public Help() {
		Main.cmd[3].onExecuted(context -> {				
			String[] names = new String[Main.cmd.length];
			String[] description = new String[Main.cmd.length];
			for(int i = 0; i < Main.cmd.length; i++) {
				names[i] = Main.cmd[i].getName();
				description[i] = Main.cmd[i].getDescription();
			}
			
			for (int n = 0; n < Main.cmd.length; n++) {
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
			for(int i = 0; i < Main.cmd.length; i++) {				
				oP += "- "+names[i]+"\n+ "+description[i]+"\n\n";
			}
			oP += "Current prefix is '"+BotUtils.BOT_PREFIX+"'```";
			context.getMessage().getChannel().sendMessage(oP);
		});
		CommandRegistry.getForClient(Main.cli).register(Main.cmd[3]);
	}
}
