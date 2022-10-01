package de.ventsmc.projects.listeners;

import de.ventsmc.projects.apis.TicketAPI;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class TextChannelDeleteEventListener extends ListenerAdapter {

    private final String prefix;
    private final JDABuilder builder;
    private final TicketAPI ticketAPI;

    public TextChannelDeleteEventListener(String prefix, JDABuilder builder, TicketAPI ticketAPI) {
        this.prefix = prefix;
        this.builder = builder;
        this.ticketAPI = ticketAPI;
    }

    @Override
    public void onTextChannelDelete(TextChannelDeleteEvent event) {
        final Guild guild = event.getGuild();
        if (event.getChannel().getName().equalsIgnoreCase("")) {

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