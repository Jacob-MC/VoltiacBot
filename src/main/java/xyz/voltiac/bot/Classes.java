package xyz.voltiac.bot;

import discord4j.core.GatewayDiscordClient;
import xyz.voltiac.bot.Commands.*;
import xyz.voltiac.bot.ModerationAndAdminCommands.*;
import xyz.voltiac.bot.OtherUtil.*;

public class Classes {
    public static void Classes(GatewayDiscordClient client) {
        CommandHandler.CommandHandler(client);
        RollCommand.RollCommand(client);
        RPSCommand.RPSCommand(client);
        SetBotStatus.SetBotStatus(client);
        TicketSetup.ticketsetup(client);
        Tickets.tickets(client);
        AdminCommands.admincommands(client);
        SetBotPrefix.setbotprefix(client);
        ForceLeave.forceleave(client);
    }
}