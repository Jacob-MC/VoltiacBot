package xyz.voltiac.bot;

import discord4j.core.GatewayDiscordClient;
import xyz.voltiac.bot.Commands.*;
import xyz.voltiac.bot.ModerationAndAdminCommands.*;
import xyz.voltiac.bot.OtherUtil.*;
public class Classes {
    public static void Classes(GatewayDiscordClient client) {
        CommandHandler.CommandHandler(client);
        LockdownCommand.LockdownCommand(client);
        Tickets.TicketListeners(client);
        LinkBlacklist.LinkBlacklist(client);
        MessageLogs.MessageLogs(client);
        ReactionRoles.ReactionRoles(client);
        WelcomeMessages.WelcomeMessages(client);
        RollCommand.RollCommand(client);
        RPSCommand.RPSCommand(client);
        SetBotStatus.SetBotStatus(client);
    }
}