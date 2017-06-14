import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;

public class ReactionEvent {

	@EventSubscriber
	public void handle(ReactionAddEvent event) {
		if(event.getMessage().equals(Main.m)) {
			if(!(event.getUser().getLongID() == event.getClient().getOurUser().getLongID())) {				
				System.out.println(event.getReaction().getClientReacted());
				if(event.getReaction().getUnicodeEmoji().getUnicode().equals("👍")) {					
					event.getGuild().getUserByID(Main.paramL).getOrCreatePMChannel().sendMessage(" accepted your Request");
					Main.dispatchEvent(Main.cli.getDispatcher());					
				}else if(event.getReaction().getUnicodeEmoji().getUnicode().equals("👎")) {										
					event.getGuild().getUserByID(Main.paramL).getOrCreatePMChannel().sendMessage(" rejected your Request");
					Main.dispatchEvent(Main.cli.getDispatcher());
				}
			}else {
				System.out.println("Reaction by Bot user");
			}
		}
	}

}