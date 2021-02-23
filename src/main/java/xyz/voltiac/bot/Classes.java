package xyz.voltiac.bot;

import discord4j.core.GatewayDiscordClient;
import xyz.voltiac.bot.Commands.*;
import xyz.voltiac.bot.Moderation.*;
import xyz.voltiac.bot.OtherUtil.*;
public class Classes {
    public static void Classes(GatewayDiscordClient client) {
        CommandHandler commands = new CommandHandler();
        commands.CommandHandler(client);
        LockdownCommand lockdownCommand = new LockdownCommand();
        LockdownCommand.LockdownCommand(client);
        Tickets tickets = new Tickets();
        tickets.TicketListeners(client);
        LinkBlacklist linkBlacklist = new LinkBlacklist();
        linkBlacklist.LinkBlacklist(client);
        MessageLogs messageLogs = new MessageLogs();
        messageLogs.MessageLogs(client);
        ReactionRoles reactionRoles = new ReactionRoles();
        reactionRoles.ReactionRoles(client);
        WelcomeMessages welcomeMessages = new WelcomeMessages();
        welcomeMessages.WelcomeMessages(client);
    }
}