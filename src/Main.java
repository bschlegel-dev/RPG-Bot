import com.vdurmont.emoji.EmojiManager;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.RequestBuffer;

public class Main {
	public static IMessage m;
	public static ReactionEvent rE = new ReactionEvent();
	public static IDiscordClient cli;
	public static String param;
	public static long paramL;
	public static IUser requestBy;
	public static void main(String[] args){		
		if(args.length != 1){
			System.out.println("Please enter the bots token as the first argument e.g java -jar thisjar.jar tokenhere");
			return;
		}

		cli = BotUtils.getBuiltDiscordClient(args[0]);


		// Commented out as you don't really want duplicate listeners unless you're intentionally writing your code 
		// like that.
		// Register a listener via the IListener interface
		
		cli.getDispatcher().registerListener(new IListener<MessageReceivedEvent>() {
			public void handle(MessageReceivedEvent event) {
				if(event.getMessage().getContent().startsWith(BotUtils.BOT_PREFIX + "request")) {	
					try {
						param = "<" + event.getMessage().getContent().substring(event.getMessage().getContent().indexOf("@"));
						paramL = Long.parseLong(param.substring(2, param.length()-1));
						System.out.println(paramL);
						BotUtils.sendMessage(event.getChannel(), "The first parameter is "+param);
					}catch(Exception e) {
						
					}				
					boolean success = false;
					
					try {
						System.out.println(event.getGuild().getUserByID(paramL));
						m = event.getGuild().getUserByID(paramL).getOrCreatePMChannel().sendMessage("Accept Request by "+event.getMessage().getAuthor()+"?");
					}catch(Exception e) {
						
					}
					dispatchEvent(cli.getDispatcher());
					cli.getDispatcher().registerListener(rE);
					do {
						try {							
							m.addReaction("👍");
							m.addReaction("👎");
							success = true;
							break;
						}catch(Exception e) {

						}
					}while(success == false);					
				}else {
					//event.getChannel().sendMessage("Bot Online");
				}				
				//.addReaction(":thumbsup:");
			}
		});				

		// Register a listener via the EventSubscriber annotation which allows for organisation and delegation of events				
		// Only login after all events are registered otherwise some may be missed.
		cli.login();

	}
	
	public static void dispatchEvent(EventDispatcher eD) {
		eD.unregisterListener(rE);
	}

}