package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import xyz.voltiac.bot.Main;

public class DiscordInvite {
    public static void discordinvite(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                   Message message = event.getMessage();
                   String messagecontent = message.getContent();
                   Member member = message.getAuthorAsMember().block();
                   String username = member.getUsername();
                    assert username != null;
                   String avatarurl = member.getAvatarUrl();
                    MessageChannel channel = (MessageChannel) message.getChannel().block();
                   if (messagecontent.equalsIgnoreCase(Main.prefix + "supportdiscord") && !member.isBot()) {
                       channel.createEmbed(EmbedCreateSpec -> {
                            EmbedCreateSpec.setTitle("**VoltiacBot Support**").setUrl("https://discord.gg/8n6NRcSnGv")
                                    .setFooter("Command Executed By: " + username, avatarurl)
                                    .setColor(Color.of(51, 153, 255));
                       }).block();
                       System.out.println("DiscordInvite Command Executed By: " + username);
                   }
                    } catch (Exception e) {
                        System.out.println("ERROR:");
                        e.printStackTrace();
                    }
                });
    }
}
