package xyz.voltiac.bot.OtherUtil;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;

public class Subreddits {
    public static void subreddits(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                   Message message = event.getMessage();
                   String messagecontent = message.getContent();
                    User member = message.getAuthorAsMember().block();
                    String username = member.getUsername();
                    assert username != null;
                    String avatar = member.getAvatarUrl();
                    MessageChannel channel = (MessageChannel) message.getChannel().block();
                   if (messagecontent.equalsIgnoreCase("!subreddits")) {
                       channel.createEmbed(EmbedCreateSpec -> {
                           EmbedCreateSpec.setTitle("**Subreddits**")
                                   .addField("**How to Use:**", "To use these commands, just type in any of these subreddits, and press enter. Example: ```r/memes```", false)
                                   .addField("**r/memes**", "Fetches a random meme from r/memes", true)
                                   .addField("**r/dankmemes**", "Fetches a random meme from r/dankmemes", true)
                                   .addField("**r/surrealmemes**", "Fetches a random meme from r/surrealmemes", true)
                                   .addField("**r/wholesomememes**", "Fetches a random meme from r/wholesomememes", true)
                                   .addField("**r/facepalm**", "Fetches a random post from r/facepalm", true)
                                   .addField("**r/cursedcomments**", "Fetches a random post from r/cursedcomments", true)
                                   .addField("**r/rareinsults**", "Fetches a random post from r/rareinsults", true)
                                   .addField("**r/therewasanattempt**", "Fetches a random post from r/therewasanattempt", true)
                                   .addField("**r/starterpacks**", "Fetches a random post from r/starterpacks", true)
                                   .addField("**r/clevercomebacks**", "Fetches a random post from r/clevercomebacks", true)
                                   .addField("**r/aww**", "Fetches a random post from r/aww", true)
                                   .addField("**r/gaming**", "Fetches a random post from r/gaming", true)
                                   .addField("**r/bonehurtingjuice**", "Fetches a random post from r/bonehurtingjuice", true)
                                   .addField("**r/programmerhumor**", "Fetches a random post from r/programmerhumor", true)
                                   .addField("**r/eyebleach**", "Fetches a random post from r/eyebleach", true)
                                   .addField("**r/minecraft**", "Fetches a random post from r/minecraft", true)
                                   .addField("**r/animalcrossing**", "Fetches a random post from r/animalcrossing", true)
                                   .addField("**r/all**", "Fetches a random post from r/all", true)
                                   .setColor(Color.of(51, 153, 255))
                                   .setFooter("Command Executed By: " + username, avatar);
                       }).block();
                       message.delete().block();
                   }
                });
    }
}
