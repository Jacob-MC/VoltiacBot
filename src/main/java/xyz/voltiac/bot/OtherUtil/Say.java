package xyz.voltiac.bot.OtherUtil;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;

public class Say {
    public static void Say(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                   Message message = event.getMessage();
                   String messagecontent = message.getContent();
                    MessageChannel channel = (MessageChannel) message.getChannel().block();
                   Member member = message.getAuthorAsMember().block();
                   String username = member.getUsername();
                   String avatarurl = member.getAvatarUrl();
                   if (messagecontent.equalsIgnoreCase("!say")) {
                       channel.createEmbed(EmbedCreateSpec -> {
                           EmbedCreateSpec.setTitle("**!say**")
                                   .setDescription("Repeats whatever you say")
                                   .addField("**Usage**:", "!say (message)", false)
                                   .addField("**Example:**", "!say hello", false)
                                   .setFooter("Command Executed By: " + username, avatarurl)
                                   .setColor(Color.of(51, 153, 255));
                       }).block();
                   }
                   if (messagecontent.startsWith("!say") && messagecontent.length() > 4 && !messagecontent.toLowerCase().contains("@everyone") && !messagecontent.toLowerCase().contains("@here") && !member.isBot()) {
                       int index = messagecontent.indexOf(' ') + 1;
                       String content = messagecontent.substring(index);
                       channel.createMessage(content).block();
                   } else if (messagecontent.toLowerCase().contains("@here") || messagecontent.toLowerCase().contains("@everyone")) {
                       channel.createMessage("Please do not try pinging everyone or here, it will not work.").block();
                   }
                });
    }
}
