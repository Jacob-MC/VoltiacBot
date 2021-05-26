package xyz.voltiac.bot;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.Presence;
import xyz.voltiac.bot.Databases.SQLiteDataSource;

import java.sql.SQLException;

public class Main {
    public static String prefix = "vb.";
    public static void main(String[] args) throws SQLException {
        SQLiteDataSource.getConnection();
        GatewayDiscordClient client = DiscordClientBuilder.create(System.getenv("TOKEN"))
                .build()
                .login()
                .block();

        Classes.Classes(client);
        int numberguilds = client.getGuilds().collectList().block().size();
        client.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(event -> {
                        User self = event.getSelf();
                        System.out.printf(
                                "Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator()
                        );
                        client.updatePresence(Presence.online(Activity.playing(prefix + "help | In " + numberguilds + " Guilds"))).block();
                });
        client.onDisconnect().block();
    }
}
