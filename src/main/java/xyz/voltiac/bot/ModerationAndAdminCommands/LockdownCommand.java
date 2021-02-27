package xyz.voltiac.bot.ModerationAndAdminCommands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

public class LockdownCommand {
    public static void LockdownCommand(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    Message Command = event.getMessage();
                    if ("!lockdown".equalsIgnoreCase(Command.getContent())) {
                        String username = Command.getAuthor().get().getUsername();
                        String avatar = Command.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = Command.getChannel().block();
                        Guild guild = (Guild) event.getGuild().block();
                        System.out.println("Lockdown Start Command Executed By: " + username);

                    }
                    if ("!lockdown end".equalsIgnoreCase(Command.getContent())) {
                        String username = Command.getAuthor().get().getUsername();
                        String avatar = Command.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = Command.getChannel().block();

                        System.out.println("Lockdown End Command Executed By: " + username);
                    }
                });
    }
}
