package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

public class Commands {
    public static void Commands(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                   Message message = event.getMessage();
                    if ("!commands".equalsIgnoreCase(message.getContent())) {
                        String username = message.getAuthor().get().getUsername();
                        String avatar = message.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = message.getChannel().block();
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**Discord Commands**")
                                    .addField("**!ip**", "Displays the server IP", false)
                                    .addField("**!consoles**", "Displays a guide on how to join the server on console", false)
                                    .addField("**!howtojoin**", "Displays a guide on how to join the server", false)
                                    .addField("**!rules**", "Displays the server rules", false)
                                    .addField("**!avatar (user)**", "Gets a user's avatar - Works by mentioning the user or by using the user's ID", false)
                                    .addField("**!roll (number of dice)**", "Rolls a specified amount of dice", false)
                                    .addField("**!rps (choice)**", "Plays rock, paper, scissors with the bot", false)
                                    .addField("**!help**", "Help Command", false)
                                    .setColor(Color.of(51, 153, 255))
                                    .setFooter("Command Executed By: " + username, avatar);
                        }).block();
                        System.out.println("Commands Command Executed By: " + username);
                    }
                });
    }
}
