package xyz.voltiac.bot.OtherUtil;

import com.fasterxml.jackson.databind.JsonNode;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import org.json.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Memes {
    public static void memes(GatewayDiscordClient client) {
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    try {
                    Message message = event.getMessage();
                    String messagecontent = message.getContent();
                    User member = message.getAuthorAsMember().block();
                    assert member != null;
                    String username = member.getUsername();
                    assert username != null;
                    String discriminator = member.getDiscriminator();
                    String avatarurl = member.getAvatarUrl();
                    MessageChannel channel = (MessageChannel) message.getChannel().block();
                    String posturl = null;
                    String imageurl = null;
                    String title = null;
                    String ups = null;
                    String author = null;
                    if (messagecontent.equalsIgnoreCase("!memes")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/memes");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();

                            System.out.println("!memes Command Executed By: " + username);
                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!surrealmemes")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/surrealmemes");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!surrealmemes Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!all")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/all");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!all Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!rareinsults")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/rareinsults");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!rareinsults Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!cursedcomments")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/cursedcomments");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!cursedcomments Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!facepalm")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/facepalm");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!facepalm Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!wholesomememes")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/wholesomememes");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!wholesomememes Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!dankmemes")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/dankmemes");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!dankmemes Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!therewasanattempt")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/therewasanattempt");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!therewasanattempt Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!starterpacks")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/starterpacks");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!starterpacks Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!clevercomebacks")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/clevercomebacks");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!clevercomebacks Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!aww")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/aww");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!aww Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!gaming")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/gaming");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!gaming Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!bonehurtingjuice")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/bonehurtingjuice");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!bonehurtingjuice Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!programmerhumor")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/programmerhumor");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!programmerhumor Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!eyebleach")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/eyebleach");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!eyebleach Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!minecraft")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/minecraft");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!minecraft Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("!animalcrossing")) {
                        try {
                            URL url = new URL("https://meme-api.herokuapp.com/gimme/animalcrossing");
                            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            String input = br.readLine();
                            title = input.substring(input.indexOf("\"title\":\"") + 9, input.indexOf("\",\"url\":\""));
                            imageurl = input.substring(input.indexOf("\"url\":\"") + 7, input.indexOf("\",\"nsfw\""));
                            posturl = input.substring(input.indexOf("\"postLink\":\"") + 12, input.indexOf("\",\"subreddit\""));
                            ups = input.substring(input.indexOf(",\"ups\":") + 7, input.indexOf(",\"preview\""));
                            author = input.substring(input.indexOf(",\"author\":\"") + 11, input.indexOf("\",\"ups\""));
                            br.close();
                            String finalImageurl = imageurl;
                            String finalTitle = title;
                            String finalPosturl = posturl;
                            String finalAuthor = author;
                            String finalUps = ups;
                            channel.createEmbed(EmbedCreateSpec -> {
                                EmbedCreateSpec.setTitle(finalTitle).setUrl(finalPosturl)
                                        .setImage(finalImageurl)
                                        .setColor(Color.of(51, 153, 255))
                                        .setFooter("Requested By: " + username + "#" + discriminator + " | u/" + finalAuthor + " | \uD83D\uDC4D: " + finalUps, avatarurl);
                            }).block();
                            System.out.println("!animalcrossing Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }
                    } catch (Exception e) {
                    }
                    });
    }
}

