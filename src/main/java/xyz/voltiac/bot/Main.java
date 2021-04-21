package xyz.voltiac.bot;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.Presence;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList list = null;
        try {
            System.out.println("Fetching token.");
            Scanner scanner = new Scanner(new FileReader(System.getProperty("user.dir") + "\\token.txt"));
            list = new ArrayList();

            while(scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }

            System.out.println("Fetching token successful.");

        } catch (Exception var15) {
            System.out.println("Fetching token failed. File may be missing, named incorrectly, or in the wrong directory. Please place the file in the root of the jar.");
        }

        GatewayDiscordClient client = DiscordClientBuilder.create(list.toString().substring(1, 60))
                .build()
                .login()
                .block();
        Classes.Classes(client);
        client.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(event -> {
                    User self = event.getSelf();
                    System.out.printf(
                            "Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator()
                   );
                    client.updatePresence(Presence.online(Activity.playing("!help"))).block();
                });
        client.onDisconnect().block();
    }
}
