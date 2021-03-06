package xyz.voltiac.bot.ModerationAndAdminCommands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

import java.time.Instant;
import java.util.Optional;

public class MessageLogs {
    public static void MessageLogs(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                    Message m = event.getMessage();
                    String messagecontent = m.getContent();
                    Channel c = m.getChannel().block();
                    Snowflake channelid = m.getChannelId();
                    Guild guild = m.getGuild().block();
                    assert c != null;
                    String cname = c.getMention();
                    Optional<User> a = m.getAuthor();
                    Snowflake authorid = a.get().getId();
                    String mention = a.get().getMention();
                    String name = a.get().getUsername();
                    String discriminator = a.get().getDiscriminator();
                    String pfp = a.get().getAvatarUrl();
                    Instant instant = Instant.now();
                    try {
                        Snowflake ID = null;
                        String channels = guild.getChannels().collectList().toString();
                        if (channels.toLowerCase().contains("message-logs")) {
                            int beginindex = channels.indexOf("message-logs");
                            String channelinfo = channels.substring(beginindex);
                            int channelidbeginindex = channelinfo.indexOf("BaseChannel{data=ChannelData{id=") + 32;
                            int channelidendindex = channelidbeginindex + 18;
                            ID = Snowflake.of(channelinfo.substring(channelidbeginindex, channelidendindex));
                        }

                        MessageChannel channel = (MessageChannel) client.getChannelById(ID).block();
                        String embeds = String.valueOf(m.getEmbeds());
                        int length = embeds.length();
                        if (channelid.asLong() != ID.asLong() && length == 2) {
                            assert channel != null;
                            channel.createEmbed(embedCreateSpec -> {
                                embedCreateSpec.setTitle("**Message Sent**")
                                        .setDescription("Message Sent In " + cname + " By " + mention)
                                        .addField("**Message Content**", messagecontent, false)
                                        .setFooter(name + "#" + discriminator, pfp)
                                        .setTimestamp(instant)
                                        .setColor(Color.of(51, 153, 255));
                            }).block();
                        }
                    } catch (Exception e) {
                        String servername = event.getGuild().block().getName();
                        System.out.println("Unable to log messages in server: " + servername);
                    }
                    } catch (Exception e) {
                    }
                });
    }
}
