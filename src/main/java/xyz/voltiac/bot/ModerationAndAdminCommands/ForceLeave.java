package xyz.voltiac.bot.ModerationAndAdminCommands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.Presence;
import discord4j.rest.util.Color;
import xyz.voltiac.bot.Main;

public class ForceLeave {
    public static void forceleave(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                        Message message = event.getMessage();
                        Member member = message.getAuthorAsMember().block();
                        String messagecontent = message.getContent();
                        Snowflake userid = member.getId();
                        String username = member.getUsername();
                        String avatar = member.getAvatarUrl();
                        MessageChannel channel = message.getChannel().block();
                        if (messagecontent.equalsIgnoreCase(Main.prefix + "forceleave") && userid.equals(Snowflake.of(778742764908183612L))) {
                            Snowflake guild = event.getGuildId().get();
                            channel.createMessage("Force Leaving Server.").block();
                            client.getGuildById(guild).block().leave().block();
                        }
                    } catch (Exception e) {
                    }
                });
    }
}
