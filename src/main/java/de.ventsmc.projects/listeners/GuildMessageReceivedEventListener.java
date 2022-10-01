package de.ventsmc.projects.listeners;

import de.ventsmc.projects.commands.TicketCommand;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class GuildMessageReceivedEventListener extends ListenerAdapter {

    private final String prefix;
    private final JDABuilder builder;
    private final TicketCommand ticketCommand;

    public GuildMessageReceivedEventListener(String prefix, JDABuilder builder) {
        this.prefix = prefix;
        this.builder = builder;
        this.ticketCommand = new TicketCommand("v!ticket", this.prefix);
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        final String[] args = event.getMessage().getContentRaw().split(" ");
        final Message message = event.getMessage();
        final Member member = event.getMember();
        final Guild guild = event.getGuild();
        this.writeInBotCommandsChannel(guild);
        this.registerCommands(args, guild, member);
    }

    private void writeInBotCommandsChannel(Guild guild) {
        for (Message message : Objects.requireNonNull(guild.getTextChannelById("939501590732230689")).getHistory().getRetrievedHistory()) {
            message.delete().complete();
        }
    }

    private void registerCommands(String[] args, Guild guild, Member member) {
        this.getTicketCommand().getTicketCommand(args, guild, member);
    }

    public String getPrefix() {
        return prefix;
    }

    public JDABuilder getBuilder() {
        return builder;
    }

    public TicketCommand getTicketCommand() {
        return ticketCommand;
    }
}