package xyz.voltiac.bot.ModerationAndAdminCommands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.ClientPresence;
import discord4j.rest.util.Color;
import xyz.voltiac.bot.Main;

public class SetBotPrefix {

    public static void setbotprefix(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                        Message message = event.getMessage();
                        Member member = message.getAuthorAsMember().block();
                        String messagecontent = message.getContent();
                        Snowflake userid = member.getId();
                        String username = member.getUsername();
                        String avatar = member.getAvatarUrl();
                        MessageChannel channel = message.getChannel().block();
                        if (messagecontent.equalsIgnoreCase(Main.prefix + "setbotprefix") && userid.equals(Snowflake.of(778742764908183612L))) {
                            channel.createEmbed(embedCreateSpec -> {
                                embedCreateSpec.setTitle("**" + Main.prefix + "setbotprefix**")
                                        .setDescription("Sets the bots preifx!")
                                        .addField("**Usage:**", Main.prefix + "setbotprefix (prefix)", false)
                                        .addField("Example:", Main.prefix + "setbotprefix !", false)
                                        .setFooter("Command Executed By: " + username, avatar)
                                        .setColor(Color.of(51, 153, 255));
                            }).block();
                        } else if (messagecontent.toLowerCase().startsWith(Main.prefix + "setbotprefix ") && userid.equals(Snowflake.of(778742764908183612L))) {
                            int index = messagecontent.indexOf(" ") + 1;
                            String botprefix = messagecontent.substring(index);
                            Main.prefix = botprefix;
                            client.updatePresence(ClientPresence.online(ClientActivity.playing(botprefix + "help | " + client.getGuilds().collectList().block().size() + " Guilds"))).block();
                            channel.createMessage("Bot prefix updated.").block();
                            System.out.println("Bot prefix set to: " + botprefix);
                        }
                    } catch (Exception e) {
                    }
                });
    }
}
