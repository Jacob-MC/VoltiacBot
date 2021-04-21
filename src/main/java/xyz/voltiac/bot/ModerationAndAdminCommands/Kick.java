package xyz.voltiac.bot.ModerationAndAdminCommands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

public class Kick {
    public static void kick(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                    Message message = event.getMessage();
                    String messagecontent = message.getContent();
                    User admin = message.getAuthor().get();
                    assert admin != null;
                    String avatarurl = admin.getAvatarUrl();
                    String adminusername = admin.getUsername();
                    Guild guild = event.getGuild().block();
                    MessageChannel channel = message.getChannel().block();
                    if (messagecontent.startsWith("!kick") && message.getContent().length() > 26) {
                        try {
                            int index = messagecontent.indexOf(' ') + 1;
                            String mention = messagecontent.substring(index);
                            Snowflake id = Snowflake.of(mention.substring(3, 21));
                            User user = client.getUserById(id).block();
                            assert user != null;
                            String username = user.getUsername();
                            String discriminator = user.getDiscriminator();
                            assert guild != null;
                            guild.kick(id).block();
                            message.delete().block();
                            assert channel != null;
                            channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("\uD83D\uDC6E \uD83E\uDD7E " + username + "#" + discriminator + " Has Been Kicked!")
                                    .setColor(Color.of(51, 153, 255))
                                    .setFooter("Command Executed By: " + adminusername, avatarurl)).block();
                            System.out.println(adminusername + " Has Kicked: " + username + "#" + discriminator);
                        } catch (Exception e) {
                            assert channel != null;
                            channel.createMessage("An error occured. Please check that you have the correct permissions and/or the user is in the server, and that the bot has the correct permissions").block();
                        }
                    }

                    if (message.getContent().toLowerCase().startsWith("!kick") && message.getContent().length() <= 26) {
                        try {
                            String userid = message.getContent().substring(6);
                            User user = client.getUserById(Snowflake.of(userid)).block();
                            assert user != null;
                            String username = user.getUsername();
                            String discriminator = user.getDiscriminator();
                            assert guild != null;
                            guild.kick(Snowflake.of(userid)).block();
                            message.delete().block();
                            assert channel != null;
                            channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("\uD83D\uDC6E \uD83E\uDD7E " + username + "#" + discriminator + " Has Been Kicked!")
                                    .setColor(Color.of(51, 153, 255))
                                    .setFooter("Command Executed By: " + adminusername, avatarurl)).block();
                            System.out.println(adminusername + " Has Kicked: " + username + "#" + discriminator);
                        } catch (Exception e) {
                            assert channel != null;
                            channel.createMessage("An error occured. Please check that you have the correct permissions and/or the user is in the server, and that the bot has the correct permissions").block();
                        }
                    }
                    } catch (Exception e) {
                    }
                });
    }
}
