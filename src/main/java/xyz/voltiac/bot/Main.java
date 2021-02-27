package xyz.voltiac.bot;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;

public class Main {
    static GatewayDiscordClient client = DiscordClientBuilder.create("token here")
            .build()
            .login()
            .block();
    public static void main(String[] args) {
        Main main = new Main();
        assert main.client != null;
        Classes classes = new Classes();
        classes.Classes(main.client);
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