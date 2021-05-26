package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import xyz.voltiac.bot.Main;

public class Ping {
    public static void ping(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                        int ping = client.getGatewayClient(event.getShardInfo().getIndex()).get().getResponseTime().getNano() / 1000000;
                        Message message = event.getMessage();
                        Member member = message.getAuthorAsMember().block();
                        String username = member.getUsername();
                        String avatar = member.getAvatarUrl();
                        MessageChannel channel = message.getChannel().block();
                        if (message.getContent().equalsIgnoreCase(Main.prefix + "ping") && !member.isBot()) {
                            channel.createEmbed(embedCreateSpec -> {
                                embedCreateSpec.setTitle("**\uD83C\uDFD3 Pong!**")
                                        .setDescription("Bot Response Time: " + ping + "ms")
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Command Executed By: " + username, avatar);
                            }).block();
                        }
                    } catch (Exception e) {
                        System.out.println("ERROR:");
                        e.printStackTrace();
                    }
                });
    }
}
