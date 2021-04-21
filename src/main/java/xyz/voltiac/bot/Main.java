package xyz.voltiac.bot;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.Presence;
import org.json.JSONObject;

import java.io.FileReader;

public class Main {
    static GatewayDiscordClient client = DiscordClientBuilder.create(System.getenv("token"))
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
                    client.updatePresence(Presence.online(Activity.playing("!help"))).block();
                });
        main.client.onDisconnect().block();
    }
}
