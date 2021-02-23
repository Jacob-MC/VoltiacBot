package xyz.voltiac.bot.Commands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

public class CommandHandler {
    public void CommandHandler(GatewayDiscordClient client) {
        Commands commands = new Commands();
        commands.Commands(client);
        Consoles consoles = new Consoles();
        consoles.Consoles(client);
        GetAvatar getAvatar = new GetAvatar();
        getAvatar.GetAvatar(client);
        Help help = new Help();
        help.Help(client);
        HowToJoin howToJoin = new HowToJoin();
        howToJoin.HowToJoin(client);
        Rules rules = new Rules();
        rules.Rules(client);
        ServerIP serverIP = new ServerIP();
        serverIP.ServerIP(client);
    }
}
