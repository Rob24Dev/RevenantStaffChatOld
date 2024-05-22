package rob24Dev.revenantstaffchat.Listeners;

import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import rob24Dev.revenantstaffchat.Main;
import rob24Dev.revenantstaffchat.Managers.Files.FilesManager;
import rob24Dev.revenantstaffchat.Managers.ToggleManagers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Listeners implements Listener {

    @EventHandler
    public void OnChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        ToggleManagers toggleManagers = Main.toggleManagersHashMap.get(player.getUniqueId());
        if (toggleManagers.isStaffchattoggle()) {
            e.setCancelled(true);
            String format = Main.getInstance().getConfig().getString("Messages.staffchat.playerwritemessage");
            Bukkit.getOnlinePlayers().forEach(p -> {
                if (p.hasPermission("revenantstaffchat.view"))
                    p.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(format)
                                                                                                .replace("%player%", player.getDisplayName()) + e.getMessage()));
            });
            String discordformat = Main.getInstance().getConfig().getString("Messages.discord.staffchatwrite");
            DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter timeformat2 = DateTimeFormatter.ofPattern("(HH:mm:ss)");
            LocalDateTime time2 = LocalDateTime.now();
            FilesManager.getSCLogs()
                        .set(timeformat.format(time) + "." + player.getName() + ".msg" + timeformat2.format(time2), Objects.requireNonNull(format)
                                                                                                                           .replace("%player%", player.getName()) + e.getMessage());
            FilesManager.saveSCLogs();
            FilesManager.reloadSCLogs();
            String staffchat = Main.getInstance().getConfig().getString("discordintegration.staffchatid");
            boolean enablediscord = Main.getInstance().getConfig().getBoolean("discordintegration.discord");
            if (enablediscord) {
                TextChannel textChannel = Main.jda.getTextChannelById(Objects.requireNonNull(staffchat));
                if (Objects.requireNonNull(textChannel).canTalk()) {
                    textChannel.sendMessage(Objects.requireNonNull(discordformat)
                                                   .replace("%player%", player.getDisplayName()) + e.getMessage())
                               .queue();
                }
            }
        } else {
            if (toggleManagers.isHelperchattoggle()) {
                e.setCancelled(true);
                String format = Main.getInstance().getConfig().getString("Messages.helperchat.playerwritemessage");
                String discordformat = Main.getInstance().getConfig().getString("Messages.discord.helperchatwrite");
                Bukkit.getOnlinePlayers().forEach(p -> {
                    if (p.hasPermission("revenantstaffchat.helpers.view"))
                        p.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(format)
                                                                                                    .replace("%player%", player.getDisplayName()) + e.getMessage()));
                });
                DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDateTime time = LocalDateTime.now();
                DateTimeFormatter timeformat2 = DateTimeFormatter.ofPattern("(HH:mm:ss)");
                LocalDateTime time2 = LocalDateTime.now();
                FilesManager.getHCLogs()
                            .set(timeformat.format(time) + "." + player.getName() + ".msg" + timeformat2.format(time2), Objects.requireNonNull(format)
                                                                                                                               .replace("%player%", player.getName() + e.getMessage()));
                FilesManager.saveHCLogs();
                FilesManager.reloadHCLogs();
                String helperchat = Main.getInstance()
                                        .getConfig()
                                        .getString("discordintegration.helperschatid");
                boolean enablediscord = Main.getInstance().getConfig().getBoolean("discordintegration.discord");
                if (enablediscord) {
                    TextChannel textChannel = Main.jda.getTextChannelById(Objects.requireNonNull(helperchat));
                    if (Objects.requireNonNull(textChannel).canTalk()) {
                        textChannel.sendMessage(Objects.requireNonNull(discordformat)
                                                       .replace("%player%", player.getDisplayName()) + e.getMessage())
                                   .queue();
                    }
                }
            } else {
                if (toggleManagers.isDeveloperchattoggle()) {
                    e.setCancelled(true);
                    String format = Main.getInstance()
                                        .getConfig()
                                        .getString("Messages.developerchat.playerwritemessage");
                    String discordformat = Main.getInstance()
                                               .getConfig()
                                               .getString("Messages.discord.developerchatwrite");
                    Bukkit.getOnlinePlayers().forEach(p -> {
                        if (p.hasPermission("revenantstaffchat.developers.view"))
                            p.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(format)
                                                                                                        .replace("%player%", player.getDisplayName())+ e.getMessage()));
                    });
                    DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDateTime time = LocalDateTime.now();
                    DateTimeFormatter timeformat2 = DateTimeFormatter.ofPattern("(HH:mm:ss)");
                    LocalDateTime time2 = LocalDateTime.now();
                    FilesManager.getDCLogs()
                                .set(timeformat.format(time) + "." + player.getName() + ".msg" + timeformat2.format(time2), Objects.requireNonNull(format)
                                                                                                                                   .replace("%player%", player.getName()) + e.getMessage());
                    FilesManager.saveDCLogs();
                    FilesManager.reloadDCLogs();
                    String developerchat = Main.getInstance()
                                               .getConfig()
                                               .getString("discordintegration.developerschatid");
                    boolean enablediscord = Main.getInstance()
                                                .getConfig()
                                                .getBoolean("discordintegration.discord");
                    if (enablediscord) {
                        TextChannel textChannel = Main.jda.getTextChannelById(Objects.requireNonNull(developerchat));
                        if (Objects.requireNonNull(textChannel).canTalk()) {
                            textChannel.sendMessage(Objects.requireNonNull(discordformat)
                                                           .replace("%player%", player.getDisplayName()) + e.getMessage())
                                       .queue();
                        }
                    }
                } else {
                    if (toggleManagers.isBuilderchattoggle()) {
                        e.setCancelled(true);
                        String format = Main.getInstance()
                                            .getConfig()
                                            .getString("Messages.builderchat.playerwritemessage");
                        String discordformat = Main.getInstance()
                                                   .getConfig()
                                                   .getString("Messages.discord.builderchatwrite");
                        Bukkit.getOnlinePlayers().forEach(p -> {
                            if (p.hasPermission("revenantstaffchat.builders.view"))
                                p.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(format)
                                                                                                            .replace("%player%", player.getDisplayName()) + e.getMessage()));
                        });
                        DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        LocalDateTime time = LocalDateTime.now();
                        DateTimeFormatter timeformat2 = DateTimeFormatter.ofPattern("(HH:mm:ss)");
                        LocalDateTime time2 = LocalDateTime.now();
                        FilesManager.getBCLogs()
                                    .set(timeformat.format(time) + "." + player.getName() + ".msg" + timeformat2.format(time2), Objects.requireNonNull(format)
                                                                                                                                       .replace("%player%", player.getName()) + e.getMessage());
                        FilesManager.saveBCLogs();
                        FilesManager.reloadBCLogs();
                        String builderchat = Main.getInstance()
                                                 .getConfig()
                                                 .getString("discordintegration.builderschatid");
                        boolean enablediscord = Main.getInstance().getConfig().getBoolean("discordintegration.discord");
                        if (enablediscord) {
                            TextChannel textChannel = Main.jda.getTextChannelById(Objects.requireNonNull(builderchat));
                            if (Objects.requireNonNull(textChannel).canTalk()) {
                                textChannel.sendMessage(Objects.requireNonNull(discordformat)
                                                               .replace("%player%", player.getDisplayName()) + e.getMessage())
                                           .complete();
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void OnJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Main.toggleManagersHashMap.put(player.getUniqueId(), new ToggleManagers(false,false,false,false));
        if (player.hasPermission("revenantstaffchat.join")) {
            String staffchat = Main.getInstance().getConfig().getString("discordintegration.staffchatid");
            boolean enablediscord = Main.getInstance().getConfig().getBoolean("discordintegration.discord");
            String join = Main.getInstance().getConfig().getString("Messages.player.join");
            String joindiscord = Main.getInstance().getConfig().getString("Messages.discord.joindiscord");
            Bukkit.getOnlinePlayers().forEach(p -> {
                if (p.hasPermission("revenantstaffchat.view"))
                    p.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(join)
                                                                                                .replace("%player%", player.getName())));
            });
            if (enablediscord) {
                TextChannel textChannel = Main.jda.getTextChannelById(Objects.requireNonNull(staffchat));
                if (Objects.requireNonNull(textChannel).canTalk()) {
                    textChannel.sendMessage(Objects.requireNonNull(joindiscord).replace("%player%", player.getName())).complete();
                }
            }
        }
    }

    @EventHandler
    public void OnLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (player.hasPermission("revenantstaffchat.leave")) {
            String leave = Main.getInstance().getConfig().getString("Messages.player.leave");
            String leavediscord = Main.getInstance().getConfig().getString("Messages.discord.leavediscord");
            String staffchat = Main.getInstance().getConfig().getString("discordintegration.staffchatid");
            boolean enablediscord = Main.getInstance().getConfig().getBoolean("discordintegration.discord");
            Main.toggleManagersHashMap.put(player.getUniqueId(), new ToggleManagers(false,false,false,false));
            Bukkit.getOnlinePlayers().forEach(p -> {
                if (p.hasPermission("revenantstaffchat.view"))
                    p.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(leave)
                                                                                                .replace("%player%", player.getName())));
            });
            if (enablediscord) {
                TextChannel textChannel = Main.jda.getTextChannelById(Objects.requireNonNull(staffchat));
                if (Objects.requireNonNull(textChannel).canTalk()) {
                    textChannel.sendMessage(Objects.requireNonNull(leavediscord).replace("%player%", player.getName())).complete();
                }
            }
        }
    }
}
