package xyz.voltiac.bot.Commands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

import java.util.Optional;

public class GetAvatar {
    public static void GetAvatar(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                   Message message = event.getMessage();
                   MessageChannel channel = message.getChannel().block();
                   User messageuser = message.getAuthor().get();
                   String messageusername = messageuser.getUsername();
                   String messageavatar = messageuser.getAvatarUrl();
                    if (message.getContent().equalsIgnoreCase("!avatar") || message.getContent().equalsIgnoreCase("!avatar ")) {
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**!avatar**")
                                    .setDescription("Get a user's avatar!")
                                    .addField("**Usage:**", "!avatar (mention user or enter their ID)", false)
                                    .addField("Example:", "!avatar <@!778742764908183612>", false)
                                    .addField("Example:", "!avatar 778742764908183612", false)
                                    .setFooter("Command Executed By: " + messageusername, messageavatar)
                                    .setColor(Color.of(51, 153, 255));
                        }).block();
                    }
                    if (message.getContent().contains("!avatar") && message.getContent().length() > 26) {
                        String messagecontent = message.getContent();
                        int index = messagecontent.indexOf(' ') + 1;
                        String mention = messagecontent.substring(index);
                        Snowflake id = Snowflake.of(mention.substring(3, 21));
                        User user = client.getUserById(id).block();
                        String username = user.getUsername();
                        String avatarurl = user.getAvatarUrl();
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle(username + "'s Avatar")
                                    .setColor(Color.of(51, 153, 255))
                                    .setImage(avatarurl)
                                    .setFooter("Command Executed By: " + messageusername, messageavatar);
                        }).block();
                    }

                    if (message.getContent().toLowerCase().contains("!avatar") && message.getContent().length() <= 26) {
                        String userid = message.getContent().substring(8);
                        User user = client.getUserById(Snowflake.of(userid)).block();
                        String username = user.getUsername();
                        String avatarurl = user.getAvatarUrl();
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle(username + "'s Avatar")
                                    .setColor(Color.of(51, 153, 255))
                                    .setImage(avatarurl)
                                    .setFooter("Command Executed By: " + messageusername, messageavatar);
                        }).block();
                    }


                });
    }
}
