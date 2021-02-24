package xyz.voltiac.bot.OtherUtil;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

import java.util.ArrayList;
import java.util.Collections;

public class RPSCommand {
    public static void RPSCommand(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                   Message message = event.getMessage();
                   String messagecontent = message.getContent();
                   MessageChannel channel = message.getChannel().block();
                   User user = message.getAuthor().get();
                   String username = user.getUsername();
                   String avatar = user.getAvatarUrl();
                    if (messagecontent.equalsIgnoreCase("!rps") || messagecontent.equalsIgnoreCase("!rps ")) {
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**!rps**")
                                    .setDescription("Play Rock, Paper, Scissors with the bot!")
                                    .addField("**Usage:**", "!rps (choice)", false)
                                    .addField("**Example:**", "!rps paper", false)
                                    .setFooter("Command Executed By: " + username, avatar)
                                    .setColor(Color.of(51, 153, 255));
                            System.out.println("RPS Command Executed By: " + username);
                        }).block();
                    } else if (messagecontent.equalsIgnoreCase("!rps rock") || messagecontent.equalsIgnoreCase("!rps paper") || messagecontent.equalsIgnoreCase("!rps scissors")) {
                        System.out.println("RPS Command Executed By: " + username);
                        int index = messagecontent.indexOf(' ') + 1;
                        String choice = messagecontent.substring(index);
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        for (int i = 1; i < 4; i++) {
                            list.add(new Integer(i));
                        }
                        Collections.shuffle(list);
                        int rand = list.get(0);
                        if (rand == 1) {
                            if (choice.equalsIgnoreCase("Rock")) {
                                channel.createMessage("You chose ***Rock***. I choose ***Rock***.\n" + "It's a tie! Please choose another.").block();
                            }
                            if (choice.equalsIgnoreCase("Paper")) {
                                channel.createMessage("You chose ***Paper***. I choose ***Rock***.\n" + "Paper wins!").block();
                            }
                            if (choice.equalsIgnoreCase("Scissors")) {
                                channel.createMessage("You chose ***Scissors***. I choose ***Rock***.\n" + "Rock wins!").block();
                            }
                        }
                        if (rand == 2) {
                            if (choice.equalsIgnoreCase("Rock")) {
                                channel.createMessage("You chose ***Rock***. I choose ***Paper***.\n" + "Paper wins!").block();
                            }
                            if (choice.equalsIgnoreCase("Paper")) {
                                channel.createMessage("You chose ***Paper***. I choose ***Paper***.\n" + "It's a tie! Please choose another.").block();
                            }
                            if (choice.equalsIgnoreCase("Scissors")) {
                                channel.createMessage("You chose ***Scissors***. I choose ***Paper***.\n" + "Scissors win!").block();
                            }
                        }
                        if (rand == 3) {
                            String choice1 = "Scissors";
                            if (choice.equalsIgnoreCase("Rock")) {
                                channel.createMessage("You chose ***Rock***. I choose ***Scissors***.\n" + "Rock wins!").block();
                            }
                            if (choice.equalsIgnoreCase("Paper")) {
                                channel.createMessage("You chose ***Paper***. I choose ***Scissors***.\n" + "Scissors win!").block();
                            }
                            if (choice.equalsIgnoreCase("Scissors")) {
                                channel.createMessage("You chose ***Scissors***. I choose ***Scissors***.\n" + "It's a tie! Please choose another.").block();
                            }
                        }
                    }
                });
    }
}
