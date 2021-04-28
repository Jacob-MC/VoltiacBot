package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import xyz.voltiac.bot.Main;

public class Help {
    public static void Help(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                        Member member = event.getMember().get();
                        Message message = event.getMessage();
                    if (message.getContent().equalsIgnoreCase(Main.prefix + "help") || message.getContent().equals("<@!809487051564908576>") && !member.isBot()) {
                        String username = message.getAuthor().get().getUsername();
                        String avatar = message.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = message.getChannel().block();
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**Help**")
                                    .setDescription("Hello, " + username + ", my prefix is `" + Main.prefix + "`! You can view a list of commands by typing " + Main.prefix + "commands")
                                    .setColor(Color.of(51, 153, 255))
                                    .setFooter("Command Executed By: " + username, avatar);
                        }).block();
                        System.out.println("Help Command Executed By: " + username);
                    }
                    } catch (Exception e) {
                    }
                });
    }
}
