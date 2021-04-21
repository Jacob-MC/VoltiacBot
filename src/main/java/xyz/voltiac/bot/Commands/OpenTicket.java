package xyz.voltiac.bot.Commands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.PermissionOverwrite;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.Role;
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
import static discord4j.rest.util.Permission.MANAGE_MESSAGES;

public class OpenTicket {
    public static void OpenTicket(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                    Message message = event.getMessage();
                    Member m = message.getAuthorAsMember().block();
                    String user = m.getUsername();
                    assert user != null;
                    String messagecontent = message.getContent();
                    String mention = m.getMention();
                    String name = m.getUsername();
                    Guild guild = m.getGuild().block();
                    Snowflake userid = m.getId();
                    Role everyonerole = guild.getEveryoneRole().block();
                    Snowflake everyoneroleid = everyonerole.getId();
                    MessageChannel channel = (MessageChannel) message.getChannel().block();

                    if (messagecontent.equalsIgnoreCase("!ticketasdfadsfdasfasdfasdfasdfsafasf")){
                    try {
                        Snowflake staffrole = null;
                        String roles = guild.getRoles().collectList().block().toString();
                        if (roles.toLowerCase().contains("staff")) {
                            int beginindex = roles.toLowerCase().indexOf("staff") - 25;
                            int endindex = beginindex + 18;
                            staffrole = Snowflake.of(roles.substring(beginindex, endindex));
                        } else {
                        }
                        Role staffrole1 = guild.getRoleById(staffrole).block();
                        String staffrolemention = staffrole1.getMention();
                        Instant instant = Instant.now();
                        assert message != null;
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
                        overwrites.add(PermissionOverwrite.forRole(staffrole, PermissionSet.of(VIEW_CHANNEL, SEND_MESSAGES, MENTION_EVERYONE), PermissionSet.of(MANAGE_MESSAGES)));
                        overwrites.add(PermissionOverwrite.forMember(userid, PermissionSet.of(VIEW_CHANNEL, SEND_MESSAGES), PermissionSet.of(MENTION_EVERYONE, MANAGE_MESSAGES)));
                        String randomid = rand1 + rand2 + rand3 + rand4 + rand5 + rand6;
                        TextChannel create = guild.createTextChannel(TextChannelCreateSpec -> {
                            TextChannelCreateSpec.setName("ticket-" + name + "-" + randomid)
                                    .setPermissionOverwrites(overwrites);
                        }).block();
                        System.out.println("Ticket Created By: " + user);
                        long ticketchannelid = create.getId().asLong();
                        channel.createMessage(messageCreateSpec -> messageCreateSpec.setContent("Please be patient, " + mention + " , " + staffrolemention + " will be with you soon.")).block();
                        String channels = guild.getChannels().collectList().block().toString();
                        Snowflake logchannelID = null;
                        if (channels.toLowerCase().contains("logs")) { ;
                            int beginindex = channels.indexOf("logs");
                            String channelinfo = channels.substring(beginindex);
                            int channelidbeginindex = channelinfo.indexOf("BaseChannel{data=ChannelData{id=") + 32;
                            int channelidendindex = channelidbeginindex + 18;
                            logchannelID = Snowflake.of(channelinfo.substring(channelidbeginindex, channelidendindex));
                        }
                        MessageChannel channel2 = (MessageChannel) client.getChannelById(logchannelID).block();
                        assert channel2 != null;
                        channel2.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**Ticket Opened**")
                                .setDescription("Ticket #" + randomid + " opened by " + mention)
                                .setColor(Color.of(51, 153, 255))
                                .setTimestamp(instant)).block();
                        message.delete("Ticket Created").block();
                    } catch(Exception e) {
                        channel.createMessage("An error occured. Please check if there is a staff role, a logging channel, and that the bot has the correct permissions in order for the ticket system to work.").block();
                    }
                        }
                    } catch (Exception e) {
                    }
                    });
    }
}
