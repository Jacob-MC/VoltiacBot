package xyz.voltiac.bot.OtherUtil;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.object.Embed;
import discord4j.core.object.PermissionOverwrite;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.Role;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.core.spec.TextChannelEditSpec;
import discord4j.rest.util.Color;
import discord4j.rest.util.Permission;
import discord4j.rest.util.PermissionSet;
import xyz.voltiac.bot.Main;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Tickets {
    public static void tickets(GatewayDiscordClient client) {
            client.getEventDispatcher().on(ReactionAddEvent.class)
                    .subscribe(event -> {
                        try {
                            Guild guild = event.getGuild().block();
                            final ArrayList<Integer> idlist = new ArrayList<Integer>();
                            for (int i = 1; i < 10; ++i) {
                                idlist.add(new Integer(i));
                            }
                            Collections.shuffle(idlist);
                            final Random r = new Random();
                            final String c1 = String.valueOf((char) (r.nextInt(26) + 97));
                            final String c2 = String.valueOf((char) (r.nextInt(26) + 97));
                            final String c1upper = String.valueOf((char) (r.nextInt(26) + 97)).toUpperCase();
                            final String c2upper = String.valueOf((char) (r.nextInt(26) + 97)).toUpperCase();
                            final String randnum1 = idlist.get(0).toString();
                            final String randnum2 = idlist.get(1).toString();
                            final String text = c1 + c2 + c1upper + c2upper + randnum1 + randnum2;
                            final String shuffledtext = Shuffle.shuffle(text);
                            final String shuffledtext2 = Shuffle.shuffle(shuffledtext);

                            Message message = event.getMessage().block();
                            Member member = event.getMember().get();
                            String membermention = member.getMention();
                            String id = member.getId().asString();
                            ReactionEmoji reaction = event.getEmoji();
                            String username = member.getUsername();
                            Instant instant = Instant.now();
                            MessageChannel channel = message.getChannel().block();

                            if (message.getEmbeds().size() > 0) {
                                try {
                                    if (message.getEmbeds().get(0).getTitle().get().equals("**Ticket**") && message.getEmbeds().get(0).getDescription().get().equals("React to this message to create a ticket!") && !member.isBot()) {
                                        try {
                                            String roles = guild.getRoles().collectList().block().toString().toLowerCase();
                                            int beginIndex = roles.indexOf("staff") - 30;
                                            int endIndex = roles.indexOf("staff");
                                            String filteredrole = roles.substring(beginIndex, endIndex);
                                            int beginIndex2 = filteredrole.indexOf("id=") + 3;
                                            int endIndex2 = filteredrole.indexOf(", name=");
                                            String roleid = filteredrole.substring(beginIndex2, endIndex2);
                                            message.removeReaction(reaction, Snowflake.of(id)).block();
                                            Role everyonerole = guild.getEveryoneRole().block();
                                            Snowflake everyoneroleid = everyonerole.getId();
                                            String staffrolemention = guild.getRoleById(Snowflake.of(roleid)).block().getMention();

                                            HashSet<PermissionOverwrite> overwrites = new HashSet<PermissionOverwrite>();
                                            overwrites.add(PermissionOverwrite.forRole(everyoneroleid, PermissionSet.of(new Permission[0]), PermissionSet.of(Permission.VIEW_CHANNEL)));
                                            overwrites.add(PermissionOverwrite.forMember(Snowflake.of(id), PermissionSet.of(new Permission[]{Permission.VIEW_CHANNEL, Permission.SEND_MESSAGES}), PermissionSet.of(new Permission[]{Permission.MENTION_EVERYONE, Permission.MANAGE_MESSAGES})));
                                            overwrites.add(PermissionOverwrite.forRole(Snowflake.of(roleid), PermissionSet.of(new Permission[]{Permission.VIEW_CHANNEL, Permission.SEND_MESSAGES}), PermissionSet.of(new Permission[]{Permission.MENTION_EVERYONE, Permission.MANAGE_MESSAGES})));
                                            TextChannel ticketchannel = guild.createTextChannel(TextChannelCreateSpec -> TextChannelCreateSpec.setName("ticket-" + username + "-id-" + shuffledtext2).setPermissionOverwrites(overwrites)).block();
                                            message.removeReaction(reaction, member.getId()).block();
                                            ticketchannel.edit(TextChannelEditSpec -> {
                                                TextChannelEditSpec.setTopic("Once you are done, you can close this ticket with " + Main.prefix + "close");
                                            }).block();
                                            ticketchannel.createMessage("Please be patient, " + membermention + ", " + staffrolemention + " will be with you soon.").block();
                                        } catch (Exception e) {
                                        }
                                        try {
                                            String channels = guild.getChannels().collectList().block().toString();
                                            Snowflake ID = null;
                                            String channelsinfo = guild.getChannels().collectList().block().toString();
                                            if (channels.toLowerCase().contains("server-logs")) {
                                                ;
                                                int beginindex = channelsinfo.indexOf("server-logs");
                                                String channelinfo = channelsinfo.substring(beginindex);
                                                int channelidbeginindex = channelinfo.indexOf("BaseChannel{data=ChannelData{id=") + 32;
                                                int channelidendindex = channelidbeginindex + 18;
                                                ID = Snowflake.of(channelinfo.substring(channelidbeginindex, channelidendindex));
                                            } else {
                                            }
                                            MessageChannel channel2 = (MessageChannel) client.getChannelById(ID).block();
                                            assert channel2 != null;
                                            System.out.println("Ticket Opened By: " + username);
                                            channel2.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**Ticket Opened**")
                                                    .setDescription("Ticket #" + shuffledtext2 + " opened by " + membermention)
                                                    .setColor(Color.of(51, 153, 255))
                                                    .setTimestamp(instant)).block();
                                        } catch (Exception e) {
                                            System.out.println("Unable to log ticket open.");
                                        }
                                    }
                                } catch (Exception e) {
                                }
                            }
                        } catch (Exception e) {
                        }
                    });
    }
}