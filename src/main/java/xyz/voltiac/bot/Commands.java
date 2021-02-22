package xyz.voltiac.bot;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

public class Commands {
    void CommandListeners(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {

                    Message Command = event.getMessage();

                    if ("!ip".equalsIgnoreCase(Command.getContent())) {
                        String username = Command.getAuthor().get().getUsername();
                        String avatar = Command.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = Command.getChannel().block();
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**Server IP**")
                                .setDescription("IP: play.voltiac.xyz\n"
                                        + "\n"
                                        + "Port: 19132 (Only needed for bedrock)")
                                .setColor(Color.of(51, 153, 255))
                                .setFooter("Command Executed By: " + username, avatar)).block();
                        System.out.println("IP Command Executed By: " + username);
                    }

                    if ("!consoles".equalsIgnoreCase(Command.getContent())) {
                        String username = Command.getAuthor().get().getUsername();
                        String avatar = Command.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = Command.getChannel().block();
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**How to join on consoles**")
                                .setDescription("If you want to join the server on console, check this link for a tutorial on whatever console you use:\n" +
                                        "https://github.com/GeyserMC/Geyser/wiki/Using-Geyser-with-Consoles\n" +
                                        "\n" +
                                        "If your on Xbox, PlayStation or Switch and you have a android phone you can download and use this app:\n" +
                                        "https://github.com/Hellohi3654/GeyserAndroid/releases/latest\n" +
                                        "\n" +
                                        "Just connect your phone to the same network as your console go to Join a bedrock server on the home menu and click start. In Minecraft on your console go to the friends tab and you should see a LAN game join it and you should be redirected to the server.\n" +
                                        "If you need any help please just create a ticket in <#801852792276254751>")
                                .setColor(Color.of(51, 153, 255))
                                .setFooter("Command Executed By: " + username, avatar)).block();
                        System.out.println("Consoles Command Executed By: " + username);
                    }

                    if ("!rules".equalsIgnoreCase(Command.getContent())) {
                        String username = Command.getAuthor().get().getUsername();
                        String avatar = Command.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = Command.getChannel().block();
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**Voltiac Server Rules**")
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
                                .setFooter("Command Executed By: " + username, avatar)).block();
                        System.out.println("Rules Command Executed By: " + username);
                    }

                    if ("!howtojoin".equalsIgnoreCase(Command.getContent())) {
                        String username = Command.getAuthor().get().getUsername();
                        String avatar = Command.getAuthor().get().getAvatarUrl();

                        MessageChannel channel = Command.getChannel().block();
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**How to join the server**")
                                .setDescription("> Mobile:\n" +
                                        "> If you are on mobile, go to the servers tab, scroll to the bottom and press `Add Server`. In the server name put `Voltiac Network`, and for the server address put `play.voltiac.xyz` and leave the default port.\n" +
                                        "\n" +
                                        "> Console:\n" +
                                        "> If you are on console, click this link:\n" +
                                        "> https://github.com/GeyserMC/Geyser/wiki/Using-Geyser-with-Consoles\n" +
                                        "> and follow the tutorial for whichever console you use. You can also use this app if you have an android to connect to the server: https://github.com/Hellohi3654/GeyserAndroid/releases/latest\n" +
                                        "\n" +
                                        "> Java:\n" +
                                        "> If you are on Java, go to multiplayer, click add server, and put `play.voltiac.xyz` in the server address.")
                                .setColor(Color.of(51, 153, 255))
                                .setFooter("Command Executed By: " + username, avatar)).block();
                        System.out.println("Howtojoin Command Executed By: " + username);
                    }

                    if ("!commands".equalsIgnoreCase(Command.getContent())) {
                        String username = Command.getAuthor().get().getUsername();
                        String avatar = Command.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = Command.getChannel().block();
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**Discord Commands**")
                                .setDescription("!ip\n" +
                                        "!consoles\n" +
                                        "!howtojoin\n" +
                                        "!rules\n" +
                                        "!help\n")
                                .setColor(Color.of(51, 153, 255))
                                .setFooter("Command Executed By: " + username, avatar)).block();
                        System.out.println("Commands Command Executed By: " + username);
                    }

                    if ("!help".equalsIgnoreCase(Command.getContent())) {
                        String username = Command.getAuthor().get().getUsername();
                        String avatar = Command.getAuthor().get().getAvatarUrl();
                        MessageChannel channel = Command.getChannel().block();

                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**Help**")
                                .setDescription("Hello, " + username + "! If you need help joining the server check <#805640788692303902>. You can view a list of commands by doing !commands")
                                .setColor(Color.of(51, 153, 255))
                                .setFooter("Command Executed By: " + username, avatar)).block();
                        System.out.println("Help Command Executed By: " + username);
                    }

                    // OTHER COMMANDS

                    if (Command.getContent().toLowerCase().contains("!avatar") && Command.getContent().length() > 26) {
                        String messagecontent = Command.getContent();
                        int index = messagecontent.indexOf(' ') + 1;
                        String mention = messagecontent.substring(index);
                        Snowflake id = Snowflake.of(mention.substring(3, 21));
                        User user = client.getUserById(id).block();
                        String username = user.getUsername();
                        String avatarurl = user.getAvatarUrl();
                        MessageChannel channel = Command.getChannel().block();
                        channel.createEmbed(embedCreateSpec -> {
                           embedCreateSpec.setTitle(username + "'s Avatar")
                                   .setColor(Color.of(51, 153, 255))
                           .setImage(avatarurl);
                        }).block();
                    }


                        if (Command.getContent().toLowerCase().contains("!avatar") && Command.getContent().length() <= 26) {
                        String userid = Command.getContent().substring(8);
                        User user = client.getUserById(Snowflake.of(userid)).block();
                        String username = user.getUsername();
                        String avatarurl = user.getAvatarUrl();
                        MessageChannel channel = Command.getChannel().block();
                        channel.createEmbed(embedCreateSpec -> {
                            embedCreateSpec.setTitle(username + "'s Avatar")
                                    .setColor(Color.of(51, 153, 255))
                                    .setImage(avatarurl);
                        }).block();
                    }
                });
    }
}
