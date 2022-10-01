package de.ventsmc.projects;

import de.ventsmc.projects.apis.TicketAPI;
import de.ventsmc.projects.listeners.ButtonClickEventListener;
import de.ventsmc.projects.listeners.GuildMessageReceivedEventListener;
import de.ventsmc.projects.listeners.MemberJoinEventListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class VentsMC {

    private static final JDABuilder jdaBuilder = JDABuilder.createDefault("MTAxNDExODMwNzIzMTcxNTMyOA.GsGHxJ.PNvFog0mjCi7XLrhJElVaCDr1YueiyF3-6Wmy8");
    private static final TicketAPI ticketApi = new TicketAPI("939501589541056527", "45.142.114.105", "GsGHxJ.PNvFog0mjCi7XLrhJElVaCDr1YueiyF3-6Wmy8");

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        registerListeners();
        optionalSettings();
    }

    private static void optionalSettings() {
        try {
            getJdaBuilder().setStatus(OnlineStatus.IDLE);
            getJdaBuilder().setActivity(Activity.playing("ventsmc.de"));
            getJdaBuilder().enableCache(CacheFlag.MEMBER_OVERRIDES);
            getJdaBuilder().setMemberCachePolicy(MemberCachePolicy.VOICE);
            getJdaBuilder().enableIntents(GatewayIntent.GUILD_PRESENCES,GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_EMOJIS, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_TYPING, GatewayIntent.GUILD_BANS);
            getJdaBuilder().build();
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
    }

    private static void registerListeners() {
        getJdaBuilder().addEventListeners(new MemberJoinEventListener("VentsMC", getJdaBuilder()));
        getJdaBuilder().addEventListeners(new GuildMessageReceivedEventListener("VentsMC", getJdaBuilder()));
        getJdaBuilder().addEventListeners(new ButtonClickEventListener("VentsMC", getJdaBuilder(), ticketApi));
    }

    public static JDABuilder getJdaBuilder() {
        return jdaBuilder;
    }
}
