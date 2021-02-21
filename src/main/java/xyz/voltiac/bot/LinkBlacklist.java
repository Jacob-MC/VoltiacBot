package xyz.voltiac.bot;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.Role;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

import java.time.Instant;
import java.util.Optional;

public class LinkBlacklist {
    void LinkBlacklist(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    final Message m = event.getMessage();
                    final Optional<Member> member = event.getMember();
                    final Role r = member.get().getHighestRole().block();
                    assert r != null;
                    final int role = r.getRawPosition();
                    final Optional<User> a = m.getAuthor();
                    final String name = a.get().getUsername();
                    final String discriminator = a.get().getDiscriminator();
                    final String pfp = a.get().getAvatarUrl();
                    final String mention = a.get().getMention();
                    final Channel c = m.getChannel().block();
                    final Snowflake id = m.getChannelId();
                    final long blacklistedchannel = 808838745541050399L;
                    assert c != null;
                    final String channelmention = c.getMention();
                    final String messagecontent = m.getContent();
                    final Instant instant = Instant.now();
                    final String reason = "Contains Link";
                    final String reason2 = "Bad Word Usage";
                    final String messagecontentlowercase = messagecontent.toLowerCase();

                    if (role >= 27 || id.asLong() == blacklistedchannel) {

                    } else {
                        final MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(808838744609652785L)).block();
                        if (messagecontentlowercase.contains("https://") || messagecontentlowercase.contains("discord.gg") || messagecontentlowercase.contains(".com")
                                || messagecontentlowercase.contains(".net") || messagecontentlowercase.contains(".org")) {
                            m.delete(reason).block();
                            assert channel != null;
                            channel.createEmbed(EmbedCreateSpec -> EmbedCreateSpec.setColor(Color.RED)
                                    .setTitle("**Message Deleted**")
                                    .setDescription("Message Sent By " + mention + " Deleted in " + channelmention)
                                    .addField("**Reason**", reason, false)
                                    .setFooter(name + "#" + discriminator, pfp)
                                    .setTimestamp(instant)
                                    .setColor(Color.BLUE)).block();
                        }
                    }
                });
    }
}
