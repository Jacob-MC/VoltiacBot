package xyz.voltiac.bot.Moderation;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.Role;
import discord4j.core.object.entity.channel.MessageChannel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class LockdownCommand {
    public static void LockdownCommand(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    Message Command = event.getMessage();
                    if ("!lockdown".equalsIgnoreCase(Command.getContent())) {
                        String username = Command.getAuthor().get().getUsername();
                        String avatar = Command.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = Command.getChannel().block();
                        Guild guild = (Guild) event.getGuild().block();
                        System.out.println("Lockdown Start Command Executed By: " + username);
                        while (1 < 9999) {
                        List<Role> roles = guild.getRoles().collectSortedList().block();
                        System.out.println(roles);
                        }
                    }
                    if ("!lockdown end".equalsIgnoreCase(Command.getContent())) {
                        String username = Command.getAuthor().get().getUsername();
                        String avatar = Command.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = Command.getChannel().block();

                        System.out.println("Lockdown End Command Executed By: " + username);
                    }
                });
    }
}
