package xyz.voltiac.bot;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.Presence;

public class Main {
    static GatewayDiscordClient client = DiscordClientBuilder.create("ODA5NDg3MDUxNTY0OTA4NTc2.YCVzkA.kLlYlrc88Ywl6PtFG_t09rhN9UU")
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
                    client.updatePresence(Presence.online(Activity.playing("Moderating Voltiac Network"))).block();
                });
        main.client.onDisconnect().block();
    }
}
