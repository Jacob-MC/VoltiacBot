package xyz.voltiac.bot.OtherUtil;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

public class Say {
    public static void Say(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                   Message message = event.getMessage();
                   String messagecontent = message.getContent();
                    int index = messagecontent.indexOf(' ') + 1;
                    String content = messagecontent.substring(index);
                    MessageChannel channel = (MessageChannel) message.getChannel().block();
                   Member member = event.getMember().get();
                   if (messagecontent.startsWith("!say") && !member.isBot()) {
                       channel.createMessage(content).block();
                   }
                });
    }
}
