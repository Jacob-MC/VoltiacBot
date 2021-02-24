package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

public class Rules {
    public static void Rules(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                   Message message = event.getMessage();
                    if ("!rules".equalsIgnoreCase(message.getContent())) {
                        String username = message.getAuthor().get().getUsername();
                        String avatar = message.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = message.getChannel().block();
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle("**Voltiac Server Rules**")
                                    .setDescription("[1] Follow Discord TOS (Ex: Must be over 13 years old)\n" +
                                            "[2] Do not discuss illegal activities\n" +
                                            "[3] Use common sense\n" +
                                            "[4] Please have a name that is SFW and is pingable, any nickname with special characters that cannot be typed on a normal keyboard will be changed\n" +
                                            "[5] Be polite towards others\n" +
                                            "[6] No toxicity, disrespect, harrasment, discrimination, suicide encouragment, racism, sexism, slurs, or homophobia\n" +
                                            "[7] No NSFW\n" +
                                            "[8] No Swearing\n" +
                                            "[9] No DDoS, doxxing, or death threats\n" +
                                            "[10] No spamming\n" +
                                            "[11] No ban/mute evasion\n" +
                                            "[12] Only advertise in <#808838745541050399>\n" +
                                            "[13] Do not advertise the buying or selling of services\n" +
                                            "[14] Do not come here only to advertise your social medias, discord servers, or minecraft servers\n" +
                                            "[15] No DM advertising\n" +
                                            "[16] Post in the correct channels\n" +
                                            "[17] Do not try to bypass the chat filter\n" +
                                            "[18] Do not mass ping users or staff\n" +
                                            "[19] Do not impersonate others\n" +
                                            "[20] Do not DM staff with questions, if you have a question, open a ticket\n" +
                                            "[21] Do not argue with others\n" +
                                            "[22] Do not try to push the rules\n" +
                                            "[23] Staff have the say, if you think a punishment was unfair, you can DM the Owner or Co-Owner\n" +
                                            "\n" +
                                            "**Minecraft Server Rules:**\n" +
                                            "[1] No Hacking or Cheating (This includes xray)\n" +
                                            "[2] No Griefing\n" +
                                            "[2] No Stealing\n" +
                                            "[4] No killing other players unless agreed upon or on KitPVP\n" +
                                            "[5] Be respectful\n" +
                                            "[6] Be ethical\n" +
                                            "[7] Use common sense\n" +
                                            "[8] Have fun!\n" +
                                            "\n" +
                                            "**Server IP:**\n" +
                                            "play.voltiac.xyz\n" +
                                            "\n" +
                                            "**Port:**\n" +
                                            "19132\n" +
                                            "\n" +
                                            "On java just enter the IP:\n" +
                                            "play.voltiac.xyz\n" +
                                            "\n" +
                                            "If your on console and would like to join just do !console in general chat.\n" +
                                            "\n" +
                                            "Official Invite: https://discord.gg/mmpUHnG\n" +
                                            "\n" +
                                            "You can find the Map, Store and other links on our\n" +
                                            "Server Website: https://voltiac.xyz/")
                                    .setColor(Color.of(51, 153, 255))
                                    .setFooter("Command Executed By: " + username, avatar);
                        }).block();
                        System.out.println("Rules Command Executed By: " + username);
                    }
                });
    }
}
