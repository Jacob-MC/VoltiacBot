package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import xyz.voltiac.bot.Main;

public class MiscCommands {
    public static void misccommands(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                        Member member = event.getMember().get();
                        Message message = event.getMessage();
                        if (message.getContent().equalsIgnoreCase(Main.prefix + "help misc") && !member.isBot()) {
                            String username = message.getAuthor().get().getUsername();
                            String avatar = message.getAuthor().get().getAvatarUrl();
                            MessageChannel channel = message.getChannel().block();
                            assert channel != null;
                            channel.createEmbed(embedCreateSpec -> {
                                embedCreateSpec.setTitle("**Miscellaneous Commands**")
                                        .addField("**" + Main.prefix + "avatar (user)**", "Gets a user's avatar - Works by mentioning the user or by using the user's ID", true)
                                        .addField("**" + Main.prefix + "serverinfo**", "Provides info on the server the command is executed from", true)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Command Executed By: " + username, avatar);
                            }).block();
                            System.out.println("Misc commands Command Executed By: " + username);
                        }
                    } catch (Exception e) {
                        System.out.println("ERROR:");
                        e.printStackTrace();
                    }
                });
    }
}