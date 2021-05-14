package xyz.voltiac.bot.FunAndUtil;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.Invite;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.InviteCreateSpec;
import discord4j.rest.util.Color;
import xyz.voltiac.bot.Main;

public class YouTubeTogether {
    public static void YouTubeTogether(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    Message message = event.getMessage();
                    String messagecontent = message.getContent().toLowerCase();
                    Member member = message.getAuthorAsMember().block();
                    MessageChannel channel = message.getChannel().block();
                    String username = member.getUsername();
                    String avatar = member.getAvatarUrl();
                    try {
                            String ytemoji = client.getGuildEmojiById(Snowflake.of(110373943822540800l), Snowflake.of(314349922885566475l)).block().asFormat();
                            if (messagecontent.equalsIgnoreCase(Main.prefix + "help yt") && !member.isBot()) {
                                channel.createEmbed(EmbedCreateSpec -> {
                                    EmbedCreateSpec.setTitle("**" + ytemoji + " " + Main.prefix + "yt | ONLY WORKS ON PC**")
                                            .addField("**" + Main.prefix + "yt**", "Provides more detailed info on how to use this command.", false)
                                            .addField("**" + Main.prefix + "yt [Voice Channel ID]**", "Creates an YouTube Together invite for the specified channel.", false)
                                            .setFooter("Command Executed By: " + username, avatar)
                                            .setColor(Color.of(51, 153, 255));
                                }).block();
                                System.out.println("YT Command Executed By: " + username);
                            }

                            if (messagecontent.equalsIgnoreCase(Main.prefix + "yt") && !member.isBot()) {
                                channel.createEmbed(EmbedCreateSpec -> {
                                    EmbedCreateSpec.setTitle("**" + ytemoji + " " + Main.prefix + "yt | ONLY WORKS ON PC**")
                                            .setDescription("Posts a YouTube Together invite for the specified channel")
                                            .addField("**Usage**:", Main.prefix + "yt (Voice Channel ID)", false)
                                            .addField("**Example:**", Main.prefix + "yt 753431688335327308", false)
                                            .setFooter("Command Executed By: " + username, avatar)
                                            .setColor(Color.of(51, 153, 255));
                                }).block();
                                System.out.println("YT Command Executed By: " + username);
                            }

                            if (messagecontent.startsWith(Main.prefix + "yt") && messagecontent.contains(" ") && !member.isBot()) {
                                int beginindex = messagecontent.indexOf(" ") + 1;
                                String channelid = messagecontent.substring(beginindex, beginindex + 18);
                                Channel vc = client.getChannelById(Snowflake.of(channelid)).block();
                                String invite = "https://discord.gg/" + vc.getRestChannel().createInvite(new InviteCreateSpec().setMaxAge(86400).setMaxUses(10).setTargetApplicationId(Snowflake.of(755600276941176913l)).setTargetType(Invite.Type.of(2)).setTemporary(false).asRequest(), "Voice Activity").block().code();
                                channel.createEmbed(EmbedCreateSpec -> {
                                    EmbedCreateSpec.setTitle("**" + ytemoji + " YouTube Together**")
                                            .addField("Click the link below to join the voice channel.", invite, false)
                                            .setColor(Color.of(51, 153, 255))
                                            .setFooter("Command Executed By: " + username, avatar);
                                }).block();
                                System.out.println("YT Command Executed By: " + username);
                            }
                    } catch (Exception e) {
                        channel.createMessage("❌ Invalid channel ID. Please copy and paste the ID of the voice channel you wish to use YouTube Together in.").block();
                    }
                });
    }
}