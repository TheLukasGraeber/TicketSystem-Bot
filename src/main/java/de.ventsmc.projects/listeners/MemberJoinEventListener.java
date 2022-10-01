package de.ventsmc.projects.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Date;

public class MemberJoinEventListener extends ListenerAdapter {

    private final String prefix;
    private final JDABuilder builder;

    public MemberJoinEventListener(String prefix, JDABuilder builder) {
        this.prefix = prefix;
        this.builder = builder;
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        final Member member = event.getMember();
        final Guild guild = event.getGuild();
        final TextChannel welcomeChannel = guild.getTextChannelById("939501589541056530");
        assert welcomeChannel != null;
        welcomeChannel.sendMessage(this.getJoinMessage(member, guild).build()).queue();
    }

    private EmbedBuilder getJoinMessage(Member member, Guild guild) {
        final EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Willkommen - " + member.getUser().getName());
        embedBuilder.setColor(new Color(238,	110,	9));
        embedBuilder.setTimestamp(new Date().toInstant());
        embedBuilder.setThumbnail(member.getEffectiveAvatarUrl());
        embedBuilder.setDescription("✘────────────────────────────✘\n" +
                "<:bild2:1014125562467467316> Hey " + member.getUser().getName() + ", schau dich doch mal in folgenden Channel um. Dadurch erhältst du eine gute Einführung in den Server!\n" +
                "✘────────────────────────────✘\n" +
                "<:bild25:1014125597359874108> " + guild.getTextChannelById("939501589541056531").getAsMention() + " Lese das Regelwerk durch.\n" +
                "<:bild25:1014125597359874108> " + guild.getTextChannelById("939501589788524644").getAsMention() + " Hole dir unsere tollen Rollen ab.\n" +
                "<:bild25:1014125597359874108> " + guild.getTextChannelById("939501589788524645").getAsMention() + " Hier gibt es weiter Infos zum Server.\n" +
                "✘────────────────────────────✘\n" +
                "<:bild25:1014125597359874108> Du brauchst Hilfe?\n" +
                "<:bild25:1014125597359874108> Dann öffne in " + guild.getTextChannelById("939501590291816521").getAsMention() + " ein Ticket! Hier erhältst du dann von unserem Team Unterstützung.\n" +
                "\n" +
                "<:bild25:1014125597359874108> Heißt doch gerne " + member.getUser().getName() + " in │\uD83D\uDCAC〢general willkommen!\n" +
                "✘────────────────────────────✘\n");
        embedBuilder.setImage("https://cdn.discordapp.com/attachments/939501589541056528/1014124970277867520/Kopie_von_Neuanfang.png");
        embedBuilder.setFooter("Made by VentsMC", "https://cdn.discordapp.com/attachments/939501589541056528/1014121972814336000/orange.png");
        return embedBuilder;
    }

    public String getPrefix() {
        return prefix;
    }

    public JDABuilder getBuilder() {
        return builder;
    }

}
