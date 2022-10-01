package de.ventsmc.projects.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.interactions.components.ButtonStyle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TicketSetupCommand {

    private final String prefix;

    public TicketSetupCommand(String prefix) {
        this.prefix = prefix;
    }

    public void getTicketSetupCommand(Guild guild) {
        this.getTicketSetupFunction(guild);
    }

    private void getTicketSetupFunction(Guild guild) {
        final TextChannel supportChannel = guild.getTextChannelById("939501590291816521");
        final List<net.dv8tion.jda.api.interactions.components.Button> buttons = new ArrayList<>();
        buttons.clear();
        buttons.add(net.dv8tion.jda.api.interactions.components.Button.of(ButtonStyle.SUCCESS, "createTicket", "Ticket Erstellen", Emoji.fromMarkdown("<:bild18:1014125585901043795>")));
        assert supportChannel != null;
        supportChannel.sendMessage(this.getTicketSetupMessage(guild).build()).setActionRow(buttons).queue();
    }

    private EmbedBuilder getTicketSetupMessage(Guild guild) {
        final EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Support - Beantragen");
        embedBuilder.setColor(new Color(238,	110,	9));
        embedBuilder.setTimestamp(new Date().toInstant());
        embedBuilder.setThumbnail("https://cdn.discordapp.com/attachments/939501589541056528/1014121972814336000/orange.png");
        embedBuilder.setDescription("✘────────────────────────────✘\n" +
                "<:bild2:1014125562467467316> Hier kannst du ein Support Ticket öffnen, bitte beachte das du keine Regel/n dabei brichst!\n" +
                "✘────────────────────────────✘\n" +
                "<:bild25:1014125597359874108> " + Objects.requireNonNull(guild.getTextChannelById("939501589541056531")).getAsMention() + " Lese das Regelwerk durch.\n" +
                "<:bild25:1014125597359874108> " + Objects.requireNonNull(guild.getTextChannelById("939501589788524644")).getAsMention() + " Hole dir unsere tollen Rollen ab.\n" +
                "<:bild25:1014125597359874108> " + Objects.requireNonNull(guild.getTextChannelById("939501589788524645")).getAsMention() + " Hier gibt es weiter Infos zum Server.\n" +
                "✘────────────────────────────✘\n");
        embedBuilder.setImage("https://cdn.discordapp.com/attachments/939501589541056528/1014132525045653644/Kopie_von_Neuanfang_2.png");
        embedBuilder.setFooter("Made by VentsMC", "https://cdn.discordapp.com/attachments/939501589541056528/1014121972814336000/orange.png");
        return embedBuilder;
    }

    public String getPrefix() {
        return prefix;
    }

}
