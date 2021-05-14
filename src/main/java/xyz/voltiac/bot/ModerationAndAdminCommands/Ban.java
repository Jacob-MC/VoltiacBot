package xyz.voltiac.bot.ModerationAndAdminCommands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.BanQuerySpec;
import discord4j.rest.util.Color;
import discord4j.rest.util.Permission;
import xyz.voltiac.bot.Main;

public class Ban {
    public static void ban(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                    Message message = event.getMessage();
                    String messagecontent = message.getContent();
                    Member admin = message.getAuthorAsMember().block();
                    assert admin != null;
                    String avatarurl = admin.getAvatarUrl();
                    assert avatarurl != null;
                    String adminusername = admin.getUsername();
                    Guild guild = event.getGuild().block();
                    assert guild != null;
                    Snowflake guildid = guild.getId();
                    MessageChannel channel = message.getChannel().block();
                    try {
                        if (messagecontent.startsWith(Main.prefix + "ban") && message.getContent().length() > Main.prefix.length() + 25 && admin.getBasePermissions().block().contains(Permission.ADMINISTRATOR) && !admin.isBot()) {
                            int index = messagecontent.indexOf(' ') + 4;
                            String mention = messagecontent.substring(index, index + 18);
                            Snowflake id = Snowflake.of(mention);
                            User user = client.getUserById(id).block();
                            assert user != null;
                            Member member = user.asMember(guildid).block();
                            String username = user.getUsername();
                            String discriminator = user.getDiscriminator();
                            assert member != null;
                            try {
                                    if (client.getSelf().block().asMember(guildid).block().getBasePermissions().block().contains(Permission.BAN_MEMBERS)) {
                                        guild.ban(id, BanQuerySpec::asRequest).block();
                                        assert channel != null;
                                        channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("\uD83D\uDC6E \uD83D\uDD12 " + username + "#" + discriminator + " Has Been Banned!")
                                                .setColor(Color.of(51, 153, 255))
                                                .setFooter("Command Executed By: " + adminusername, avatarurl)).block();
                                        System.out.println(adminusername + " Has Banned: " + username + "#" + discriminator);
                                    } else {
                                        channel.createMessage("I do not have permission to ban users! Please give me the `BAN_MEMBERS` permission.").block();
                                    }
                                } catch (Exception e) {
                                    assert channel != null;
                                    channel.createMessage("Could not ban this user! Please check that the specified user does not have administrator permission, or a higher role than me.").block();
                                }
                            } else if (messagecontent.toLowerCase().startsWith(Main.prefix + "ban") && !admin.getBasePermissions().block().contains(Permission.ADMINISTRATOR) && !admin.isBot()) {
                            channel.createMessage("You do not have permission to use this command!").block();
                            }
                    } catch (Exception e) {
                        assert channel != null;
                        channel.createMessage("An error occured. Please check that you mentioned a valid user and that the user is in the guild.").block();
                    }
                    try {
                    if (message.getContent().toLowerCase().startsWith(Main.prefix + "ban") && message.getContent().length() <= Main.prefix.length() + 25 && admin.getBasePermissions().block().contains(Permission.ADMINISTRATOR) && !admin.isBot()) {
                        int index = messagecontent.indexOf(' ');
                        String userid = message.getContent().substring(index, index + 18);
                        User user = client.getUserById(Snowflake.of(userid)).block();
                        assert user != null;
                        String username = user.getUsername();
                        String discriminator = user.getDiscriminator();
                        Member member = user.asMember(guildid).block();
                        assert member != null;
                        if (admin.getBasePermissions().block().contains(Permission.ADMINISTRATOR)) {
                            try {
                                if (client.getSelf().block().asMember(guildid).block().getBasePermissions().block().contains(Permission.BAN_MEMBERS)) {
                                    assert channel != null;
                                    channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("\uD83D\uDC6E \uD83D\uDD12 " + username + "#" + discriminator + " Has Been Banned!")
                                            .setColor(Color.of(51, 153, 255))
                                            .setFooter("Command Executed By: " + adminusername, avatarurl)).block();
                                    System.out.println(adminusername + " Has Banned: " + username + "#" + discriminator);
                                } else {
                                    channel.createMessage("I do not have permission to ban users! Please give me the `BAN_MEMBERS` permission.").block();
                                }
                            } catch (Exception e) {
                                assert channel != null;
                                channel.createMessage("Could not ban this user! Please check that the specified user does not have administrator permission, or a higher role than me.").block();
                            }
                        } else if (messagecontent.toLowerCase().startsWith(Main.prefix + "ban") && !admin.getBasePermissions().block().contains(Permission.ADMINISTRATOR) && !admin.isBot()){
                            channel.createMessage("You do not have permission to use this command!").block();
                        }
                        }
                    } catch (Exception e) {
                        channel.createMessage("An error occured. Please check that you mentioned a valid user and that the user is in the guild.").block();
                        assert channel != null;
                    }
                    } catch (Exception e) {
                    }
                });
    }
}
