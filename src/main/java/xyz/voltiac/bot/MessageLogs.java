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
                    final Message m = event.getMessage();
                    final String messagecontent = m.getContent();
                    final Channel c = m.getChannel().block();
                    final Snowflake channelid = m.getChannelId();
                    final long blacklistedchannelid = 809196891488518214L;
                    final long blacklistedauthorid = 787057269903851520L;
                    assert c != null;
                    final String cname = c.getMention();
                    final Optional<User> a = m.getAuthor();
                    final Snowflake authorid = a.get().getId();
                    final String mention = a.get().getMention();
                    final String name = a.get().getUsername();
                    final String discriminator = a.get().getDiscriminator();
                    final String pfp = a.get().getAvatarUrl();
                    final Instant instant = Instant.now();
                    final MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(809196891488518214L)).block();
                    if (channelid.asLong() != blacklistedchannelid && authorid.asLong() != blacklistedauthorid) {
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
