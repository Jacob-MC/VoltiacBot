package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

public class FunCommands {
    public static void FunCommands(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    Message message = event.getMessage();
                    if ("!funcommands".equalsIgnoreCase(message.getContent())) {
                        String username = message.getAuthor().get().getUsername();
                        String avatar = message.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = message.getChannel().block();
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**Fun Commands**")
                                    .addField("**!avatar (user)**", "Gets a user's avatar - Works by mentioning the user or by using the user's ID", false)
                                    .addField("**!roll (number of dice)**", "Rolls a specified amount of dice (Maximum 5)", false)
                                    .addField("**!rps (choice)**", "Plays rock, paper, scissors with the bot", false)
                                    .addField("**!say**", "Repeats what you say", false)
                                    .addField("**!subreddits**", "Displays the list of subreddits you can fetch posts from", true)
                                    .setColor(Color.of(51, 153, 255))
                                    .setFooter("Command Executed By: " + username, avatar);
                        }).block();
                        message.delete().block();
                        System.out.println("Funcommands Command Executed By: " + username);
                    }
                });
    }
}
