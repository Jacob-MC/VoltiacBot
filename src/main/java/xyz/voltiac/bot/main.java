package xyz.voltiac.bot;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.guild.MemberJoinEvent;
import discord4j.core.event.domain.guild.MemberLeaveEvent;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.event.domain.message.ReactionRemoveEvent;
import discord4j.core.object.PermissionOverwrite;
import discord4j.core.object.entity.*;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.rest.util.Color;
import discord4j.rest.util.PermissionSet;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import static discord4j.rest.util.Permission.*;

public class main {
    public static void main(String[] args) {
        GatewayDiscordClient client = DiscordClientBuilder.create("token here")
                .build()
                .login()
                .block();

        assert client != null;
        client.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(event -> {
                    final User self = event.getSelf();
                    System.out.printf(
                            "Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator()
                   );
                    final Message message = client.getMessageById(Snowflake.of(808838744609652784L), Snowflake.of(809278160704897034L)).block();
                    assert message != null;
                    message.addReaction(ReactionEmoji.unicode("\u2705")).block();
                });

        // COMMANDS

        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    final Message m = event.getMessage();
                    if ("!ip".equalsIgnoreCase(m.getContent())) {
                        final String username = m.getAuthor().get().getUsername();
                        final String avatar = m.getAuthor().get().getAvatarUrl();
                        final MessageChannel channel = m.getChannel().block();
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**Server IP**")
                                .setDescription("IP: play.voltiac.xyz\n"
                                        + "\n"
                                        + "Port: 19132 (Only needed for bedrock)")
                                .setColor(Color.of(51, 153, 255))
                                .setFooter("Command Executed By: " + username, avatar)).block();
                        System.out.println("IP Command Executed By: " + username);
                    }

