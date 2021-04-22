package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.Role;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

import java.util.List;
import java.util.Locale;

public class Commands {
    public static void Commands(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                   Message message = event.getMessage();
                    if ("!commands".equalsIgnoreCase(message.getContent())) {
                        Guild guild = event.getGuild().block();
                        String username = message.getAuthor().get().getUsername();
                        String avatar = message.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = message.getChannel().block();
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**Discord Commands**")
                                    .addField("**!funcommands**", "Fun discord commands", false)
                                    .addField("**!admincommands**", "Administrator discord commands", false)
                                    .addField("**!help**", "Help Command", false)
                                    .addField("**!botinvite**", "Authorization link for VoltiacBot", false)
                                    .addField("**!supportdiscord**", "Invite link for VoltiacBot support server", false)
                                    .setColor(Color.of(51, 153, 255))
                                    .setFooter("Command Executed By: " + username, avatar);
                        }).block();
                        System.out.println("Commands Command Executed By: " + username);
                    }
                    } catch (Exception e) {
                    }
                });
    }
}
