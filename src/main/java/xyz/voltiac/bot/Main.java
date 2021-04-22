package xyz.voltiac.bot;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.Presence;
import discord4j.rest.util.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

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
        client.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(event -> {
                    User self = event.getSelf();
                    System.out.printf(
                            "Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator()
                   );
                    client.updatePresence(Presence.online(Activity.playing("vb.help"))).block();
                });
        client.onDisconnect().block();

        // EDIT BOT PREFIX COMMAND

        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                   Message message = event.getMessage();
                   Member member = message.getAuthorAsMember().block();
                   String messagecontent = message.getContent();
                   String userid = member.getId().toString();
                   String username = member.getUsername();
                   String avatar = member.getAvatarUrl();
                    MessageChannel channel = message.getChannel().block();
                    if (messagecontent.equalsIgnoreCase(Main.prefix + "setbotprefix") && userid.equals("778742764908183612")) {
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**" + Main.prefix + "setbotstatus**")
                                    .setDescription("Sets the bots status!")
                                    .addField("**Usage:**",  Main.prefix + "setbotstatus (status)", false)
                                    .addField("Example:",  Main.prefix + "setbotstatus Moderating Voltiac Network", false)
                                    .setFooter("Command Executed By: " + username, avatar)
                                    .setColor(Color.of(51, 153, 255));
                        }).block();
                        message.delete().block();
                    } else if(messagecontent.toLowerCase().startsWith(Main.prefix + "setbotprefix ") && userid.equals("778742764908183612")) {
                        int index = messagecontent.indexOf(" ");
                        int endIndex = messagecontent.lastIndexOf(" ");
                        String botprefix = messagecontent.substring(index, endIndex);
                        prefix = botprefix;
                        client.updatePresence(Presence.online(Activity.playing(botprefix))).block();
                        channel.createMessage("Bot prefix updated.").block();
                        System.out.println("Bot prefix set to: " + botprefix);

                        message.delete().block();
                    }
                });

    }
}
