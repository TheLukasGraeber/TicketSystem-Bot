package de.ventsmc.projects.apis;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.ButtonStyle;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TicketAPI {

    private final String token;
    private final String address;
    private final String ticketsCategory;

    public TicketAPI(String ticketsCategory, String address, String token) {
        this.token = token;
        this.address = address;
        this.ticketsCategory = ticketsCategory;
    }

    public void createTicket(Member member, Guild guild) {
        final Collection<Permission> permissionCollection = new ArrayList<>();
        permissionCollection.add(Permission.VIEW_CHANNEL);
        final Category category = guild.getCategoryById(this.getTicketsCategory());
        this.createTicketPlayerDatabase(member, member.getEffectiveName() + "-ticket");
        guild.createTextChannel(member.getEffectiveName() + "-ticket", category).addMemberPermissionOverride(member.getIdLong(), permissionCollection, null).queue();
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            final List<Button> buttons = new ArrayList<>();
            buttons.clear();
            buttons.add(net.dv8tion.jda.api.interactions.components.Button.of(ButtonStyle.DANGER, "deleteTicket", "Ticket Löschen", Emoji.fromMarkdown("<:bild14:1014125580016427018>")));
            for (TextChannel textChannel : guild.getTextChannelsByName(getTicketPlayer(member), true)) {
                textChannel.sendMessage("Ticket schließung -> Button").setActionRow(buttons).queue();
            }
        }, 1, TimeUnit.SECONDS);
    }

    public void deleteTicket(Member member, Guild guild) {
        for (TextChannel textChannel : guild.getTextChannelsByName(this.getTicketPlayer(member), true)) {
            textChannel.delete().queue();
        }
        this.deleteTicketPlayerDatabase(member);
    }

    public String getTicketPlayer(Member member) {
        try{
            String url = null;
            url = "http://" + this.getAddress() + "/" + this.getToken() + "/ticketsystem/tickets/" + member.getId() + "/main/ticket.html";
            URLConnection openConnection = new URL(url).openConnection();
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            @SuppressWarnings("resource")
            Scanner scan = new Scanner((new InputStreamReader(openConnection.getInputStream())));
            while(scan.hasNextLine()){
                String firstline = scan.nextLine();
                return firstline;
            }
        } catch(Exception e){
            return "false";
        }
        return null;
    }

    public String getTicketPlayerID(Member member) {
        try{
            String url = null;
            url = "http://" + this.getAddress() + "/" + this.getToken() + "/ticketsystem/tickets/" + member.getId() + "/main/ticketID.html";
            URLConnection openConnection = new URL(url).openConnection();
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            @SuppressWarnings("resource")
            Scanner scan = new Scanner((new InputStreamReader(openConnection.getInputStream())));
            while(scan.hasNextLine()){
                String firstline = scan.nextLine();
                return firstline;
            }
        } catch(Exception e){
            return "false";
        }
        return null;
    }

    public void createTicketPlayerDatabase(Member member, TextChannel textChannel) {
        URLConnection request = null;
        try {
            URL url = new URL("http://" + this.getAddress() + "/" + this.getToken() + "/ticketsystem/create.php?token=" + this.getToken() + "&member=" + member.getId() + "&channelID=" + textChannel.getId());
            request = url.openConnection();
            request.connect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assert request != null;
        try {
            request.getContent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean isTicketRegistered(Member member) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            //        HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con =
                    (HttpURLConnection) new URL("http://" + this.getAddress() + "/" + this.getToken() + "/ticketsystem/tickets/" + member.getId() + "/main/ticket.html").openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteTicketPlayerDatabase(Member member) {
        URLConnection request = null;
        try {
            URL url = new URL("http://" + this.getAddress() + "/" + this.getToken() + "/ticketsystem/delete.php?token=" + this.getToken() + "&member=" + member.getId());
            request = url.openConnection();
            request.connect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assert request != null;
        try {
            request.getContent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTicketPlayerDatabase(Member member, String textChannel) {
        URLConnection request = null;
        try {
            URL url = new URL("http://" + this.getAddress() + "/" + this.getToken() + "/ticketsystem/create.php?token=" + this.getToken() + "&member=" + member.getId() + "&channelID=" + textChannel);
            request = url.openConnection();
            request.connect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assert request != null;
        try {
            request.getContent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTicketsCategory() {
        return ticketsCategory;
    }

    public String getToken() {
        return token;
    }

    public String getAddress() {
        return address;
    }
}
