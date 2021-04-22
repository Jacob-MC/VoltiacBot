package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import xyz.voltiac.bot.ModerationAndAdminCommands.AdminCommands;
import xyz.voltiac.bot.ModerationAndAdminCommands.Ban;
import xyz.voltiac.bot.ModerationAndAdminCommands.Kick;
import xyz.voltiac.bot.OtherUtil.Memes;
import xyz.voltiac.bot.OtherUtil.Say;
import xyz.voltiac.bot.OtherUtil.Subreddits;

public class CommandHandler {
    public static void CommandHandler(GatewayDiscordClient client) {
        Commands.Commands(client);
        GetAvatar.GetAvatar(client);
        Help.Help(client);
        CloseTicket.CloseTicket(client);
        Say.Say(client);
        FunCommands.FunCommands(client);
        Memes.memes(client);
        Subreddits.subreddits(client);
        Ban.ban(client);
        Kick.kick(client);
        DiscordInvite.discordinvite(client);
        BotInvite.botinvite(client);
    }
}
