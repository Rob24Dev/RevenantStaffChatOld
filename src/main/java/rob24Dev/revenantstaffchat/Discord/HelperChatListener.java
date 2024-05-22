package rob24Dev.revenantstaffchat.Discord;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import rob24Dev.revenantstaffchat.Main;
import rob24Dev.revenantstaffchat.Managers.Files.FilesManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelperChatListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        String helperchat = Main.getInstance().getConfig().getString("discordintegration.helperschatid");
        boolean enablediscord = Main.getInstance().getConfig().getBoolean("discordintegration.discord");
        if (event.getChannel().getId().equals(helperchat)) {
            if (enablediscord) {
                if (!event.getAuthor().isBot()) {
                    String format = Main.getInstance()
                                                                 .getConfig()
                                                                 .getString("Messages.helperchat.playerwritemessagediscord");
                    for (String message : args) {
                        format = format + message + " ";
                    }
                    String finalformat = format;
                    Bukkit.getOnlinePlayers().forEach(p -> {
                        if (p.hasPermission("revenantstaffchat.helpers.view"))
                            p.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', finalformat.replace("%discord_name%", event.getAuthor()
                                                                                                                                            .getName())));
                    });
                    DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDateTime time = LocalDateTime.now();
                    DateTimeFormatter timeformat2 = DateTimeFormatter.ofPattern("(HH:mm:ss)");
                    LocalDateTime time2 = LocalDateTime.now();
                    FilesManager.getHCLogs()
                                .set(timeformat.format(time) + "." + event.getMember()
                                                                          .getUser()
                                                                          .getName() + "(Discord).msg" + timeformat2.format(time2), format.replace("%discord_name%", event.getMember()
                                                                                                                                                                          .getUser()
                                                                                                                                                                          .getName()));
                    FilesManager.saveHCLogs();
                    FilesManager.reloadHCLogs();
                }
            }
        }
    }
}