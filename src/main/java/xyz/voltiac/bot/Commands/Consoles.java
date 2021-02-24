package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

public class Consoles {
    public static void Consoles(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                   Message message = event.getMessage();
                    if ("!consoles".equalsIgnoreCase(message.getContent())) {
                        String username = message.getAuthor().get().getUsername();
                        String avatar = message.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = message.getChannel().block();
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**How to join on consoles**")
                                    .setDescription("If you want to join the server on console, check this link for a tutorial on whatever console you use:\n" +
                                            "https://github.com/GeyserMC/Geyser/wiki/Using-Geyser-with-Consoles\n" +
                                            "\n" +
                                            "If your on Xbox, PlayStation or Switch and you have a android phone you can download and use this app:\n" +
                                            "https://github.com/Hellohi3654/GeyserAndroid/releases/latest\n" +
                                            "\n" +
                                            "Just connect your phone to the same network as your console go to Join a bedrock server on the home menu and click start. In Minecraft on your console go to the friends tab and you should see a LAN game join it and you should be redirected to the server.\n" +
                                            "If you need any help please just create a ticket in <#801852792276254751>")
                                    .setColor(Color.of(51, 153, 255))
                                    .setFooter("Command Executed By: " + username, avatar);
                        }).block();
                        System.out.println("Consoles message Executed By: " + username);
                    }
                });
    }
}
