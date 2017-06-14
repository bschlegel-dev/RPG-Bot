import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.RequestBuffer;

class BotUtils {

    // Constants for use throughout the bot
    static String BOT_PREFIX = "/";

    // Handles the creation and getting of a IDiscordClient object for a token
    static IDiscordClient getBuiltDiscordClient(String token){

        // The ClientBuilder object is where you will attach your params for configuring the instance of your bot.
        // Such as withToken, setDaemon etc    	
        return new ClientBuilder()
                .withToken(token)
                .build();

    }

    // Helper functions to make certain aspects of the bot easier to use.
    static IMessage sendMessage(IChannel channel, String message){    	
        // This might look weird but it'll be explained in another page.
        RequestBuffer.request(() -> {
        	IMessage r = null;
            try{
            	r = channel.sendMessage(message);
            } catch (DiscordException e){
                System.err.println("Message could not be sent with error: ");
                e.printStackTrace();
            }
            return r;
        });

		return null;
    }
        
}