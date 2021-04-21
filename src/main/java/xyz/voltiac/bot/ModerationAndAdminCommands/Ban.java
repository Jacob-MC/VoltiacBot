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

import java.util.Objects;

public class Ban {
    public static void ban(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                    Message message = event.getMessage();
                    String messagecontent = message.getContent();
                    User admin = message.getAuthorAsMember().block();
                    assert admin != null;
                    String avatarurl = admin.getAvatarUrl();
                    assert avatarurl != null;
                    String adminusername = admin.getUsername();
                    Guild guild = event.getGuild().block();
                    assert guild != null;
                    Snowflake guildid = guild.getId();
                    MessageChannel channel = message.getChannel().block();
                    try {
                        if (messagecontent.startsWith("!ban") && message.getContent().length() > 26) {
                            int index = messagecontent.indexOf(' ') + 1;
                            String mention = messagecontent.substring(index);
                            Snowflake id = Snowflake.of(mention.substring(3, 21));
                            User user = client.getUserById(id).block();
                            assert user != null;
                            Member member = user.asMember(guildid).block();
                            String username = user.getUsername();
                            String discriminator = user.getDiscriminator();
                            assert member != null;
                            if (Objects.requireNonNull(member.getBasePermissions().block()).contains(Permission.ADMINISTRATOR)) {
                                try {
                                    guild.ban(id, BanQuerySpec::asRequest).block();
                                    message.delete().block();
                                    assert channel != null;
                                    channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("\uD83D\uDC6E \uD83D\uDD12 " + username + "#" + discriminator + " Has Been Banned!")
                                            .setColor(Color.of(51, 153, 255))
                                            .setFooter("Command Executed By: " + adminusername, avatarurl)).block();
                                    System.out.println(adminusername + " Has Banned: " + username + "#" + discriminator);
                                } catch (Exception e) {
                                    assert channel != null;
                                    channel.createMessage("An error occured. Please check that you have the correct permissions and/or the user is in the server, and that the bot has the correct permissions").block();
                                }
                            } else {
                                assert channel != null;
                                channel.createMessage("You do not have permission to user this command!").block();
                            }
                        }
                    } catch (Exception e) {
                        assert channel != null;
                        channel.createMessage("An error occured. Please check that you have the correct permissions and/or the user is in the server, and that the bot has the correct permissions").block();
                    }
                    try {
                    if (message.getContent().toLowerCase().startsWith("!ban") && message.getContent().length() <= 26) {
                        String userid = message.getContent().substring(5);
                        User user = client.getUserById(Snowflake.of(userid)).block();
                        assert user != null;
                        String username = user.getUsername();
                        String discriminator = user.getDiscriminator();
                        Member member = user.asMember(guildid).block();
                        assert member != null;
                        if (Objects.requireNonNull(member.getBasePermissions().block()).contains(Permission.ADMINISTRATOR)) {
                            try {
                                guild.ban(Snowflake.of(userid), BanQuerySpec::asRequest).block();
                                message.delete().block();
                                assert channel != null;
                                channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("\uD83D\uDC6E \uD83D\uDD12 " + username + "#" + discriminator + " Has Been Banned!")
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Command Executed By: " + adminusername, avatarurl)).block();
                                System.out.println(adminusername + " Has Banned: " + username + "#" + discriminator);
                            } catch (Exception e) {
                                assert channel != null;
                                channel.createMessage("An error occured. Please check that you have the correct permissions and/or the user is in the server, and that the bot has the correct permissions").block();
                            }
                        } else {
                            assert channel != null;
                            channel.createMessage("You do not have permission to user this command!").block();
                        }
                        }
                    } catch (Exception e) {
                        assert channel != null;
                        channel.createMessage("An error occured. Please check that you have the correct permissions and/or the user is in the server, and that the bot has the correct permissions").block();
                    }
                    } catch (Exception e) {
                    }
                });
    }
}
