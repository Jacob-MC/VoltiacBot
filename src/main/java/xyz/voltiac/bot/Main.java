package xyz.voltiac.bot;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.Presence;
import xyz.voltiac.bot.Databases.SQLite;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;

public class Main {
    public static String prefix = "vb.";
    public static void main(String[] args) {
        String token = null;
        try {
            System.out.println("Fetching token.");
            InputStream in = Main.class.getResourceAsStream("/token.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            token = reader.readLine();
            System.out.println("Fetching token successful.");
            reader.close();
        } catch (Exception var15) {
            System.out.println("Fetching token failed. File may be missing, named incorrectly, or in the wrong directory. Please place the file in the root of the jar.");
        }
        GatewayDiscordClient client = DiscordClientBuilder.create(token)
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
