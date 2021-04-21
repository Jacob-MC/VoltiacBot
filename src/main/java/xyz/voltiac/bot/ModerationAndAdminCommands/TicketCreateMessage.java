package xyz.voltiac.bot.ModerationAndAdminCommands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.*;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.core.spec.MessageCreateSpec;
import discord4j.rest.util.Color;
import discord4j.rest.util.Permission;

import java.time.Instant;

public class TicketCreateMessage {
    public static void TicketCreateMessage(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                   Message message = event.getMessage();
                   Member member = message.getAuthorAsMember().block();
                   String messagecontent = message.getContent();
                   String messagechannelid = message.getChannelId().toString();
                   MessageChannel channel = message.getChannel().block();

                   User user = message.getAuthor().get();
                   String username = user.getUsername();
                   String avatarurl = user.getAvatarUrl();
                   Guild guild = event.getGuild().block();
                   Snowflake guildid = guild.getId();
                    Role highestmemberrole = member.getHighestRole().block();
                    int memberrolepos = highestmemberrole.getRawPosition();
                });
    }
}
