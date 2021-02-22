package xyz.voltiac.bot;

import discord4j.core.GatewayDiscordClient;

public class Classes {
    void Classes(GatewayDiscordClient client) {
        Commands commands = new Commands();
        commands.CommandListeners(client);
        ModerationCommands moderationCommands = new ModerationCommands();
        moderationCommands.ModCommandListeners(client);
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
