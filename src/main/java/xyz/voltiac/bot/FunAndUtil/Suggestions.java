package xyz.voltiac.bot.FunAndUtil;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

import java.time.Instant;

public class Suggestions {
    public static void Suggestions(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                    Message m = event.getMessage();
                    MessageChannel c = (MessageChannel) m.getChannel().block();
                    String channelid = c.getId().toString();
                    User user = m.getAuthor().get();
                    String username = user.getUsername();
                    String discriminator = user.getDiscriminator();
                    String avatarurl = user.getAvatarUrl();
                    String messagecontent = m.getContent();
                    Guild guild = m.getGuild().block();
                    Instant instant = Instant.now();
                    String ID = null;
                    String channels = guild.getChannels().collectList().toString();
                    if (channels.toLowerCase().contains("suggest")) {
                        int beginindex = channels.indexOf("suggest");
                        String channelinfo = channels.substring(beginindex);
                        int channelidbeginindex = channelinfo.indexOf("BaseChannel{data=ChannelData{id=") + 32;
                        int channelidendindex = channelidbeginindex + 18;
                        ID = channelinfo.substring(channelidbeginindex, channelidendindex);
                        MessageChannel suggestionchannel = (MessageChannel) guild.getChannelById(Snowflake.of(ID)).block();
                        System.out.println(ID);
                        suggestionchannel.createEmbed(EmbedCreateSpec -> {
                            EmbedCreateSpec.setTitle("**Suggestion**")
                                    .setDescription(messagecontent)
                                    .setFooter("Suggested by: " + username + "#" + discriminator, avatarurl)
                                    .setColor(Color.of(51, 153, 255));
                        }).block();
                    }
                    } catch (Exception e) {
                        System.out.println("ERROR:");
                        e.printStackTrace();
                    }
                });
    }
}
