package de.ventsmc.projects.listeners;

import de.ventsmc.projects.apis.TicketAPI;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ButtonClickEventListener extends ListenerAdapter {

    private final String prefix;
    private final JDABuilder builder;
    private final TicketAPI ticketAPI;

    public ButtonClickEventListener(String prefix, JDABuilder builder, TicketAPI ticketAPI) {
        this.prefix = prefix;
        this.builder = builder;
        this.ticketAPI = ticketAPI;
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        final Member member = event.getMember();
        final Guild guild = event.getGuild();
        this.onClickTicketCreateButton(event, member, guild);
        this.onClickTicketDeleteButton(event, member, guild);
    }

    public void onClickTicketCreateButton(ButtonClickEvent event, Member member, Guild guild) {
        if (event.getComponent().getId().equalsIgnoreCase("createTicket")) {
            if (!this.getTicketAPI().isTicketRegistered(member)) {
                System.out.println("YES");
                this.getTicketAPI().createTicket(member, guild);
            } else {
                System.out.println("NO");
            }
        }
    }

    public void onClickTicketDeleteButton(ButtonClickEvent event, Member member, Guild guild) {
        if (event.getComponent().getId().equalsIgnoreCase("deleteTicket")) {
            if (this.getTicketAPI().isTicketRegistered(member)) {
                System.out.println("YES");
                this.getTicketAPI().deleteTicket(member, guild);
            } else {
                System.out.println("NO");
            }
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public JDABuilder getBuilder() {
        return builder;
    }

    public TicketAPI getTicketAPI() {
        return ticketAPI;
    }
}
