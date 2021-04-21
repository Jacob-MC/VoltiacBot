package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import xyz.voltiac.bot.ModerationAndAdminCommands.Ban;
import xyz.voltiac.bot.ModerationAndAdminCommands.Kick;
import xyz.voltiac.bot.OtherUtil.Memes;
import xyz.voltiac.bot.OtherUtil.Say;
import xyz.voltiac.bot.OtherUtil.Subreddits;

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
        Say.Say(client);
        FunCommands.FunCommands(client);
        McCommands.McCommands(client);
        Memes.memes(client);
        Subreddits.subreddits(client);
        Ban.ban(client);
        AdminCommands.admincommands(client);
        Kick.kick(client);
        DiscordInvite.discordinvite(client);
        BotInvite.botinvite(client);
    }
}
