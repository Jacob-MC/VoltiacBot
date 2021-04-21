package xyz.voltiac.bot.OtherUtil;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.event.domain.message.ReactionRemoveEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

public class ReactionRoles {
    public static void ReactionRoles(GatewayDiscordClient client) {
        client.getEventDispatcher().on(ReactionAddEvent.class)
                .subscribe(event -> {
                    try {
                    Snowflake id = event.getMessageId();
                    Optional<Member> m = event.getMember();
                    Message message = event.getMessage().block();
                    String messagecontent = message.getContent();
                    if (messagecontent.equalsIgnoreCase("React to this message to recieve the《 Bedrock 》role!")){
                        Snowflake r = event.getUserId();
                        if (r.asLong() == r.asLong()) {
                            m.get().addRole(Snowflake.of("808838744203198504")).block();
                        }
                    }
                    } catch (Exception e) {
                    }
                });

        client.getEventDispatcher().on(ReactionRemoveEvent.class)
                .subscribe(event -> {
                    try {
                    Message message = event.getMessage().block();
                    String messagecontent = message.getContent();
                    Snowflake id = event.getMessageId();
                    User u = event.getUser().block();
                    assert u != null;
                    Snowflake m = u.getId();
                    Mono<Member> g = Objects.requireNonNull(event.getGuild().block()).getMemberById(m);
                    long messageid = 809164971148705843L;
                    if (messagecontent.equalsIgnoreCase("React to this message to recieve the《 Bedrock 》role!")){
                        Snowflake r = event.getUserId();
                        if (m.asLong() == m.asLong()) {
                            Objects.requireNonNull(g.block()).removeRole(Snowflake.of("808838744203198504")).block();
                        }
                    }
                    } catch (Exception e) {
                    }
                });
    }
}
