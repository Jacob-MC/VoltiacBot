package xyz.voltiac.bot.FunAndUtil;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import xyz.voltiac.bot.Main;

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
                   if (messagecontent.equalsIgnoreCase(Main.prefix + "subreddits") && !member.isBot()) {
                       channel.createEmbed(EmbedCreateSpec -> {
                           EmbedCreateSpec.setTitle("**Subreddits**")
                                   .addField("**How to Use:**", "To use these commands, just type in any of these subreddits, and press enter. Example: ```" + Main.prefix + "memes```", false)
                                   .addField("**" + Main.prefix + "memes**", "Fetches a random meme from r/memes", true)
                                   .addField("**" + Main.prefix + "dankmemes**", "Fetches a random meme from r/dankmemes", true)
                                   .addField("**" + Main.prefix + "surrealmemes**", "Fetches a random meme from r/surrealmemes", true)
                                   .addField("**" + Main.prefix + "wholesomememes**", "Fetches a random meme from r/wholesomememes", true)
                                   .addField("**" + Main.prefix + "facepalm**", "Fetches a random post from r/facepalm", true)
                                   .addField("**" + Main.prefix + "cursedcomments**", "Fetches a random post from r/cursedcomments", true)
                                   .addField("**" + Main.prefix + "rareinsults**", "Fetches a random post from r/rareinsults", true)
                                   .addField("**" + Main.prefix + "therewasanattempt**", "Fetches a random post from r/therewasanattempt", true)
                                   .addField("**" + Main.prefix + "starterpacks**", "Fetches a random post from r/starterpacks", true)
                                   .addField("**" + Main.prefix + "clevercomebacks**", "Fetches a random post from r/clevercomebacks", true)
                                   .addField("**" + Main.prefix + "aww**", "Fetches a random post from r/aww", true)
                                   .addField("**" + Main.prefix + "gaming**", "Fetches a random post from r/gaming", true)
                                   .addField("**" + Main.prefix + "bonehurtingjuice**", "Fetches a random post from r/bonehurtingjuice", true)
                                   .addField("**" + Main.prefix + "programmerhumor**", "Fetches a random post from r/programmerhumor", true)
                                   .addField("**" + Main.prefix + "eyebleach**", "Fetches a random post from r/eyebleach", true)
                                   .addField("**" + Main.prefix + "minecraft**", "Fetches a random post from r/minecraft", true)
                                   .addField("**" + Main.prefix + "animalcrossing**", "Fetches a random post from r/animalcrossing", true)
                                   .addField("**" + Main.prefix + "all**", "Fetches a random post from r/all", true)
                                   .setColor(Color.of(51, 153, 255))
                                   .setFooter("Command Executed By: " + username, avatar);
                       }).block();
                       System.out.println("Subreddits Command Executed By: " + username);
                   }
                    } catch (Exception e) {
                        System.out.println("ERROR:");
                        e.printStackTrace();
                    }
                });
    }
}
