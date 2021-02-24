package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

public class ServerIP {
    public static void ServerIP(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    Message Command = event.getMessage();
                    if ("!ip".equalsIgnoreCase(Command.getContent())) {
                        String username = Command.getAuthor().get().getUsername();
                        String avatar = Command.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = Command.getChannel().block();
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**Server IP**")
                                    .setDescription("IP: play.voltiac.xyz\n"
                                            + "\n"
                                            + "Port: 19132 (Only needed for bedrock)")
                                    .setColor(Color.of(51, 153, 255))
                                    .setFooter("Command Executed By: " + username, avatar);
                        }).block();
                        System.out.println("IP Command Executed By: " + username);
                    }
                });
    }
}
