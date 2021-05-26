package xyz.voltiac.bot.FunAndUtil;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import xyz.voltiac.bot.Main;

import java.util.ArrayList;
import java.util.Collections;

public class RollCommand {
    public static void RollCommand(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                        Member member = event.getMember().get();
                   Message message = event.getMessage();
                   String messagecontent = message.getContent();
                   MessageChannel channel = message.getChannel().block();
                   User author = message.getAuthorAsMember().block();
                   String username = author.getUsername();
                    assert username != null;
                   String useravatar = author.getAvatarUrl();
                   if (messagecontent.equalsIgnoreCase(Main.prefix + "roll") || messagecontent.equalsIgnoreCase(Main.prefix + "roll ") && !member.isBot()) {
                       channel.createEmbed(embedCreateSpec -> {
                           embedCreateSpec.setTitle("**" + Main.prefix + "roll**")
                                   .setDescription("Roll a specified number of dice!")
                                   .addField("**Usage:**",  Main.prefix + "roll (number of dice)", false)
                                   .addField("Example:",  Main.prefix + "roll 5", false)
                                   .setFooter("Command Executed By: " + username, useravatar)
                                   .setColor(Color.of(51, 153, 255));
                       }).block();
                       System.out.println("Roll Command Executed By: " + username);
                   }
                   if (messagecontent.toLowerCase().startsWith(Main.prefix + "roll ") && !member.isBot()) {
                       System.out.println("Roll Command Executed By: " + username);
                       int index = messagecontent.indexOf(" ") + 1;
                       String rollnumberdice = messagecontent.substring(index);
                       if (!rollnumberdice.matches("^[0-9]*$") ) {
                           channel.createMessage("Invalid number. Please enter a number between 1 and 5.").block();
                       } else {
                               int numberdice = Integer.valueOf(rollnumberdice);
                           ArrayList<Integer> list = new ArrayList<Integer>();
                           for (int i = 1; i < 11; i++) {
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
                           if (numberdice == 2) {
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
                           if (numberdice > 5 || numberdice < 1) {
                               channel.createMessage("Invalid number. Please enter a number between 1 and 5.").block();
                           }
                       }
                   }
                    } catch (Exception e) {
                        System.out.println("ERROR:");
                        e.printStackTrace();
                    }
                });
    }
}
