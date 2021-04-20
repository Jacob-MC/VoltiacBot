package xyz.voltiac.bot.OtherUtil;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.guild.MemberJoinEvent;
import discord4j.core.event.domain.guild.MemberLeaveEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

import java.time.Instant;

public class WelcomeMessages {
    public static void WelcomeMessages(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MemberJoinEvent.class)
                .subscribe(event -> {
                    Member member = event.getMember();
                    String username = member.getUsername();
                    String discriminator = member.getDiscriminator();
                    String mention = member.getMention();
                    String avatarurl = member.getAvatarUrl();
                    Guild guild = event.getGuild().block();
                    Snowflake infochannelID = null;
                    String channels = guild.getChannels().collectList().block().toString();
                    if (channels.toLowerCase().contains("info")) { ;
                        int beginindex = channels.indexOf("info");
                        String channelinfo = channels.substring(beginindex);
                        int channelidbeginindex = channelinfo.indexOf("BaseChannel{data=ChannelData{id=") + 32;
                        int channelidendindex = channelidbeginindex + 18;
                        infochannelID = Snowflake.of(channelinfo.substring(channelidbeginindex, channelidendindex));
                    } else {
                    }

                    Snowflake supportchannelID = null;
                    if (channels.toLowerCase().contains("support")) { ;
                        int beginindex = channels.indexOf("support");
                        String channelinfo = channels.substring(beginindex);
                        int channelidbeginindex = channelinfo.indexOf("BaseChannel{data=ChannelData{id=") + 32;
                        int channelidendindex = channelidbeginindex + 18;
                        supportchannelID = Snowflake.of(channelinfo.substring(channelidbeginindex, channelidendindex));
                    } else {
                    }

                    Snowflake welcomechannelID = null;
                    if (channels.toLowerCase().contains("welcome")) { ;
                        int beginindex = channels.indexOf("welcome");
                        String channelinfo = channels.substring(beginindex);
                        int channelidbeginindex = channelinfo.indexOf("BaseChannel{data=ChannelData{id=") + 32;
                        int channelidendindex = channelidbeginindex + 18;
                        welcomechannelID = Snowflake.of(channelinfo.substring(channelidbeginindex, channelidendindex));
                    } else {
                    }

                    Snowflake memberroleID = null;
                    String roles = guild.getRoles().collectList().block().toString();
                    if (roles.toLowerCase().contains("《 member 》")) {
                        int beginindex = roles.toLowerCase().indexOf("《 member 》") - 25;
                        int endindex = beginindex + 18;
                        memberroleID = Snowflake.of(roles.substring(beginindex, endindex));
                    } else {
                    }

                    Channel infochannel = client.getChannelById(infochannelID).block();
                    Channel supportchannel = client.getChannelById(supportchannelID).block();
                    String supportchannelmention = supportchannel.getMention();
                    String infochannelmention = infochannel.getMention();
                    String membercount = String.valueOf(guild.getMemberCount());
                    Instant instant = Instant.now();
                    Void r = member.addRole(memberroleID).block();
                    MessageChannel channel = (MessageChannel) client.getChannelById(welcomechannelID).block();
                    channel.createEmbed(EmbedCreateSpec -> {
                        EmbedCreateSpec.setTitle(username + " **Has Joined the Server!**")
                                .setDescription("Welcome " + mention + " to the Voltiac Network Discord Server!")
                                .addField("**How to Join**", "If you want to join the minecraft server, check out " + infochannelmention, false)
                                .addField("**Support**", "If you have questions or need support, create a ticket in " + supportchannelmention + ", or use !ticket", false)
                                .addField("**Members**", "We now have " + membercount + " members!", false)
                                .setFooter(username + "#" + discriminator, avatarurl)
                                .setColor(Color.GREEN)
                                .setTimestamp(instant);
                    }).block();
                });
        client.getEventDispatcher().on(MemberLeaveEvent.class)
                .subscribe(event -> {
                    Member member = event.getMember().get();
                    String username = member.getUsername();
                    String discriminator = member.getDiscriminator();
                    String avatarurl = member.getAvatarUrl();
                    Guild guild = event.getGuild().block();
                    String membercount = String.valueOf(guild.getMemberCount());
                    Instant instant = Instant.now();

                    Snowflake welcomechannelID = null;
                    String channels = guild.getChannels().collectList().block().toString();
                    if (channels.toLowerCase().contains("welcome")) { ;
                        int beginindex = channels.indexOf("welcome");
                        String channelinfo = channels.substring(beginindex);
                        int channelidbeginindex = channelinfo.indexOf("BaseChannel{data=ChannelData{id=") + 32;
                        int channelidendindex = channelidbeginindex + 18;
                        welcomechannelID = Snowflake.of(channelinfo.substring(channelidbeginindex, channelidendindex));
                    } else {
                    }

                    MessageChannel channel = (MessageChannel) client.getChannelById(welcomechannelID).block();
                    channel.createEmbed(EmbedCreateSpec -> {
                        EmbedCreateSpec.setTitle(username + " **Has Left the Server**")
                                .setDescription(username + "#" + discriminator + " Has Left the Server")
                                .addField("**Members**", "We now have " + membercount + " members.", false)
                                .setFooter(username + "#" + discriminator, avatarurl)
                                .setColor(Color.RED)
                                .setTimestamp(instant);
                    }).block();
                });
    }
}
