package xyz.voltiac.bot;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.Presence;
import discord4j.rest.util.Color;
import xyz.voltiac.bot.Databases.SQLite;
import xyz.voltiac.bot.Databases.SelectApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static String prefix = "vb.";

    //jdbc:sqlite:database.sqlite

    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:database.sqlite";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS servers (\n"
                + "	serverid long NOT NULL,\n"
                + "	name text NOT NULL,\n"
                + "	prefix text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createNewTable();
        SelectApp app = new SelectApp();
        app.selectAll();
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
