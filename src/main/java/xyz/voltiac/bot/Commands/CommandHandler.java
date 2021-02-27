package xyz.voltiac.bot.Commands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

public class CommandHandler {
    public static void CommandHandler(GatewayDiscordClient client) {
        Commands.Commands(client);
        Consoles.Consoles(client);
        GetAvatar.GetAvatar(client);
        Help.Help(client);
        HowToJoin.HowToJoin(client);
        Rules.Rules(client);
        ServerIP.ServerIP(client);
        CloseTicket.CloseTicket(client);
    }
}
