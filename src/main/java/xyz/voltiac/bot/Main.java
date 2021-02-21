package xyz.voltiac.bot;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.guild.MemberJoinEvent;
import discord4j.core.event.domain.guild.MemberLeaveEvent;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.event.domain.message.ReactionRemoveEvent;
import discord4j.core.object.PermissionOverwrite;
import discord4j.core.object.entity.*;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.rest.util.Color;
import discord4j.rest.util.PermissionSet;
import reactor.core.publisher.Mono;
import sun.awt.image.ImageWatched;

import java.time.Instant;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import static discord4j.rest.util.Permission.*;

public class Main {
    static GatewayDiscordClient client = DiscordClientBuilder.create("ODA5NDg3MDUxNTY0OTA4NTc2.YCVzkA.f5N1i9oYIS4dojERd_ZzKc-AVqw")
            .build()
            .login()
            .block();
    public static void main(String[] args) {
        Commands commands = new Commands();
        commands.CommandListeners(client);
        ModerationCommands moderationCommands = new ModerationCommands();
        moderationCommands.ModCommandListeners(client);
        Tickets tickets = new Tickets();
        tickets.TicketListeners(client);
        LinkBlacklist linkBlacklist = new LinkBlacklist();
        linkBlacklist.LinkBlacklist(client);
        MessageLogs messageLogs = new MessageLogs();
        messageLogs.MessageLogs(client);
        ReactionRoles reactionRoles = new ReactionRoles();
        reactionRoles.ReactionRoles(client);
        WelcomeMessages welcomeMessages = new WelcomeMessages();
        welcomeMessages.WelcomeMessages(client);
        assert client != null;
        client.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(event -> {
                    final User self = event.getSelf();
                    System.out.printf(
                            "Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator()
                   );
                    final Message message = client.getMessageById(Snowflake.of(808838744609652784L), Snowflake.of(809278160704897034L)).block();
                    assert message != null;
                    message.addReaction(ReactionEmoji.unicode("\u2705")).block();
                });
        client.onDisconnect().block();
    }
}
