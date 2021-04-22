package xyz.voltiac.bot.ModerationAndAdminCommands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import discord4j.rest.util.Permission;

public class Kick {
    public static void kick(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                    Message message = event.getMessage();
                    String messagecontent = message.getContent();
                    Member admin = message.getAuthorAsMember().block();
                    assert admin != null;
                    String avatarurl = admin.getAvatarUrl();
                    String adminusername = admin.getUsername();
                    Guild guild = event.getGuild().block();
                    MessageChannel channel = message.getChannel().block();
                    if (messagecontent.startsWith("!kick") && message.getContent().length() > 26 && admin.getBasePermissions().block().contains(Permission.ADMINISTRATOR)) {
                        try {
                            int index = messagecontent.indexOf(' ') + 1;
                            String mention = messagecontent.substring(index);
                            Snowflake id = Snowflake.of(mention.substring(3, 21));
                            User user = client.getUserById(id).block();
                            assert user != null;
                            String username = user.getUsername();
                            String discriminator = user.getDiscriminator();
                            assert guild != null;
                            try {
                                guild.kick(id).block();
                                assert channel != null;
                                channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("\uD83D\uDC6E \uD83E\uDD7E " + username + "#" + discriminator + " Has Been Kicked!")
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Command Executed By: " + adminusername, avatarurl)).block();
                                System.out.println(adminusername + " Has Kicked: " + username + "#" + discriminator);
                            } catch (Exception e) {
                                channel.createMessage("I do not have permission to kick users! Please give me the `KICK_MEMBERS` permission.").block();
                            }
                        } catch (Exception e) {
                        }
                    } else if (messagecontent.startsWith("!kick") && message.getContent().length() > 26 && !admin.getBasePermissions().block().contains(Permission.ADMINISTRATOR)) {
                        channel.createMessage("You do not have permission to use this command!").block();
                    }

                    if (message.getContent().toLowerCase().startsWith("!kick") && message.getContent().length() <= 26 && admin.getBasePermissions().block().contains(Permission.ADMINISTRATOR)) {
                        try {
                            String userid = message.getContent().substring(6);
                            User user = client.getUserById(Snowflake.of(userid)).block();
                            assert user != null;
                            String username = user.getUsername();
                            String discriminator = user.getDiscriminator();
                            assert guild != null;
                            try {
                                guild.kick(Snowflake.of(userid)).block();
                                assert channel != null;
                                channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("\uD83D\uDC6E \uD83E\uDD7E " + username + "#" + discriminator + " Has Been Kicked!")
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Command Executed By: " + adminusername, avatarurl)).block();
                                System.out.println(adminusername + " Has Kicked: " + username + "#" + discriminator);
                            } catch (Exception e) {
                                channel.createMessage("I do not have permission to kick users! Please give me the `KICK_MEMBERS` permission.").block();
                            }
                        } catch (Exception e) {
                        }
                    } else if (messagecontent.startsWith("!kick") && message.getContent().length() > 26 && !admin.getBasePermissions().block().contains(Permission.ADMINISTRATOR)) {
                        channel.createMessage("You do not have permission to use this command!").block();
                    }
                    } catch (Exception e) {
                    }
                });
    }
}
