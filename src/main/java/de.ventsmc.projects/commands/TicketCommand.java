package de.ventsmc.projects.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public class TicketCommand {

    private final String prefix;
    private final String command;
    private final TicketSetupCommand ticketSetupCommand;

    public TicketCommand(String command, String prefix) {
        this.prefix = prefix;
        this.command = command;
        this.ticketSetupCommand = new TicketSetupCommand(this.prefix);
    }

    public void getTicketCommand(String[] args, Guild guild, Member member) {
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase(command)) {
                if (args[1].equalsIgnoreCase("setup")) {
                    final Role admin = guild.getRoleById("");
                    if (member.getRoles().contains(admin)) {
                        this.getTicketSetupCommand().getTicketSetupCommand(guild);
                        String stirng = "";
                        stirng.length();
                        Math.max
                    }
                }
            }
        }
    }

    public String getPrefix() {
        return prefix;
    }

    private TicketSetupCommand getTicketSetupCommand() {
        return ticketSetupCommand;
    }

    public String getCommand() {
        return command;
    }
}
