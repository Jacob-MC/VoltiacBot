package xyz.voltiac.bot.OtherUtil;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

public class RollCommand {
    public static  void RollCommand(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                   Message message = event.getMessage();
                   String messagecontent = message.getContent();
                   MessageChannel channel = message.getChannel().block();
                   Optional<User> author = message.getAuthor();
                   String username = author.get().getUsername();
                   String useravatar = author.get().getAvatarUrl();
                   if (messagecontent.equalsIgnoreCase("!roll") || messagecontent.equalsIgnoreCase("roll ")) {
                       channel.createEmbed(embedCreateSpec -> {
                           embedCreateSpec.setTitle("**!roll**")
                                   .setDescription("Roll a specified number of dice!")
                                   .addField("**Usage:**", "!roll (number of dice)", false)
                                   .addField("Example:", "!roll 5", false)
                                   .setFooter("Command Executed By: " + username, useravatar)
                                   .setColor(Color.of(51, 153, 255));
                       }).block();
                       System.out.println("Roll Command Executed By: " + username);
                   }
                   if (messagecontent.toLowerCase().contains("!roll ")) {
                       System.out.println("Roll Command Executed By: " + username);
                       String rollnumberdice = messagecontent.substring(6);
                       if (rollnumberdice.matches(".*[a-z].*")) {
                           channel.createMessage("Invalid number. Please enter a number between 1 and 5.").block();
                       } else {
                           int numberdice = Integer.valueOf(rollnumberdice);

                           ArrayList<Integer> list = new ArrayList<Integer>();
                           for (int i = 1; i < 7; i++) {
                               list.add(new Integer(i));
                           }
                           Collections.shuffle(list);
                           int rand1 = list.get(0);
                           int rand2 = list.get(1);
                           int rand3 = list.get(2);
                           int rand4 = list.get(3);
                           int rand5 = list.get(4);
                           if (numberdice == 1) {
                               channel.createMessage(username + ", you rolled " + rand1).block();
                           }

                           if (numberdice == 2 && Integer.valueOf(rand1) != Integer.valueOf(rand2)) {
                               channel.createMessage(username + ", you rolled " + rand1 + ", " + rand2).block();
                           }

                           if (numberdice == 3) {
                               channel.createMessage(username + ", you rolled " + rand1 + ", " + rand2 + ", " + rand3).block();
                           }

                           if (numberdice == 4) {
                               channel.createMessage(username + ", you rolled " + rand1 + ", " + rand2 + ", " + rand3 + ", " + rand4).block();
                           }

                           if (numberdice == 5) {
                               channel.createMessage(username + ", you rolled " + rand1 + ", " + rand2 + ", " + rand3 + ", " + rand4 + ", " + rand5).block();
                           }
                       }
                   }
                });
    }
}
