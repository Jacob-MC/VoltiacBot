package xyz.voltiac.bot.OtherUtil;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;

import java.io.File;

public class Subreddits {
    public static void subreddits(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
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
                                   .addField("**How to Use:**", "To use these commands, just type in any of these subreddits, and press enter. Example: ```!memes```", false)
                                   .addField("**!memes**", "Fetches a random meme from !memes", true)
                                   .addField("**!dankmemes**", "Fetches a random meme from !dankmemes", true)
                                   .addField("**!surrealmemes**", "Fetches a random meme from !surrealmemes", true)
                                   .addField("**!wholesomememes**", "Fetches a random meme from !wholesomememes", true)
                                   .addField("**!facepalm**", "Fetches a random post from !facepalm", true)
                                   .addField("**!cursedcomments**", "Fetches a random post from !cursedcomments", true)
                                   .addField("**!rareinsults**", "Fetches a random post from !rareinsults", true)
                                   .addField("**!therewasanattempt**", "Fetches a random post from !therewasanattempt", true)
                                   .addField("**!starterpacks**", "Fetches a random post from !starterpacks", true)
                                   .addField("**!clevercomebacks**", "Fetches a random post from !clevercomebacks", true)
                                   .addField("**!aww**", "Fetches a random post from !aww", true)
                                   .addField("**!gaming**", "Fetches a random post from !gaming", true)
                                   .addField("**!bonehurtingjuice**", "Fetches a random post from !bonehurtingjuice", true)
                                   .addField("**!programmerhumor**", "Fetches a random post from !programmerhumor", true)
                                   .addField("**!eyebleach**", "Fetches a random post from !eyebleach", true)
                                   .addField("**!minecraft**", "Fetches a random post from !minecraft", true)
                                   .addField("**!animalcrossing**", "Fetches a random post from !animalcrossing", true)
                                   .addField("**!all**", "Fetches a random post from !all", true)
                                   .setColor(Color.of(51, 153, 255))
                                   .setFooter("Command Executed By: " + username, avatar);
                       }).block();
                   }
                    } catch (Exception e) {
                    }
                });
    }
}
