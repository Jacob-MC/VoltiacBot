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
                    Snowflake id = event.getMessageId();
                    Optional<Member> m = event.getMember();
                    long messageid = 809164971148705843L;
                    if (id.asLong() == messageid){
                        Snowflake r = event.getUserId();
                        if (r.asLong() == r.asLong()) {
                            m.get().addRole(Snowflake.of("808838744203198504")).block();
                        }
                    }
                });
        client.getEventDispatcher().on(ReactionRemoveEvent.class)
                .subscribe(event -> {
                    Snowflake id = event.getMessageId();
                    User u = event.getUser().block();
                    assert u != null;
                    Snowflake m = u.getId();
                    Mono<Member> g = Objects.requireNonNull(event.getGuild().block()).getMemberById(m);
                    long messageid = 809164971148705843L;
                    if (id.asLong() == messageid){
                        Snowflake r = event.getUserId();
                        if (m.asLong() == m.asLong()) {
                            Objects.requireNonNull(g.block()).removeRole(Snowflake.of("808838744203198504")).block();
                        }
                    }
                });
    }
}
