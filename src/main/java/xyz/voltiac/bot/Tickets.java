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
                    final Snowflake id = event.getMessageId();
                    final ReactionEmoji reactionemoji = event.getEmoji();
                    final Message message = event.getMessage().block();
                    final Member m = event.getMember().get();
                    final String user = m.getUsername();
                    final Member member = client.getMemberById(Snowflake.of(808838744203198503L), Snowflake.of(809487051564908576L)).block();
                    assert member != null;
                    final String botpfp = member.getAvatarUrl();
                    final Guild guild1 = client.getGuildById(Snowflake.of(808838744203198503L)).block();
                    assert guild1 != null;
                    final Role staffrole = guild1.getRoleById(Snowflake.of(808838744227446789L)).block();
                    assert staffrole != null;
                    final String staffrolemention = staffrole.getMention();
                    final String mention = m.getMention();
                    final String name = m.getUsername();
                    final String disc = m.getDiscriminator();
                    final Guild guild = m.getGuild().block();
                    final Snowflake userid = m.getId();
                    final long messageid = 809278160704897034L;
                    final long blacklistedrole = 809243022172356649L;
                    final long staffrole1 = 808838744227446789L;

                    assert message != null;

                    if (id.asLong() == messageid){
                        final Snowflake r = event.getUserId();
                        if (r.asLong() == r.asLong()) {
                            final Role role1 = m.getHighestRole().block();
                            assert role1 != null;
                            final Void removereaction = message.removeReaction(reactionemoji, userid).block();
                            final TextChannel create = guild1.createTextChannel(TextChannelCreateSpec -> TextChannelCreateSpec.setName("ticket-" + name + "-" + disc)
                                    .setPermissionOverwrites(Collections.singleton(PermissionOverwrite.forMember(userid, PermissionSet.of(VIEW_CHANNEL, SEND_MESSAGES), PermissionSet.of(MENTION_EVERYONE, MANAGE_MESSAGES))))
                                    .setPermissionOverwrites(Collections.singleton(PermissionOverwrite.forRole(Snowflake.of(blacklistedrole), PermissionSet.of(), PermissionSet.of(VIEW_CHANNEL))))
                                    .setPermissionOverwrites(Collections.singleton(PermissionOverwrite.forRole(Snowflake.of(staffrole1), PermissionSet.of(VIEW_CHANNEL, SEND_MESSAGES), PermissionSet.of())))).block();
                            assert create != null;
                            System.out.println("Ticket Created By: " + user);
                            long ticketchannelid = create.getId().asLong();
                            client.getEventDispatcher().on(MessageCreateEvent.class)
                                    .subscribe(event1 -> {
                                        final Message message1 = event1.getMessage();
                                        final long channel = message1.getChannelId().asLong();
                                        final Channel channel1 = message1.getChannel().block();
                                        final String messagecontent = message1.getContent();
                                        final User author = message1.getAuthor().get();
                                        final String authormention = author.getMention();
                                        final String username = author.getUsername();
                                        final Instant instant = Instant.now();
                                        if ("!close".equalsIgnoreCase(messagecontent)) {
                                            if(String.valueOf(channel).equals(String.valueOf(ticketchannelid))) {
                                                final TextChannel delete = (TextChannel) client.getChannelById(Snowflake.of(ticketchannelid)).block();
                                                assert delete != null;
                                                delete.delete("Ticket Closed.").block();
                                                System.out.println("Ticket Closed By: " + username);

                                                final MessageChannel channel2 = (MessageChannel) client.getChannelById(Snowflake.of(809196891488518214L)).block();
                                                assert channel2 != null;
                                                channel2.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**Ticket Closed**")
                                                        .setDescription("Ticket opened by " + mention + " closed by " + authormention)
                                                        .setColor(Color.of(51, 153, 255))
                                                        .setTimestamp(instant)).block();
                                            }
                                        }
                                    });
                            final MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(ticketchannelid)).block();
                            assert channel != null;
                            channel.createMessage(messageCreateSpec -> messageCreateSpec.setContent("Please be patient, " + mention + " , " + staffrolemention + " will be with you soon.")).block();
                        }
                    }
                });
    }
}
