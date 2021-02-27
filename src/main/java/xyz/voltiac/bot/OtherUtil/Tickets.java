package xyz.voltiac.bot.OtherUtil;

import com.sun.istack.internal.Pool;
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
import discord4j.rest.util.Permission;
import discord4j.rest.util.PermissionSet;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.*;

import static discord4j.rest.util.Permission.*;

public class Tickets {
    public static void TicketListeners(GatewayDiscordClient client) {
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
                    Role everyonerole = guild.getEveryoneRole().block();
                    Snowflake everyoneroleid = everyonerole.getId();
                    Snowflake staffrole1 = Snowflake.of(808838744227446789L);
                    Instant instant = Instant.now();
                    assert message != null;

                    if (id.asLong() == messageid){
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
                    }
                });
    }
}
