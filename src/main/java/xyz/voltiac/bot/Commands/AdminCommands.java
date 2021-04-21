package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.Embed;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import discord4j.rest.util.Permission;

public class AdminCommands {
    public static void admincommands(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                        Message message = event.getMessage();
                        String messagecontent = message.getContent();
                        MessageChannel channel = (MessageChannel) message.getChannel().block();
                        Member member = message.getAuthorAsMember().block();
                        String username = member.getUsername();
                        assert username != null;
                        String avatar = member.getAvatarUrl();
                        if (messagecontent.startsWith("!admincommands") && member.getBasePermissions().block().contains(Permission.ADMINISTRATOR)) {
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle("**Admin Commands**")
                                        .addField("**!ban [user]**", "Bans the specified user. Can be used with id's or mentions", true)
                                        .addField("**!kick [user]**", "Kicks the specified user. Can be used with id's or mentions", true)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Command Executed By: " + username, avatar);
                            }).block();
                            message.delete().block();
                        }
                    } catch (Exception e) {
                    }
                });
    }
}
