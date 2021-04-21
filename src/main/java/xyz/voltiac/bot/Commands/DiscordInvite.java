package xyz.voltiac.bot.Commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;

public class DiscordInvite {
    public static void discordinvite(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                   Message message = event.getMessage();
                   String messagecontent = message.getContent();
                   Member member = message.getAuthorAsMember().block();
                   String username = member.getUsername();
                    assert username != null;
                   String avatarurl = member.getAvatarUrl();
                    MessageChannel channel = (MessageChannel) message.getChannel().block();
                   if (messagecontent.startsWith("!discordinvite")) {
                       channel.createEmbed(EmbedCreateSpec -> {
                            EmbedCreateSpec.setTitle("**﹂〈Voltiac Network〉﹁**")
                                    .setDescription(
                                            "Voltiac Network is a Minecraft server network that aims to bring the Java experience to Bedrock\n" +
                                            "\n" +
                                            "✯ **New Features:** ✯\n" +
                                            "⍟》`New Gamemodes!`\n" +
                                            "     ⛰️  *CaveBlock:* Skyblock but you're surrounded by caves and stone!\n" +
                                            "\n" +
                                            "     \uD83E\uDD16  *Practice Bots!!!:* Coming Soon 1v1 and KitPVP **Bots** to increase your skill on!\n" +
                                            "\n" +
                                            "✯ **ꜱᴏᴍᴇ ᴏꜰ ᴏᴜʀ ᴄᴏᴏʟ ꜰᴇᴀᴛᴜʀᴇꜱ:** ✯\n" +
                                            "⍟》`Other Gamemodes`\n" +
                                            "     \uD83C\uDF0E  *SMP / Survival:* Our SMP Server offers an experience like no other -\n" +
                                            "         • Custom Terrain Generation, ShopGUI+, Morph plugin, Quests, Crates, Stock Market and Much More!\n" +
                                            "\n" +
                                            "     \uD83C\uDFDD️  *Skyblock:* RPG plugin, CaveBlock, Ecomony, Shops and More!\n" +
                                            "     \n" +
                                            "     ⚔️  *PVP Server:* KitPVP with Bots, Bot 1v1 and normal 1v1, Skywars, Bedwars, Spleef and More! **Coming Soon**\n" +
                                            "\n" +
                                            "     \uD83C\uDF0F *Semi Vanilla:* Semi Vanilla Server with morph plugin, no economy, default world generation!\n" +
                                            "         \n" +
                                            "⍟》`Java and Bedrock Crossplay`\n" +
                                            "     ⚒️  Using a Custom Fork of GeyserMC (Java Crawling / Sneaking, World Border, More Movement Fixes)\n" +
                                            "     \n" +
                                            "⍟》`Console Player Support` \n" +
                                            "     \uD83C\uDFAE  Just join our server and type the command `!consoles`\n" +
                                            "\n" +
                                            "➡️ **----------** ⬅️ \n" +
                                            "\n" +
                                            "✯ Brief Overview ✯\n" +
                                            " ▶️  *Skyblock, Caveblock, KitPVP, Skywars, Bedwars, 1v1s, SMP, and Semi Vanilla*\n" +
                                            " ▶️  *All Gamemodes have unique features*\n" +
                                            " \n" +
                                            "⍟》IP: `play.voltiac.xyz`\n" +
                                            "\n" +
                                            "⍟》Port: `19132`\n" +
                                            "\n" +
                                            "⍟》on Java just enter it as `play.voltiac.xyz`\n" +
                                            "\n" +
                                            "➥ New Website: https://voltiac.xyz\n" +
                                            "➥ https://discord.gg/mmpUHnG\n" +
                                            "➥ https://cdn.discordapp.com/attachments/758028300688949330/806299713191280640/standard-1.gif")
                                    .setFooter("Command Executed By: " + username, avatarurl)
                                    .setColor(Color.of(51, 153, 255));
                       }).block();
                       message.delete().block();
                   }
                    } catch (Exception e) {
                    }
                });
    }
}
