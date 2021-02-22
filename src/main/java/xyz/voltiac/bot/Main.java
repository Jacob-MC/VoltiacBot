package xyz.voltiac.bot;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.reaction.ReactionEmoji;

public class Main {
    static GatewayDiscordClient client = DiscordClientBuilder.create("token here")
            .build()
            .login()
            .block();
    public static void main(String[] args) {
        Classes classes = new Classes();
        classes.Classes(client);
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
