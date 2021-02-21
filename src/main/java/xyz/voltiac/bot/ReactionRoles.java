package xyz.voltiac.bot;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.event.domain.message.ReactionRemoveEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.User;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

public class ReactionRoles {
    void ReactionRoles(GatewayDiscordClient client) {
        client.getEventDispatcher().on(ReactionAddEvent.class)
                .subscribe(event -> {
                    final Snowflake id = event.getMessageId();
                    final Optional<Member> m = event.getMember();
                    final long messageid = 809164971148705843L;
                    if (id.asLong() == messageid){
                        final Snowflake r = event.getUserId();
                        if (r.asLong() == r.asLong()) {
                            m.get().addRole(Snowflake.of("808838744203198504")).block();
                        }
                    }
                });
        client.getEventDispatcher().on(ReactionRemoveEvent.class)
                .subscribe(event -> {
                    final Snowflake id = event.getMessageId();
                    final User u = event.getUser().block();
                    assert u != null;
                    final Snowflake m = u.getId();
                    final Mono<Member> g = Objects.requireNonNull(event.getGuild().block()).getMemberById(m);
                    final long messageid = 809164971148705843L;
                    if (id.asLong() == messageid){
                        final Snowflake r = event.getUserId();
                        if (m.asLong() == m.asLong()) {
                            Objects.requireNonNull(g.block()).removeRole(Snowflake.of("808838744203198504")).block();
                        }
                    }
                });
    }
}
