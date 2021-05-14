package xyz.voltiac.bot.Commands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.rest.util.Color;
import xyz.voltiac.bot.Main;

import java.time.Instant;

public class CloseTicket {
    public static void CloseTicket(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event1 -> {
                    try {
                    Message message1 = event1.getMessage();
                    long channel = message1.getChannelId().asLong();
                    TextChannel channel1 = (TextChannel) message1.getChannel().block();
                    String messagecontent = message1.getContent();
                    User author = message1.getAuthorAsMember().block();
                    String authormention = author.getMention();
                    assert authormention != null;
                    String username = author.getUsername();
                    String channelname = channel1.getName();
                    Instant instant = Instant.now();
                    Guild guild = message1.getGuild().block();
                    if (messagecontent.equalsIgnoreCase(Main.prefix + "close") && !author.isBot()) {
                        if (channelname.contains("ticket")) {
                            int index = channelname.lastIndexOf("-id-") + 4;
                            String ticketid = channelname.substring(index);
                            message1.getChannel().block().delete("Ticket Closed.").block();
                            System.out.println("Ticket Closed By: " + username);
                            String channels = guild.getChannels().collectList().block().toString();
                            Snowflake ID = null;
                            String channelsinfo = guild.getChannels().collectList().block().toString();
                            if (channels.toLowerCase().contains("server-logs")) { ;
                                int beginindex = channelsinfo.indexOf("server-logs");
                                String channelinfo = channelsinfo.substring(beginindex);
                                int channelidbeginindex = channelinfo.indexOf("BaseChannel{data=ChannelData{id=") + 32;
                                int channelidendindex = channelidbeginindex + 18;
                                ID = Snowflake.of(channelinfo.substring(channelidbeginindex, channelidendindex));
                            } else {
                            }
                            MessageChannel channel2 = (MessageChannel) client.getChannelById(ID).block();
                            assert channel2 != null;
                            channel2.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**Ticket Closed**")
                                    .setDescription("Ticket #" + ticketid + " closed by " + authormention)
                                    .setColor(Color.RED)
                                    .setTimestamp(instant)).block();
                            System.out.println("Ticket Close Command Executed By: " + username);
                        }
                    }
                    } catch (Exception e) {
                    }
                });
    }
}
