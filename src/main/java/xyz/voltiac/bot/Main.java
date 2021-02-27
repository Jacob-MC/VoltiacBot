package xyz.voltiac.bot;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import discord4j.gateway.intent.Intent;
import discord4j.gateway.intent.IntentSet;

public class Main {
    static GatewayDiscordClient client = DiscordClientBuilder.create("token here")
            .build()
            .login()
            .block();

    public static void main(String[] args) {
        Main main = new Main();
        Classes.Classes(main.client);
        main.client.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(event -> {
                    User self = event.getSelf();
                    System.out.printf(
                            "Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator()
                   );
                });
        main.client.onDisconnect().block();
    }
}