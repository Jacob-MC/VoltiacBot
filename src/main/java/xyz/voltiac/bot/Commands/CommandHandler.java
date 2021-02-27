package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;

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
        OpenTicket.OpenTicket(client);
    }
}
