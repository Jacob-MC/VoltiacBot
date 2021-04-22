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
                    if (messagecontent.equalsIgnoreCase("r/memes")) {
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

                            System.out.println("r/memes Command Executed By: " + username);
                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/surrealmemes")) {
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
                            System.out.println("r/surrealmemes Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/all")) {
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
                            System.out.println("r/all Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/rareinsults")) {
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
                            System.out.println("r/rareinsults Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/cursedcomments")) {
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
                            System.out.println("r/cursedcomments Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/facepalm")) {
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
                            System.out.println("r/facepalm Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/wholesomememes")) {
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
                            System.out.println("r/wholesomememes Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/dankmemes")) {
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
                            System.out.println("r/dankmemes Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/therewasanattempt")) {
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
                            System.out.println("r/therewasanattempt Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/starterpacks")) {
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
                            System.out.println("r/starterpacks Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/clevercomebacks")) {
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
                            System.out.println("r/clevercomebacks Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/aww")) {
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
                            System.out.println("r/aww Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/gaming")) {
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
                            System.out.println("r/gaming Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/bonehurtingjuice")) {
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
                            System.out.println("r/bonehurtingjuice Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/programmerhumor")) {
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
                            System.out.println("r/programmerhumor Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/eyebleach")) {
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
                            System.out.println("r/eyebleach Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/minecraft")) {
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
                            System.out.println("r/minecraft Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }

                    if (messagecontent.equalsIgnoreCase("r/animalcrossing")) {
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
                            System.out.println("r/animalcrossing Command Executed By: " + username);

                        } catch (Exception e) {
                            channel.createMessage("An error occured. Please try again later.").block();
                        }
                    }
                    } catch (Exception e) {
                    }
                    });
    }
}

