package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

public class HowToJoin {
    public static void HowToJoin(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    Message message = event.getMessage();
                    if ("!howtojoin".equalsIgnoreCase(message.getContent())) {
                        String username = message.getAuthor().get().getUsername();
                        String avatar = message.getAuthor().get().getAvatarUrl();

                        MessageChannel channel = message.getChannel().block();
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**How to join the server**")
                                    .setDescription("> Mobile:\n" +
                                            "> If you are on mobile, go to the servers tab, scroll to the bottom and press `Add Server`. In the server name put `Voltiac Network`, and for the server address put `play.voltiac.xyz` and leave the default port.\n" +
                                            "\n" +
                                            "> Console:\n" +
                                            "> If you are on console, click this link:\n" +
                                            "> https://github.com/GeyserMC/Geyser/wiki/Using-Geyser-with-Consoles\n" +
                                            "> and follow the tutorial for whichever console you use. You can also use this app if you have an android to connect to the server: https://github.com/Hellohi3654/GeyserAndroid/releases/latest\n" +
                                            "\n" +
                                            "> Java:\n" +
                                            "> If you are on Java, go to multiplayer, click add server, and put `play.voltiac.xyz` in the server address.")
                                    .setColor(Color.of(51, 153, 255))
                                    .setFooter("Command Executed By: " + username, avatar);
                        }).block();
                        System.out.println("Howtojoin Command Executed By: " + username);
                    }
                });
    }
}
