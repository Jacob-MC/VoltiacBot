package xyz.voltiac.bot.ModerationAndAdminCommands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import discord4j.rest.util.Permission;

public class AdminCommands {
    public static void admincommands(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                        Message message = event.getMessage();
                        Member member = message.getAuthorAsMember().block();
                        Guild guild = event.getGuild().block();
                        String username = message.getAuthor().get().getUsername();
                        String avatar = message.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = message.getChannel().block();
                        if ("!admincommands".equalsIgnoreCase(message.getContent()) && member.getBasePermissions().block().contains(Permission.ADMINISTRATOR)) {
                            channel.createEmbed(embedCreateSpec -> {
                                embedCreateSpec.setTitle("**Admin Commands**")
                                        .addField("**!ban [user]**", "Bans the specified user. Can be used with mentions or ID's", false)
                                        .addField("**!kick [user]**", "Bans the specified user. Can be used with mentions or ID's", false)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Command Executed By: " + username, avatar);
                            }).block();
                            System.out.println("Commands Command Executed By: " + username);
                        } else if ("!admincommands".equalsIgnoreCase(message.getContent()) && !member.getBasePermissions().block().contains(Permission.ADMINISTRATOR)) {
                            channel.createMessage("You do not have permission to use this command!").block();
                        }
                    } catch (Exception e) {
                    }
                });
    }
}
