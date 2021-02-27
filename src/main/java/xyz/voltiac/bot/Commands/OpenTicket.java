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
                    Message message = event.getMessage();
                    Member m = event.getMember().get();
                    String user = m.getUsername();
                    String messagecontent = message.getContent();
                    Member member = client.getMemberById(Snowflake.of(808838744203198503L), Snowflake.of(809487051564908576L)).block();
                    assert member != null;
                    Guild guild1 = client.getGuildById(Snowflake.of(808838744203198503L)).block();
                    assert guild1 != null;
                    Role staffrole = guild1.getRoleById(Snowflake.of(808838744227446789L)).block();
                    assert staffrole != null;
                    String staffrolemention = staffrole.getMention();
                    String mention = m.getMention();
                    String name = m.getUsername();
                    Guild guild = m.getGuild().block();
                    Snowflake userid = m.getId();
                    Role everyonerole = guild.getEveryoneRole().block();
                    Snowflake everyoneroleid = everyonerole.getId();
                    Snowflake staffrole1 = Snowflake.of(808838744227446789L);
                    Instant instant = Instant.now();
                    assert message != null;
                    if (messagecontent.equalsIgnoreCase("!ticket")){
                    message.delete("Ticket Created").block();
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
                        overwrites.add(PermissionOverwrite.forRole(staffrole1, PermissionSet.of(VIEW_CHANNEL, SEND_MESSAGES, MENTION_EVERYONE), PermissionSet.of(MANAGE_MESSAGES)));
                            overwrites.add(PermissionOverwrite.forMember(userid, PermissionSet.of(VIEW_CHANNEL, SEND_MESSAGES), PermissionSet.of(MENTION_EVERYONE, MANAGE_MESSAGES)));
                            String randomid = rand1 + rand2 + rand3 + rand4 + rand5 + rand6;
                            TextChannel create = guild1.createTextChannel(TextChannelCreateSpec -> {
                                TextChannelCreateSpec.setName("ticket-" + name + "-" + randomid)
                                        .setPermissionOverwrites(overwrites);
                            }).block();
                            System.out.println("Ticket Created By: " + user);
                            long ticketchannelid = create.getId().asLong();
                            MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(ticketchannelid)).block();
                            assert channel != null;
                            channel.createMessage(messageCreateSpec -> messageCreateSpec.setContent("Please be patient, " + mention + " , " + staffrolemention + " will be with you soon.")).block();
                            MessageChannel channel2 = (MessageChannel) client.getChannelById(Snowflake.of(808838744609652785L)).block();
                            assert channel2 != null;
                            channel2.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**Ticket Opened**")
                                    .setDescription("Ticket #" + randomid + " opened by " + mention)
                                    .setColor(Color.of(51, 153, 255))
                                    .setTimestamp(instant)).block();
                        }
                    });
    }
}
