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

import java.time.Instant;

public class CloseTicket {
    public static void CloseTicket(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event1 -> {
                    Message message1 = event1.getMessage();
                    long channel = message1.getChannelId().asLong();
                    TextChannel channel1 = (TextChannel) message1.getChannel().block();
                    String messagecontent = message1.getContent();
                    User author = message1.getAuthor().get();
                    String authormention = author.getMention();
                    String username = author.getUsername();
                    String channelname = channel1.getName();
                    Instant instant = Instant.now();
                    Guild guild = message1.getGuild().block();
                    if ("!close".equalsIgnoreCase(messagecontent)) {
                        if(channelname.contains("ticket")) {
                            int index = channelname.lastIndexOf("-") + 1;
                            String ticketid = channelname.substring(index);
                            TextChannel delete = (TextChannel) client.getChannelById(Snowflake.of(channel)).block();
                            assert delete != null;
                            delete.delete("Ticket Closed.").block();
                            System.out.println("Ticket Closed By: " + username);
                            String channels = guild.getChannels().collectList().block().toString();
                            Snowflake ID = null;
                            String channelsinfo = guild.getChannels().collectList().block().toString();
                            if (channels.toLowerCase().contains("logs")) { ;
                                int beginindex = channelsinfo.indexOf("logs");
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
                                    .setColor(Color.of(51, 153, 255))
                                    .setTimestamp(instant)).block();
                        }
                    }
                });
    }
}
