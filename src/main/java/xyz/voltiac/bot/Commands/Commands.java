package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.Role;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import xyz.voltiac.bot.Main;

import java.util.List;
import java.util.Locale;

public class Commands {
    public static void Commands(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                   Message message = event.getMessage();
                    if (message.getContent().equalsIgnoreCase(Main.prefix + "commands")) {
                        Guild guild = event.getGuild().block();
                        String username = message.getAuthor().get().getUsername();
                        String avatar = message.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = message.getChannel().block();
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**Discord Commands**")
                                    .addField("**" + Main.prefix + "funcommands**", "Fun discord commands", false)
                                    .addField("**" + Main.prefix + "admincommands**", "Administrator discord commands", false)
                                    .addField("**" + Main.prefix + "help**", "Help Command", false)
                                    .addField("**" + Main.prefix + "botinvite**", "Authorization link for VoltiacBot", false)
                                    .addField("**" + Main.prefix + "supportdiscord**", "Invite link for VoltiacBot support server", false)
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
