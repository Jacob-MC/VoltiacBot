package xyz.voltiac.bot.Commands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import discord4j.rest.util.Image;
import xyz.voltiac.bot.Main;

public class ServerInfo {
    public static void serverinfo(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                        Message message = event.getMessage();
                        Member member = message.getAuthorAsMember().block();
                        String username = member.getUsername();
                        String avatar = member.getAvatarUrl();
                        MessageChannel channel = message.getChannel().block();
                        Guild guild = event.getGuild().block();
                        String membercount = String.valueOf(guild.getMemberCount());
                        String servericon = guild.getIconUrl(Image.Format.PNG).toString();
                        String guildowner = guild.getOwner().block().getUsername() + guild.getOwner().block().getDiscriminator();
                        if (message.getContent().equalsIgnoreCase(Main.prefix + "serverinfo") && !member.isBot()) {
                            channel.createEmbed(embedCreateSpec -> {
                                embedCreateSpec.setTitle("**Server Info - " + guild.getName() + "**")
                                        .addField("Owner", guildowner, true)
                                        .addField("Owner ID", guild.getOwnerId().asString(), true)
                                        .addField("Server ID", guild.getId().asString(), false)
                                        .addField("Region", guild.getRegion().block().getName(), false)
                                        .addField("Total Members", membercount, false)
                                        .addField("Verification Level", guild.getVerificationLevel().toString(), false)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Command Executed By: " + username, avatar);
                            }).block();
                        }
                    } catch (Exception e) {
                        System.out.println("ERROR:");
                        e.printStackTrace();
                    }
                });
    }
}
