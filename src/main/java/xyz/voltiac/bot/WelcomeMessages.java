package xyz.voltiac.bot;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.guild.MemberJoinEvent;
import discord4j.core.event.domain.guild.MemberLeaveEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import reactor.core.publisher.Mono;

import java.time.Instant;

public class WelcomeMessages {
    void WelcomeMessages(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MemberJoinEvent.class)
                .subscribe(event -> {
                    final Member m = event.getMember();
                    final String n = m.getUsername();
                    final String d = m.getDiscriminator();
                    final String p = m.getMention();
                    final String pfp = m.getAvatarUrl();
                    final Guild guild = client.getGuildById(Snowflake.of(808838744203198503L)).block();
                    assert guild != null;
                    int count = guild.getMemberCount();
                    final Mono<Void> r = m.addRole(Snowflake.of(809243022172356649L));
                    r.block();
                    final Instant instant = Instant.now();
                    final MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(808838744609652782L)).block();
                    assert channel != null;
                    channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle(n + " **Has Joined the Server**")
                            .setColor(Color.GREEN)
                            .setDescription("Welcome " + p + " to the Voltiac Network Discord Server!")
                            .addField("**How to Join**", "If you want to join the minecraft server, check out <#808838744609652776>", false)
                            .addField("**Support**", "If you have questions or need support, create a ticket in <#808838744609652784>", false)
                            .addField("**Members**", "We now have " + count + " members!", false)
                            .setFooter(n + "#" + d, pfp)
                            .setTimestamp(instant)
                            .setThumbnail(pfp)).block();
                });
        client.getEventDispatcher().on(MemberLeaveEvent.class)
                .subscribe(event -> {
                    final User u = event.getUser();
                    final String n = u.getUsername();
                    final String d = u.getDiscriminator();
                    final String p = u.getMention();
                    final String pfp = u.getAvatarUrl();
                    final Instant instant = Instant.now();
                    final Guild guild = client.getGuildById(Snowflake.of(808838744203198503L)).block();
                    assert guild != null;
                    int count = guild.getMemberCount();
                    System.out.println(count);
                    final MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(808838744609652782L)).block();
                    assert channel != null;
                    channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle(n + " **Has Left the Server**")
                            .setColor(Color.RED)
                            .setDescription(n + "#" + d + " Has Left the Server")
                            .addField("**Members**", "We now have " + count + " members.", false)
                            .setFooter(n + "#" + d, pfp)
                            .setTimestamp(instant)
                            .setThumbnail(pfp)).block();
                });
    }
}
