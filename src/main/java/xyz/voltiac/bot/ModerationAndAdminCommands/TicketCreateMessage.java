package xyz.voltiac.bot.ModerationAndAdminCommands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.*;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.core.spec.MessageCreateSpec;
import discord4j.rest.util.Color;
import discord4j.rest.util.Permission;

import java.time.Instant;

public class TicketCreateMessage {
    public static void TicketCreateMessage(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                   Message message = event.getMessage();
                   Member member = message.getAuthorAsMember().block();
                   String messagecontent = message.getContent();
                   String messagechannelid = message.getChannelId().toString();
                   MessageChannel channel = message.getChannel().block();
                   User user = message.getAuthor().get();
                   String username = user.getUsername();
                   String avatarurl = user.getAvatarUrl();
                   Guild guild = event.getGuild().block();
                   Snowflake guildid = guild.getId();
                    Role highestmemberrole = member.getHighestRole().block();
                    int memberrolepos = highestmemberrole.getRawPosition();
                    try {
                        Snowflake supportchannelID = null;
                        String supportchannelIDstring = null;
                        String channels = guild.getChannels().collectList().toString();
                        if (channels.toLowerCase().contains("support")) {
                            ;
                            int beginindex = channels.indexOf("support");
                            String channelinfo = channels.substring(beginindex);
                            int channelidbeginindex = channelinfo.indexOf("BaseChannel{data=ChannelData{id=") + 32;
                            int channelidendindex = channelidbeginindex + 18;
                            supportchannelID = Snowflake.of(channelinfo.substring(channelidbeginindex, channelidendindex));
                            supportchannelIDstring = supportchannelID.toString();
                        } else {
                        }
                        Channel supportchannel = guild.getChannelById(supportchannelID).block();
                        String supportchannelmention = supportchannel.getMention();


                        if (messagecontent.toLowerCase().contains("!ticketcreatemessage")) {
                            if (member.getBasePermissions().block().contains(Permission.ADMINISTRATOR) && messagechannelid.equalsIgnoreCase(supportchannelIDstring)) {
                                Snowflake finalSupportchannelID = supportchannelID;
                                channel.createMessage(messageCreateSpec -> {
                                    messageCreateSpec.setContent("Tickets")
                                            .setEmbed(embedCreateSpec -> {
                                                embedCreateSpec.setTitle("**Tickets**")
                                                        .setDescription("If you wish to contact support, you can open a ticket by reacting to this message, or by emailing support@voltiac.xyz")
                                                        .setFooter("Powered by VoltiacBot", "https://cdn.discordapp.com/avatars/809487051564908576/a54b2105810f8abcfaf008a16f347514.png")
                                                        .setColor(Color.of(51, 153, 255));
                                            });
                                    String messagesbefore = channel.getMessagesBefore(Snowflake.of(Instant.now())).collectList().block().toString();
                                    int beginindex = messagesbefore.indexOf("id=") + 3;
                                    int endindex = beginindex + 18;
                                    String messageid = messagesbefore.substring(beginindex, endindex);
                                    Message ticketmessage = client.getMessageById(finalSupportchannelID, Snowflake.of(messageid)).block();
                                    ticketmessage.addReaction(ReactionEmoji.unicode("\u2709")).block();
                                }).block();
                            } else if (member.getBasePermissions().block().contains(Permission.ADMINISTRATOR) && !messagechannelid.equalsIgnoreCase(supportchannelIDstring)) {
                                channel.createMessage("Incorrect channel. This message only works in " + supportchannelmention).block();
                            } else if (!member.getBasePermissions().block().contains(Permission.ADMINISTRATOR)) {
                                channel.createMessage("You do not have permission to use that command!").block();
                            }
                        }
                    } catch (Exception e) {
                        String servername = guild.getName();
                        System.out.println("Unable to use ticket system in server: " + servername);
                    }
                });
    }
}
