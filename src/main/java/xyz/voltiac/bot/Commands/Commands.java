package xyz.voltiac.bot.Commands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import xyz.voltiac.bot.Main;

public class Commands {
    public static void Commands(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                        Member member = event.getMember().get();
                        Message message = event.getMessage();
                    if (message.getContent().equalsIgnoreCase(Main.prefix + "commands") && !member.isBot()) {
                        Guild guild = event.getGuild().block();
                        String username = message.getAuthor().get().getUsername();
                        String avatar = message.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = message.getChannel().block();
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**Commands - Prefix is: `" + Main.prefix + "`**")
                                    .addField("**\uD83D\uDE02 Fun Commands**", Main.prefix + "help fun - Fun Commands", true)
                                    .addField("**\uD83D\uDCA1 Miscellaneous Commands**", Main.prefix + "help misc - Miscellaneous Commands", true)
                                    .addField("**\uD83D\uDC6E Admin Commands**", Main.prefix + "help admin - Administrator Commands",  true)
                                    .addField("**\uD83C\uDFD3 Ping**", Main.prefix + "ping - Checks the bot's response time", true)
                                    .addField("**\uD83E\uDD16 Bot Invite**", Main.prefix + "botinvite - VoltiacBot Invite", true)
                                    .addField("**❓ Support Discord**", Main.prefix + "supportdiscord - VoltiacBot Support Discord Server", true)
                                    .setColor(Color.of(51, 153, 255))
                                    .setFooter("Command Executed By: " + username, avatar);
                        }).block();
                        System.out.println("Commands Command Executed By: " + username);
                    }
                    } catch (Exception e) {
                        System.out.println("ERROR:");
                        e.printStackTrace();
                    }
                });
    }
}
