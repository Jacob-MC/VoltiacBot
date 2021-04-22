package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import xyz.voltiac.bot.Main;

import java.util.Locale;

public class BotInvite {
    public static void botinvite(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                    Message message = event.getMessage();
                    String messagecontent = message.getContent();
                    User member = message.getAuthorAsMember().block();
                    String username = member.getUsername();
                    assert username != null;
                    String avatarurl = member.getAvatarUrl();
                    MessageChannel channel = (MessageChannel) message.getChannel().block();
                    if (messagecontent.equalsIgnoreCase(Main.prefix + "botinvite")) {
                        channel.createEmbed(EmbedCreateSpec -> {
                            EmbedCreateSpec.setTitle("**VoltiacBot Invite**").setUrl("https://discord.com/oauth2/authorize?client_id=809487051564908576&scope=bot&permissions=8")
                                    .setFooter("Command Executed By: " + username, avatarurl)
                                    .setColor(Color.of(51, 153, 255));
                        }).block();
                        System.out.println("BotInvite Command Executed By: " + username);
                    }
                    } catch (Exception e) {
                    }
                });
    }
}
