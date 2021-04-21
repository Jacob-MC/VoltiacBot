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

import java.time.Duration;
import java.util.Optional;

public class SetBotStatus {
    public static void SetBotStatus(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
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
                    if (messagecontent.equalsIgnoreCase("!setbotstatus") && member.getBasePermissions().block().contains(Permission.ADMINISTRATOR)) {
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**!setbotstatus**")
                                    .setDescription("Sets the bots status!")
                                    .addField("**Usage:**", "!setbotstatus (status)", false)
                                    .addField("Example:", "!setbotstatus Moderating Voltiac Network", false)
                                    .setFooter("Command Executed By: " + username, avatar)
                                    .setColor(Color.of(51, 153, 255));
                        }).block();
                        message.delete().block();
                    } else if(messagecontent.startsWith("!setbotstatus") && !member.getBasePermissions().block().contains(Permission.ADMINISTRATOR)) {
                        channel.createMessage("You do not have permissions to use this command!").block();
                        message.delete().block();
                    } else if(messagecontent.toLowerCase().startsWith("!setbotstatus ") && member.getBasePermissions().block().contains(Permission.ADMINISTRATOR)) {
                        int index = messagecontent.indexOf(" ");
                        String botstatus = messagecontent.substring(index);
                        client.updatePresence(Presence.online(Activity.playing(botstatus))).block();
                        channel.createMessage("Bot status updated.").block();
                           System.out.println("Bot status set to: " + botstatus + "\nby " + username);
                        message.delete().block();
                   }
                });
        }
    }
