package rob24Dev.revenantstaffchat.Discord;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import rob24Dev.revenantstaffchat.Main;
import rob24Dev.revenantstaffchat.Managers.Files.FilesManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DeveloperChatListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        String developerchat = Main.getInstance().getConfig().getString("discordintegration.developerschatid");
        boolean enablediscord = Main.getInstance().getConfig().getBoolean("discordintegration.discord");
        if (event.getChannel().getId().equals(developerchat)) {
            if (enablediscord) {
                if (!event.getAuthor().isBot()) {
                    String format = Main.getInstance()
                                                                 .getConfig()
                                                                 .getString("Messages.developerchat.playerwritemessagediscord");
                    for (String message : args) {
                        format = format + message + " ";
                    }
                    String finalformat = format;
                    Bukkit.getOnlinePlayers().forEach(p -> {
                        if (p.hasPermission("revenantstaffchat.developers.view"))
                            p.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(finalformat)
                                                                                                        .replace("%discord_name%", event.getAuthor()
                                                                                                                                        .getName())));
                    });
                    DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDateTime time = LocalDateTime.now();
                    DateTimeFormatter timeformat2 = DateTimeFormatter.ofPattern("(HH:mm:ss)");
                    LocalDateTime time2 = LocalDateTime.now();
                    FilesManager.getDCLogs()
                                .set(timeformat.format(time) + "." + Objects.requireNonNull(event.getMember())
                                                                            .getUser()
                                                                            .getName() + "(Discord).msg" + timeformat2.format(time2), Objects.requireNonNull(format)
                                                                                                                                             .replace("%discord_name%", event.getMember()
                                                                                                                                                                             .getUser()
                                                                                                                                                                             .getName()));
                    FilesManager.saveDCLogs();
                    FilesManager.reloadDCLogs();
                }
            }
        }
    }
}