                    if ("!consoles".equalsIgnoreCase(m.getContent())) {
                        final String username = m.getAuthor().get().getUsername();
                        final String avatar = m.getAuthor().get().getAvatarUrl();
                        final MessageChannel channel = m.getChannel().block();
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
        if ("!rules".equalsIgnoreCase(m.getContent())) {
            final String username = m.getAuthor().get().getUsername();
            final String avatar = m.getAuthor().get().getAvatarUrl();
            final MessageChannel channel = m.getChannel().block();
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
                            "[23] Staff have the final say, if you think a punishment was unfair, you can DM the Owner or Co-Owner\n" +
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
        if ("!howtojoin".equalsIgnoreCase(m.getContent())) {
            final String username = m.getAuthor().get().getUsername();
            final String avatar = m.getAuthor().get().getAvatarUrl();

            final MessageChannel channel = m.getChannel().block();
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

        if ("!commands".equalsIgnoreCase(m.getContent())) {
            final String username = m.getAuthor().get().getUsername();
            final String avatar = m.getAuthor().get().getAvatarUrl();
            final MessageChannel channel = m.getChannel().block();
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
        if ("!help".equalsIgnoreCase(m.getContent())) {
            final String username = m.getAuthor().get().getUsername();
            final String avatar = m.getAuthor().get().getAvatarUrl();
            final MessageChannel channel = m.getChannel().block();

            assert channel != null;
            channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**Help**")
                    .setDescription("Hello, " + username + "! If you need help joining the server check <#805640788692303902>. You can view a list of commands by doing !commands")
                    .setColor(Color.of(51, 153, 255))
                    .setFooter("Command Executed By: " + username, avatar)).block();
            System.out.println("Help Command Executed By: " + username);
        }
        if ("!lockdown".equalsIgnoreCase(m.getContent())) {
            final String username = m.getAuthor().get().getUsername();
            final String avatar = m.getAuthor().get().getAvatarUrl();
            final MessageChannel channel = m.getChannel().block();

            System.out.println("Lockdown Start Command Executed By: " + username);
        }
        if ("!lockdown end".equalsIgnoreCase(m.getContent())) {
            final String username = m.getAuthor().get().getUsername();
            final String avatar = m.getAuthor().get().getAvatarUrl();
            final MessageChannel channel = m.getChannel().block();

            System.out.println("Lockdown End Command Executed By: " + username);
        }
    });

        // LOCKDOWN COMMAND STARTED
        // ABOVE ^^^

        // TICKETS (STILL NOT DONE CURRENTLY DOING)

        client.getEventDispatcher().on(ReactionAddEvent.class)
                .subscribe(event -> {
                    final Snowflake id = event.getMessageId();
                    final ReactionEmoji reactionemoji = event.getEmoji();
                    final Message message = event.getMessage().block();
                    final Member m = event.getMember().get();
                    final String user = m.getUsername();
                    final Member member = client.getMemberById(Snowflake.of(808838744203198503L), Snowflake.of(809487051564908576L)).block();
                    assert member != null;
                    final String botpfp = member.getAvatarUrl();
                    final Guild guild1 = client.getGuildById(Snowflake.of(808838744203198503L)).block();
                    assert guild1 != null;
                    final Role staffrole = guild1.getRoleById(Snowflake.of(808838744227446789L)).block();
                    assert staffrole != null;
                    final String staffrolemention = staffrole.getMention();
                    final String mention = m.getMention();
                    final String name = m.getUsername();
                    final String disc = m.getDiscriminator();
                    final Guild guild = m.getGuild().block();
                    final Snowflake userid = m.getId();
                    final long messageid = 809278160704897034L;
                    final long blacklistedrole = 809243022172356649L;
                    final long staffrole1 = 808838744227446789L;

                    assert message != null;
                    final Void removereaction = message.removeReaction(reactionemoji, userid).block();

                    if (id.asLong() == messageid){
                        final Snowflake r = event.getUserId();
                        if (r.asLong() == r.asLong()) {
                            final TextChannel g;
                            final Role role1 = m.getHighestRole().block();
                            assert role1 != null;
                            final Integer role = role1.getRawPosition();
                            final TextChannel create = guild1.createTextChannel(TextChannelCreateSpec -> TextChannelCreateSpec.setName("ticket-" + name + "-" + disc)
                                    .setPermissionOverwrites(Collections.singleton(PermissionOverwrite.forMember(userid, PermissionSet.of(VIEW_CHANNEL, SEND_MESSAGES), PermissionSet.of(MENTION_EVERYONE, MANAGE_MESSAGES))))
                                    .setPermissionOverwrites(Collections.singleton(PermissionOverwrite.forRole(Snowflake.of(blacklistedrole), PermissionSet.of(), PermissionSet.of(VIEW_CHANNEL))))
                                    .setPermissionOverwrites(Collections.singleton(PermissionOverwrite.forRole(Snowflake.of(staffrole1), PermissionSet.of(VIEW_CHANNEL, SEND_MESSAGES), PermissionSet.of())))).block();
                            assert create != null;
                            long ticketchannelid = create.getId().asLong();
                            client.getEventDispatcher().on(MessageCreateEvent.class)
                                    .subscribe(event1 -> {
                                        final Message message1 = event1.getMessage();
                                        final long channel = message1.getChannelId().asLong();
                                        final Channel channel1 = message1.getChannel().block();
                                        final String messagecontent = message1.getContent();
                                        final User author = message1.getAuthor().get();
                                        final String authormention = author.getMention();
                                        final String username = author.getUsername();
                                        final String discriminator = author.getDiscriminator();
                                        final String pfp = author.getAvatarUrl();
                                        final Instant instant = Instant.now();
                                        if ("!close".equalsIgnoreCase(messagecontent)) {
                                            if(String.valueOf(channel).equals(String.valueOf(ticketchannelid))) {
                                                final TextChannel delete = (TextChannel) client.getChannelById(Snowflake.of(ticketchannelid)).block();
                                                assert delete != null;
                                                delete.delete("Ticket Closed.").block();
                                                System.out.println("Ticket Closed By: " + username);

                                                final MessageChannel channel2 = (MessageChannel) client.getChannelById(Snowflake.of(809196891488518214L)).block();
                                                assert channel2 != null;
                                                channel2.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**Ticket Closed**")
                                                        .setDescription("Ticket opened by " + mention + " closed by " + authormention)
                                                        .setColor(Color.of(51, 153, 255))
                                                        .setTimestamp(instant)).block();
                                            }
                                        }
                                    });
                            final MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(ticketchannelid)).block();
                            assert channel != null;
                            channel.createMessage(messageCreateSpec -> messageCreateSpec.setContent("Please be patient, " + mention + " , " + staffrolemention + " will be with you soon.")).block();
                            System.out.println("Ticket Created By: " + user);
                        }
                    }
                });

        // LINK BLACKLIST

        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    final Message m = event.getMessage();
                    final Optional<Member> member = event.getMember();
                    final Role r = member.get().getHighestRole().block();
                    assert r != null;
                    final int role = r.getRawPosition();
                    final Optional<User> a = m.getAuthor();
                    final String name = a.get().getUsername();
                    final String discriminator = a.get().getDiscriminator();
                    final String pfp = a.get().getAvatarUrl();
                    final String mention = a.get().getMention();
                    final Channel c = m.getChannel().block();
                    final Snowflake id = m.getChannelId();
                    final long blacklistedchannel = 808838745541050399L;
                    assert c != null;
                    final String channelmention = c.getMention();
                    final String messagecontent = m.getContent();
                    final Instant instant = Instant.now();
                    final String reason = "Contains Link";
                    final String reason2 = "Bad Word Usage";
                    final String messagecontentlowercase = messagecontent.toLowerCase();

                	if (role >= 27 || id.asLong() == blacklistedchannel) {

                	} else {
                    	final MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(808838744609652785L)).block();
                    	if (messagecontentlowercase.contains("https://") || messagecontentlowercase.contains("discord.gg") || messagecontentlowercase.contains(".com")
                            	|| messagecontentlowercase.contains(".net") || messagecontentlowercase.contains(".org")) {
                        	m.delete(reason).block();
                        	assert channel != null;
                            channel.createEmbed(EmbedCreateSpec -> EmbedCreateSpec.setColor(Color.RED)
                                    .setTitle("**Message Deleted**")
                                    .setDescription("Message Sent By " + mention + " Deleted in " + channelmention)
                                    .addField("**Reason**", reason, false)
                                    .setFooter(name + "#" + discriminator, pfp)
                                    .setTimestamp(instant)
                                    .setColor(Color.BLUE)).block();
                    	}
                }
                });

        // MESSAGE LOGS

        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    final Message m = event.getMessage();
                    final String messagecontent = m.getContent();
                    final Channel c = m.getChannel().block();
                    final Snowflake channelid = m.getChannelId();
                    final long blacklistedchannelid = 809196891488518214L;
                    final long blacklistedauthorid = 787057269903851520L;
                    assert c != null;
                    final String cname = c.getMention();
                    final Optional<User> a = m.getAuthor();
                    final Snowflake authorid = a.get().getId();
                    final String mention = a.get().getMention();
                    final String name = a.get().getUsername();
                    final String discriminator = a.get().getDiscriminator();
                    final String pfp = a.get().getAvatarUrl();
                    final Instant instant = Instant.now();
                    final MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(809196891488518214L)).block();
                    if (channelid.asLong() != blacklistedchannelid && authorid.asLong() != blacklistedauthorid) {
                        assert channel != null;
                        channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle("**Message Sent**")
                                .setDescription("Message Sent In " + cname + " By " + mention)
                                .addField("**Message Content**", messagecontent, false)
                                .setFooter(name + "#" + discriminator, pfp)
                                .setTimestamp(instant)
                                .setColor(Color.BLUE)).block();
                    }
                });

        // REACTION ROLES

        client.getEventDispatcher().on(ReactionAddEvent.class)
                .subscribe(event -> {
                    final Snowflake id = event.getMessageId();
                    final Optional<Member> m = event.getMember();
                    final long messageid = 809164971148705843L;
                    if (id.asLong() == messageid){
                        final Snowflake r = event.getUserId();
                        if (r.asLong() == r.asLong()) {
                            m.get().addRole(Snowflake.of("808838744203198504")).block();
                        }
                    }
                });

        client.getEventDispatcher().on(ReactionRemoveEvent.class)
                .subscribe(event -> {
                    final Snowflake id = event.getMessageId();
                    final User u = event.getUser().block();
                    assert u != null;
                    final Snowflake m = u.getId();
                    final Mono<Member> g = Objects.requireNonNull(event.getGuild().block()).getMemberById(m);
                    final long messageid = 809164971148705843L;
                    if (id.asLong() == messageid){
                        final Snowflake r = event.getUserId();
                        if (m.asLong() == m.asLong()) {
                            Objects.requireNonNull(g.block()).removeRole(Snowflake.of("808838744203198504")).block();
                        }
                    }
                });

        // WELCOME MESSAGES AND SERVER STATS

        client.getEventDispatcher().on(MemberJoinEvent.class)
                .subscribe(event -> {
                        final Member m = event.getMember();
                        final String n = m.getUsername();
                        final String d = m.getDiscriminator();
                        final String p = m.getMention();
                        final String pfp = m.getAvatarUrl();
                        final Guild guild = client.getGuildById(Snowflake.of(808838744203198503L)).block();
                    	assert guild != null;
                    	int count = guild.getMemberCount();
                        final Mono<Void> r = m.addRole(Snowflake.of(809243022172356649L));
                        r.block();
                        final Instant instant = Instant.now();
                    	final MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(808838744609652782L)).block();
                    	assert channel != null;
                    	channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle(n + " **Has Joined the Server**")
                            	.setColor(Color.GREEN)
                            	.setDescription("Welcome " + p + " to the Voltiac Network Discord Server!")
                            	.addField("**How to Join**", "If you want to join the minecraft server, check out <#808838744609652776>", false)
                            	.addField("**Support**", "If you have questions or need support, create a ticket in <#808838744609652784>", false)
                            	.addField("**Members**", "We now have " + count + " members!", false)
                            	.setFooter(n + "#" + d, pfp)
                            	.setTimestamp(instant)
                            	.setThumbnail(pfp)).block();
                });
        client.getEventDispatcher().on(MemberLeaveEvent.class)
                .subscribe(event -> {
                    final User u = event.getUser();
                    final String n = u.getUsername();
                    final String d = u.getDiscriminator();
                    final String p = u.getMention();
                    final String pfp = u.getAvatarUrl();
                    final Instant instant = Instant.now();
                    final Guild guild = client.getGuildById(Snowflake.of(808838744203198503L)).block();
                    assert guild != null;
                    int count = guild.getMemberCount();
                    System.out.println(count);
                    final MessageChannel channel = (MessageChannel) client.getChannelById(Snowflake.of(808838744609652782L)).block();
                    assert channel != null;
                    channel.createEmbed(embedCreateSpec -> embedCreateSpec.setTitle(n + " **Has Left the Server**")
                            .setColor(Color.RED)
                            .setDescription(n + "#" + d + " Has Left the Server")
                            .addField("**Members**", "We now have " + count + " members.", false)
                            .setFooter(n + "#" + d, pfp)
                            .setTimestamp(instant)
                            .setThumbnail(pfp)).block();
                });
        client.onDisconnect().block();
    }
}
