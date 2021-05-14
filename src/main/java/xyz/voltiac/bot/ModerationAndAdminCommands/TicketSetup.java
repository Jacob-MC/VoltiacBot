package xyz.voltiac.bot.ModerationAndAdminCommands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.rest.util.Color;
import discord4j.rest.util.Permission;
import xyz.voltiac.bot.Main;

public class TicketSetup {
    public static void ticketsetup(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                        Message message = event.getMessage();
                        Member member = message.getAuthorAsMember().block();
                        String messagecontent = message.getContent();
                        MessageChannel channel = message.getChannel().block();
                        Message embed = null;
                        Guild guild = event.getGuild().block();
                        String username = member.getUsername();
                        if (messagecontent.equalsIgnoreCase(Main.prefix + "ticketsetup") && member.getBasePermissions().block().contains(Permission.ADMINISTRATOR) && !member.isBot()) {
                            try {
                                message.delete().block();
                            } catch (Exception e) {
                            }
                            try {
                                try {
                                    String roles = guild.getRoles().collectList().block().toString().toLowerCase();
                                    int beginIndex = roles.indexOf("staff") - 30;
                                    int endIndex = roles.indexOf("staff");
                                    String filteredrole = roles.substring(beginIndex, endIndex);
                                    int beginIndex2 = filteredrole.indexOf("id=") + 3;
                                    int endIndex2 = filteredrole.indexOf(", name=");
                                    String roleid = filteredrole.substring(beginIndex2, endIndex2);

                                    embed = channel.createEmbed(embedCreateSpec -> {
                                        embedCreateSpec.setTitle("**Ticket**")
                                                .setDescription("React to this message to create a ticket!")
                                                .setFooter("VoltiacBot v2.2", "https://cdn.discordapp.com/avatars/809487051564908576/32795fb85b74f559994f66fef8d9669b.png")
                                                .setColor(Color.of(51, 153, 255));
                                    }).block();
                                    System.out.println("TicketSetup Command Executed By: " + username);
                                    embed.addReaction(ReactionEmoji.unicode("✉")).block();
                                } catch (Exception e) {
                                    channel.createMessage("Staff role not found. Please create a role named `staff` and assign it to the users you want to be able to access all tickets.").block().delete();
                                }
                            } catch (Exception e) {
                            }
                        } else if (messagecontent.equalsIgnoreCase(Main.prefix + "ticketsetup") && !member.getBasePermissions().block().contains(Permission.ADMINISTRATOR) && !member.isBot()) {
                            channel.createMessage("You do not have permission to use this command!").block();
                        }
                    } catch (Exception e) {
                    }
                });
    }
}
