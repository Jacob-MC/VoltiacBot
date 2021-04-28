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
import xyz.voltiac.bot.Main;

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
                    if (messagecontent.toLowerCase().startsWith(Main.prefix + "kick") && message.getContent().length() > 26 && admin.getBasePermissions().block().contains(Permission.ADMINISTRATOR) && !admin.isBot()) {
                        try {
                            int index = messagecontent.indexOf(' ') + 4;
                            String mention = messagecontent.substring(index, index + 18);
                            Snowflake id = Snowflake.of(mention);
                            User user = client.getUserById(id).block();
                            assert user != null;
                            String username = user.getUsername();
                            String discriminator = user.getDiscriminator();
                            assert guild != null;
                            try {
                                if (client.getSelf().block().asMember(guild.getId()).block().getBasePermissions().block().contains(Permission.KICK_MEMBERS)) {
                                    guild.kick(id).block();
                                    assert channel != null;
                                    channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("\uD83D\uDC6E \uD83E\uDD7E " + username + "#" + discriminator + " Has Been Kicked!")
                                            .setColor(Color.of(51, 153, 255))
                                            .setFooter("Command Executed By: " + adminusername, avatarurl)).block();
                                    System.out.println(adminusername + " Has Kicked: " + username + "#" + discriminator);
                                } else {
                                    channel.createMessage("I do not have permission to kick users! Please give me the `KICK_MEMBERS` permission.").block();
                                }
                            } catch (Exception e) {
                                channel.createMessage("Could not ban this user! Please check that the specified user does not have administrator permission, or a higher role than me.").block();
                            }
                        } catch (Exception e) {
                        }
                    } else if (messagecontent.toLowerCase().startsWith(Main.prefix + "kick") && message.getContent().length() > 26 && !admin.getBasePermissions().block().contains(Permission.ADMINISTRATOR) && !admin.isBot()) {
                        channel.createMessage("You do not have permission to use this command!").block();
                    }

                    if (message.getContent().toLowerCase().startsWith(Main.prefix + "kick") && message.getContent().length() <= 26 && admin.getBasePermissions().block().contains(Permission.ADMINISTRATOR) && !admin.isBot()) {
                        try {
                            int index = messagecontent.indexOf(' ') + 1;
                            String userid = messagecontent.substring(index, index + 18);
                            User user = client.getUserById(Snowflake.of(userid)).block();
                            assert user != null;
                            String username = user.getUsername();
                            String discriminator = user.getDiscriminator();
                            assert guild != null;
                            try {
                                if (client.getSelf().block().asMember(guild.getId()).block().getBasePermissions().block().contains(Permission.KICK_MEMBERS)) {
                                    guild.kick(Snowflake.of(userid)).block();
                                    assert channel != null;
                                    channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("\uD83D\uDC6E \uD83E\uDD7E " + username + "#" + discriminator + " Has Been Kicked!")
                                            .setColor(Color.of(51, 153, 255))
                                            .setFooter("Command Executed By: " + adminusername, avatarurl)).block();
                                    System.out.println(adminusername + " Has Kicked: " + username + "#" + discriminator);
                                } else {
                                    channel.createMessage("I do not have permission to kick users! Please give me the `KICK_MEMBERS` permission.").block();
                                    }
                            } catch (Exception e) {
                                channel.createMessage("Could not ban this user! Please check that the specified user does not have administrator permission, or a higher role than me.").block();
                            }
                        } catch (Exception e) {
                        }
                    } else if (messagecontent.toLowerCase().startsWith(Main.prefix + "kick") && message.getContent().length() > 26 && !admin.getBasePermissions().block().contains(Permission.ADMINISTRATOR) && !admin.isBot()) {
                        channel.createMessage("You do not have permission to use this command!").block();
                    }
                    } catch (Exception e) {
                    }
                });
    }
}
