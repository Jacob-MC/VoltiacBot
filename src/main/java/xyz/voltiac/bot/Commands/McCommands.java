package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

public class McCommands {
    public static void McCommands(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    Message message = event.getMessage();
                    if ("!mccommands".equalsIgnoreCase(message.getContent())) {
                        String username = message.getAuthor().get().getUsername();
                        String avatar = message.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = message.getChannel().block();
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**MC Commands**")
                                    .addField("**!ip**", "Displays the server IP", false)
                                    .addField("**!consoles**", "Displays a guide on how to join the server on console", false)
                                    .addField("**!howtojoin**", "Displays a guide on how to join the server", false)
                                    .addField("**!rules**", "Displays the server rules", false)
                                    .setColor(Color.of(51, 153, 255))
                                    .setFooter("Command Executed By: " + username, avatar);
                        }).block();
                        System.out.println("MCcommands Command Executed By: " + username);
                    }
                });
    }
}
