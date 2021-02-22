package xyz.voltiac.bot;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

import java.time.Instant;
import java.util.Optional;

public class MessageLogs {
    void MessageLogs(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    Message m = event.getMessage();
                    String messagecontent = m.getContent();
                    Channel c = m.getChannel().block();
                    Snowflake channelid = m.getChannelId();
                    long blacklistedchannelid = 809196891488518214L;
                    long blacklistedauthorid = 787057269903851520L;
                    assert c != null;
                    String cname = c.getMention();
                    Optional<User> a = m.getAuthor();
                    Snowflake authorid = a.get().getId();
                    String mention = a.get().getMention();
                    String name = a.get().getUsername();
                    String discriminator = a.get().getDiscriminator();
                    String pfp = a.get().getAvatarUrl();
                    Instant instant = Instant.now();
                    MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(809196891488518214L)).block();
                    String embeds = String.valueOf(m.getEmbeds());
                    int length = embeds.length();
                    if (channelid.asLong() != blacklistedchannelid && authorid.asLong() != blacklistedauthorid && length == 2) {
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**Message Sent**")
                                .setDescription("Message Sent In " + cname + " By " + mention)
                                .addField("**Message Content**", messagecontent, false)
                                .setFooter(name + "#" + discriminator, pfp)
                                .setTimestamp(instant)
                                .setColor(Color.BLUE)).block();
                    }
                });
    }
}
