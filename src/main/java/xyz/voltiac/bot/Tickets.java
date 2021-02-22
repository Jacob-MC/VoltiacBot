package xyz.voltiac.bot;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.object.PermissionOverwrite;
import discord4j.core.object.entity.*;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.rest.util.Color;
import discord4j.rest.util.PermissionSet;

import java.time.Instant;
import java.util.Collections;

import static discord4j.rest.util.Permission.*;

public class Tickets {
    void TicketListeners(GatewayDiscordClient client) {
        client.getEventDispatcher().on(ReactionAddEvent.class)
                .subscribe(event -> {
                    Snowflake id = event.getMessageId();
                    ReactionEmoji reactionemoji = event.getEmoji();
                    Message message = event.getMessage().block();
                    Member m = event.getMember().get();
                    String user = m.getUsername();
                    Member member = client.getMemberById(Snowflake.of(808838744203198503L), Snowflake.of(809487051564908576L)).block();
                    assert member != null;
                    String botpfp = member.getAvatarUrl();
                    Guild guild1 = client.getGuildById(Snowflake.of(808838744203198503L)).block();
                    assert guild1 != null;
                    Role staffrole = guild1.getRoleById(Snowflake.of(808838744227446789L)).block();
                    assert staffrole != null;
                    String staffrolemention = staffrole.getMention();
                    String mention = m.getMention();
                    String name = m.getUsername();
                    String disc = m.getDiscriminator();
                    Guild guild = m.getGuild().block();
                    Snowflake userid = m.getId();
                    long messageid = 809278160704897034L;
                    long blacklistedrole = 809243022172356649L;
                    long staffrole1 = 808838744227446789L;

                    assert message != null;

                    if (id.asLong() == messageid){
                        Snowflake r = event.getUserId();
                        if (r.asLong() == r.asLong()) {
                            Role role1 = m.getHighestRole().block();
                            assert role1 != null;
                            Void removereaction = message.removeReaction(reactionemoji, userid).block();
                            TextChannel create = guild1.createTextChannel(TextChannelCreateSpec -> TextChannelCreateSpec.setName("ticket-" + name + "-" + disc)
                                    .setPermissionOverwrites(Collections.singleton(PermissionOverwrite.forMember(userid, PermissionSet.of(VIEW_CHANNEL, SEND_MESSAGES), PermissionSet.of(MENTION_EVERYONE, MANAGE_MESSAGES))))
                                    .setPermissionOverwrites(Collections.singleton(PermissionOverwrite.forRole(Snowflake.of(blacklistedrole), PermissionSet.of(), PermissionSet.of(VIEW_CHANNEL))))
                                    .setPermissionOverwrites(Collections.singleton(PermissionOverwrite.forRole(Snowflake.of(staffrole1), PermissionSet.of(VIEW_CHANNEL, SEND_MESSAGES), PermissionSet.of())))).block();
                            assert create != null;
                            System.out.println("Ticket Created By: " + user);
                            long ticketchannelid = create.getId().asLong();
                            client.getEventDispatcher().on(MessageCreateEvent.class)
                                    .subscribe(event1 -> {
                                        Message message1 = event1.getMessage();
                                        long channel = message1.getChannelId().asLong();
                                        Channel channel1 = message1.getChannel().block();
                                        String messagecontent = message1.getContent();
                                        User author = message1.getAuthor().get();
                                        String authormention = author.getMention();
                                        String username = author.getUsername();
                                        Instant instant = Instant.now();
                                        if ("!close".equalsIgnoreCase(messagecontent)) {
                                            if(String.valueOf(channel).equals(String.valueOf(ticketchannelid))) {
                                                TextChannel delete = (TextChannel) client.getChannelById(Snowflake.of(ticketchannelid)).block();
                                                assert delete != null;
                                                delete.delete("Ticket Closed.").block();
                                                System.out.println("Ticket Closed By: " + username);

                                                MessageChannel channel2 = (MessageChannel) client.getChannelById(Snowflake.of(809196891488518214L)).block();
                                                assert channel2 != null;
                                                channel2.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**Ticket Closed**")
                                                        .setDescription("Ticket opened by " + mention + " closed by " + authormention)
                                                        .setColor(Color.of(51, 153, 255))
                                                        .setTimestamp(instant)).block();
                                            }
                                        }
                                    });
                            MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(ticketchannelid)).block();
                            assert channel != null;
                            channel.createMessage(messageCreateSpec -> messageCreateSpec.setContent("Please be patient, " + mention + " , " + staffrolemention + " will be with you soon.")).block();
                        }
                    }
                });
    }
}
