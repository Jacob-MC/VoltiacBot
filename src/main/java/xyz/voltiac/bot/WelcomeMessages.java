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
                    Member m = event.getMember();
                    String n = m.getUsername();
                    String d = m.getDiscriminator();
                    String p = m.getMention();
                    String pfp = m.getAvatarUrl();
                    Guild guild = client.getGuildById(Snowflake.of(808838744203198503L)).block();
                    assert guild != null;
                    int count = guild.getMemberCount();
                    Mono<Void> r = m.addRole(Snowflake.of(809243022172356649L));
                    r.block();
                    Instant instant = Instant.now();
                    MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(808838744609652782L)).block();
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
                    User u = event.getUser();
                    String n = u.getUsername();
                    String d = u.getDiscriminator();
                    String p = u.getMention();
                    String pfp = u.getAvatarUrl();
                    Instant instant = Instant.now();
                    Guild guild = client.getGuildById(Snowflake.of(808838744203198503L)).block();
                    assert guild != null;
                    int count = guild.getMemberCount();
                    System.out.println(count);
                    MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(808838744609652782L)).block();
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
