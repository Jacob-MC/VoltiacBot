package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import xyz.voltiac.bot.Main;

public class FunCommands {
    public static void FunCommands(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                        Member member = event.getMember().get();
                        Message message = event.getMessage();
                    if (message.getContent().equalsIgnoreCase(Main.prefix + "help fun") && !member.isBot()) {
                        String username = message.getAuthor().get().getUsername();
                        String avatar = message.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = message.getChannel().block();
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**Fun Commands**")
                                    .addField("**" + Main.prefix + "roll (number of dice)**", "Rolls a specified amount of dice (Maximum 5)", true)
                                    .addField("**" + Main.prefix + "rps (choice)**", "Plays rock, paper, scissors with the bot", true)
                                    .addField("**" + Main.prefix + "say**", "Repeats what you say", true)
                                    .addField("**" + Main.prefix + "subreddits**", "Displays the list of subreddits you can fetch posts from", true)
                                    .setColor(Color.of(51, 153, 255))
                                    .setFooter("Command Executed By: " + username, avatar);
                        }).block();
                        System.out.println("Fun commands Command Executed By: " + username);
                    }
                    } catch (Exception e) {
                        System.out.println("ERROR:");
                        e.printStackTrace();
                    }
                });
    }
}
