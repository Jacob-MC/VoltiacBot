package xyz.voltiac.bot.OtherUtil;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.object.PermissionOverwrite;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.Role;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.rest.util.Color;
import discord4j.rest.util.PermissionSet;

import java.time.Instant;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static discord4j.rest.util.Permission.*;

public class Tickets {
    public static void TicketListeners(GatewayDiscordClient client) {
        client.getEventDispatcher().on(ReactionAddEvent.class)
                .subscribe(event -> {
                    Snowflake id = event.getMessageId();
                    ReactionEmoji reactionemoji = event.getEmoji();
                    Message message = event.getMessage().block();
                    Member m = event.getMember().get();
                    Snowflake messagechannelid = message.getChannelId();
                    String messagecontent = message.getContent();
                    String user = m.getUsername();
                    String mention = m.getMention();
                    String name = m.getUsername();
                    String disc = m.getDiscriminator();
                    Guild guild = event.getGuild().block();
                    Instant instant = Instant.now();

                    Snowflake staffroleid = null;
                    String roles = guild.getRoles().collectList().block().toString();
                    if (roles.toLowerCase().contains("staff")) {
                        int beginindex = roles.toLowerCase().indexOf("staff") - 25;
                        int endindex = beginindex + 18;
                        staffroleid = Snowflake.of(roles.substring(beginindex, endindex));
                    } else {
                    }
                    Role staffrole = guild.getRoleById(staffroleid).block();
                    String staffrolemention = staffrole.getMention();
                    Snowflake userid = m.getId();
                    Role everyonerole = guild.getEveryoneRole().block();
                    Snowflake everyoneroleid = everyonerole.getId();
                    assert message != null;

                    Snowflake supportchannelID = null;
                    String channels = guild.getChannels().collectList().block().toString();
                    if (channels.toLowerCase().contains("support")) { ;
                        int beginindex = channels.indexOf("support");
                        String channelinfo = channels.substring(beginindex);
                        int channelidbeginindex = channelinfo.indexOf("BaseChannel{data=ChannelData{id=") + 32;
                        int channelidendindex = channelidbeginindex + 18;
                        supportchannelID = Snowflake.of(channelinfo.substring(channelidbeginindex, channelidendindex));
                    }

                    if (messagecontent.equalsIgnoreCase("tickets") && messagechannelid.equals(supportchannelID)){
                        Snowflake r = event.getUserId();
                        if (r.asLong() == r.asLong()) {
                            Role role1 = m.getHighestRole().block();
                            assert role1 != null;
                            Void removereaction = message.removeReaction(reactionemoji, userid).block();

                            Random rand = new Random();
                            int upperbound = 9;
                            String rand1 = String.valueOf(rand.nextInt(upperbound));
                            String rand2 = String.valueOf(rand.nextInt(upperbound));
                            String rand3 = String.valueOf(rand.nextInt(upperbound));
                            String rand4 = String.valueOf(rand.nextInt(upperbound));
                            String rand5 = String.valueOf(rand.nextInt(upperbound));
                            String rand6 = String.valueOf(rand.nextInt(upperbound));
                            Set<PermissionOverwrite> overwrites = new HashSet<>();
                            overwrites.add(PermissionOverwrite.forRole(everyoneroleid, PermissionSet.of(), PermissionSet.of(VIEW_CHANNEL)));
                            overwrites.add(PermissionOverwrite.forRole(staffroleid, PermissionSet.of(VIEW_CHANNEL, SEND_MESSAGES, MENTION_EVERYONE), PermissionSet.of(MANAGE_MESSAGES)));
                            overwrites.add(PermissionOverwrite.forMember(userid, PermissionSet.of(VIEW_CHANNEL, SEND_MESSAGES), PermissionSet.of(MENTION_EVERYONE, MANAGE_MESSAGES)));
                            String randomid = rand1 + rand2 + rand3 + rand4 + rand5 + rand6;
                            TextChannel create = guild.createTextChannel(TextChannelCreateSpec -> {
                                TextChannelCreateSpec.setName("ticket-" + name + "-" + randomid)
                                        .setPermissionOverwrites(overwrites);
                                    }).block();
                            System.out.println("Ticket Created By: " + user);
                            long ticketchannelid = create.getId().asLong();
                            MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(ticketchannelid)).block();
                            assert channel != null;
                            channel.createMessage(messageCreateSpec -> messageCreateSpec.setContent("Please be patient, " + mention + " , " + staffrolemention + " will be with you soon.")).block();
                            Snowflake logchannelID = null;
                            if (channels.toLowerCase().contains("logs")) { ;
                                int beginindex = channels.indexOf("logs");
                                String channelinfo = channels.substring(beginindex);
                                int channelidbeginindex = channelinfo.indexOf("BaseChannel{data=ChannelData{id=") + 32;
                                int channelidendindex = channelidbeginindex + 18;
                                logchannelID = Snowflake.of(channelinfo.substring(channelidbeginindex, channelidendindex));
                            } else {
                            }
                            MessageChannel channel2 = (MessageChannel) client.getChannelById(logchannelID).block();
                            assert channel2 != null;
                            channel2.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**Ticket Opened**")
                                    .setDescription("Ticket #" + randomid + " opened by " + mention)
                                    .setColor(Color.of(51, 153, 255))
                                    .setTimestamp(instant)).block();
                        }
                    }
                });
    }
}
