package xyz.voltiac.bot.ModerationAndAdminCommands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.Role;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Permission;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class LockdownCommand {
    public static void LockdownCommand(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    Message Command = event.getMessage();
                    Member member = Command.getAuthorAsMember().block();
                    if ("!lockdown".equalsIgnoreCase(Command.getContent()) && member.getBasePermissions().block().contains(Permission.ADMINISTRATOR)) {
                        String username = Command.getAuthor().get().getUsername();
                        String avatar = Command.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = Command.getChannel().block();
                        Guild guild = (Guild) event.getGuild().block();
                        Snowflake guildid = guild.getId();
                        String roles = client.getGuildRoles(guildid).toString();
                        System.out.println(roles);
                        System.out.println("Lockdown Start Command Executed By: " + username);

                    }
                    if ("!lockdown end".equalsIgnoreCase(Command.getContent()) && member.getBasePermissions().block().contains(Permission.ADMINISTRATOR)) {
                        String username = Command.getAuthor().get().getUsername();
                        String avatar = Command.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = Command.getChannel().block();

                        System.out.println("Lockdown End Command Executed By: " + username);
                    }
                });
    }
}
