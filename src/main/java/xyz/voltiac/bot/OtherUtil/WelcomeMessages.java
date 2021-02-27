package xyz.voltiac.bot.OtherUtil;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.guild.MemberJoinEvent;
import discord4j.core.event.domain.guild.MemberLeaveEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Role;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import reactor.core.publisher.Mono;

import java.awt.*;
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
                    Channel infochannel = client.getChannelById(Snowflake.of(808838744609652776L)).block();
                    Channel supportchannel = client.getChannelById(Snowflake.of(808838744609652784L)).block();
                    String supportchannelmention = supportchannel.getMention();
                    String infochannelmention = infochannel.getMention();
                    Guild guild = event.getGuild().block();
                    String membercount = String.valueOf(guild.getMemberCount());
                    Instant instant = Instant.now();
                    Void r = member.addRole(Snowflake.of(809243022172356649L)).block();
                    MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(815034713370394674L)).block();
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
                    Channel infochannel = client.getChannelById(Snowflake.of(808838744609652776L)).block();
                    Channel supportchannel = client.getChannelById(Snowflake.of(808838744609652784L)).block();
                    String supportchannelmention = supportchannel.getMention();
                    String infochannelmention = infochannel.getMention();
                    Guild guild = event.getGuild().block();
                    String membercount = String.valueOf(guild.getMemberCount());
                    Instant instant = Instant.now();
                    MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(815034713370394674L)).block();
                    channel.createEmbed(EmbedCreateSpec -> {
                        EmbedCreateSpec.setTitle(username + "**Has Left the Server**")
                                .setDescription(username + "#" + discriminator + " Has Left the Server")
                                .addField("**Members**", "We now have " + membercount + " members.", false)
                                .setFooter(username + "#" + discriminator, avatarurl)
                                .setColor(Color.RED)
                                .setTimestamp(instant);
                    }).block();
                });
    }
}
