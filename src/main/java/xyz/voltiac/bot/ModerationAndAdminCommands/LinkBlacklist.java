package xyz.voltiac.bot.ModerationAndAdminCommands;

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
    public static void LinkBlacklist(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    Message m = event.getMessage();
                    Optional<Member> member = event.getMember();
                    Role r = member.get().getHighestRole().block();
                    assert r != null;
                    int role = r.getRawPosition();
                    Optional<User> a = m.getAuthor();
                    String name = a.get().getUsername();
                    String discriminator = a.get().getDiscriminator();
                    String pfp = a.get().getAvatarUrl();
                    String mention = a.get().getMention();
                    Channel c = m.getChannel().block();
                    Snowflake id = m.getChannelId();
                    long blacklistedchannel = 808838745541050399L;
                    assert c != null;
                    String channelmention = c.getMention();
                    String messagecontent = m.getContent();
                    Instant instant = Instant.now();
                    String reason = "Contains Link";
                    String reason2 = "Bad Word Usage";
                    String messagecontentlowercase = messagecontent.toLowerCase();

                    if (role >= 27 || id.asLong() == blacklistedchannel) {

                    } else {
                        MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(808838744609652785L)).block();
                        if (messagecontentlowercase.contains("https://") || messagecontentlowercase.contains("discord.gg") || messagecontentlowercase.contains(".com")
                                || messagecontentlowercase.contains(".net") || messagecontentlowercase.contains(".org")) {
                            m.delete(reason).block();
                            assert channel != null;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle("**Message Deleted**")
                                        .setDescription("Message Sent By " + mention + " Deleted in " + channelmention)
                                        .addField("**Reason**", reason, false)
                                        .setFooter(name + "#" + discriminator, pfp)
                                        .setTimestamp(instant)
                                        .setColor(Color.of(51, 153, 255));
                            }).block();
                        }
                    }
                });
    }
}
