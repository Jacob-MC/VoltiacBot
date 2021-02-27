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
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Optional;

public class SetBotStatus {
    public static void SetBotStatus(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                   Message message = event.getMessage();
                   String messagecontent = message.getContent();
                   User user = message.getAuthor().get();
                   String username = user.getUsername();
                   String avatar = user.getAvatarUrl();
                   MessageChannel channel = message.getChannel().block();
                    if (messagecontent.equalsIgnoreCase("!setbotstatus") || messagecontent.equalsIgnoreCase("!setbotstatus ")) {
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**!setbotstatus**")
                                    .setDescription("Sets the bots status!")
                                    .addField("**Usage:**", "!setbotstatus (status)", false)
                                    .addField("Example:", "!setbotstatus Moderating Voltiac Network", false)
                                    .setFooter("Command Executed By: " + username, avatar)
                                    .setColor(Color.of(51, 153, 255));
                        }).block();
                    } else if(messagecontent.toLowerCase().contains("!setbotstatus")) {
                        Member member = message.getAuthorAsMember().block();
                        Role highestrole = member.getHighestRole().block();
                       int rolepos = highestrole.getPosition().block();
                       System.out.println(rolepos);
                       int index = messagecontent.indexOf(" ") + 1;
                       String status = messagecontent.substring(index);
                       if (rolepos >= 33) {
                        client.updatePresence(Presence.online(Activity.playing(status))).block();
                        channel.createMessage("Bot status updated.").block();
                       } else if (rolepos < 33) {
                           channel.createMessage("You do not have permission to use that command!").block();
                       }
                   }
                });
        }
    }
