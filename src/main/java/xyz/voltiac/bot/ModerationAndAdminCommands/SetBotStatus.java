package xyz.voltiac.bot.ModerationAndAdminCommands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.*;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.Presence;
import discord4j.rest.util.Color;
import discord4j.rest.util.Permission;
import reactor.core.publisher.Mono;
import xyz.voltiac.bot.Main;

import java.time.Duration;
import java.util.Optional;

public class SetBotStatus {
    public static void SetBotStatus(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                   Message message = event.getMessage();
                   Member member = message.getAuthorAsMember().block();
                   String messagecontent = message.getContent();
                   User user = message.getAuthor().get();
                    assert user != null;
                   String username = user.getUsername();
                   String avatar = user.getAvatarUrl();
                   MessageChannel channel = message.getChannel().block();
                   Guild guild = event.getGuild().block();
                   assert channel != null;
                    if (messagecontent.equalsIgnoreCase(Main.prefix + "setbotstatus") && member.getId().asString().equals("778742764908183612")) {
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**" + Main.prefix + "setbotstatus**")
                                    .setDescription("Sets the bots status!")
                                    .addField("**Usage:**",  Main.prefix + "setbotstatus (status)", false)
                                    .addField("Example:",  Main.prefix + "setbotstatus Moderating Voltiac Network", false)
                                    .setFooter("Command Executed By: " + username, avatar)
                                    .setColor(Color.of(51, 153, 255));
                        }).block();
                        message.delete().block();
                    } else if(messagecontent.toLowerCase().startsWith(Main.prefix + "setbotstatus ") && member.getId().asString().equals("778742764908183612")) {
                        int index = messagecontent.indexOf(" ") + 1;
                        String botstatus = messagecontent.substring(index);
                        client.updatePresence(Presence.online(Activity.playing(botstatus))).block();
                        channel.createMessage("Bot status updated.").block();
                           System.out.println("Bot status set to: " + botstatus + "\nby " + username);
                        message.delete().block();
                   }
                    } catch (Exception e) {
                    }
                });
        }
    }
