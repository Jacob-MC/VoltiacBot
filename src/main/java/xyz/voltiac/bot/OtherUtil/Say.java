package xyz.voltiac.bot.OtherUtil;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import xyz.voltiac.bot.Main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Say {
    public static void Say(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                   Message message = event.getMessage();
                   String messagecontent = message.getContent();
                    MessageChannel channel = (MessageChannel) message.getChannel().block();
                   Member member = message.getAuthorAsMember().block();
                   String username = member.getUsername();
                   String mention = member.getMention();
                   assert username != null;
                   String avatarurl = member.getAvatarUrl();
                        int index = messagecontent.indexOf(' ') + 1;
                        String content = messagecontent.substring(index);
                        Pattern p = Pattern.compile("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)");
                        Pattern p2 = Pattern.compile("(discord\\.(gg|io|me|li)|discordapp\\.com\\/invite)\\/.+[a-z]");
                        Matcher m2 = p2.matcher(content);
                        Matcher m = p.matcher(content);
                   if (messagecontent.equalsIgnoreCase(Main.prefix + "say")) {
                       channel.createEmbed(EmbedCreateSpec -> {
                           EmbedCreateSpec.setTitle("**!say**")
                                   .setDescription("Repeats whatever you say")
                                   .addField("**Usage**:", "!say (message)", false)
                                   .addField("**Example:**", "!say hello", false)
                                   .setFooter("Command Executed By: " + username, avatarurl)
                                   .setColor(Color.of(51, 153, 255));
                       }).block();
                       System.out.println("Say Command Executed By: " + username);
                   }
                   if (messagecontent.toLowerCase().startsWith(Main.prefix + "say") && messagecontent.length() > 4 && !messagecontent.toLowerCase().contains("@") && !m.find() && !m2.find() && !member.isBot()) {
                       channel.createMessage(content).block();
                       System.out.println("Say Command Executed By: " + username);
                   } else if (messagecontent.toLowerCase().startsWith(Main.prefix + "say") && !member.isBot()) {
                       if (messagecontent.toLowerCase().contains("@") || !m2.find() || !m.find()) {
                           channel.createMessage(mention + ", Please do not try pinging people/roles or posting links, it will not work.").block();
                       }

                       }
                    } catch (Exception e) {
                    }
                });
    }
}
