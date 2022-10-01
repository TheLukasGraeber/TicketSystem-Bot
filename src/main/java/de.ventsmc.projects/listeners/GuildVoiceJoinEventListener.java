package de.ventsmc.projects.listeners;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class GuildVoiceJoinEventListener extends ListenerAdapter {

    private final String prefix;
    private final JDABuilder builder;

    public GuildVoiceJoinEventListener(String prefix, JDABuilder builder) {
        this.prefix = prefix;
        this.builder = builder;
    }

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        final Member member = event.getMember();
        final Guild guild = event.getGuild();
        this.onMemberJoinToCreateChannel(event);
    }

    public void onMemberJoinToCreateChannel(GuildVoiceJoinEvent event) {
        if (event.getChannelJoined().getId().equalsIgnoreCase("939501589998207023")) {
            System.out.println("NO");
            for (VoiceChannel voiceChannel : Objects.requireNonNull(event.getGuild().getCategoryById("939501589998207018")).getVoiceChannels()) {
                if (!voiceChannel.getName().equalsIgnoreCase(event.getMember().getEffectiveName())) {
                    final Collection<Permission> permissionCollection = new ArrayList<>();
                    permissionCollection.add(Permission.ADMINISTRATOR);
                    event.getGuild().createVoiceChannel(event.getMember().getEffectiveName(), event.getGuild().getCategoryById("939501589998207018")).addMemberPermissionOverride(event.getMember().getIdLong(), permissionCollection, null).queue();
                }
            }
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public JDABuilder getBuilder() {
        return builder;
    }

}
