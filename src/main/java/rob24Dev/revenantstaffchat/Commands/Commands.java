package rob24Dev.revenantstaffchat.Commands;

import net.dv8tion.jda.api.entities.TextChannel;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import rob24Dev.revenantstaffchat.Main;
import rob24Dev.revenantstaffchat.Managers.Files.FilesManager;
import rob24Dev.revenantstaffchat.Managers.ToggleManagers;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Commands implements CommandExecutor, Listener {

    public Inventory adminteam;
    String headiteam;

    public void setHeaditeam(String headiteam) {
        this.headiteam = headiteam;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, String label, String[] args) {
        {
            if (label.equalsIgnoreCase("sca")) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    if (player.hasPermission("revenantstaffchat.admin.main")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉      &6Revenant&aStaff&cChat &bv.&6" + Main.version));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉           &6Commands"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &6/sca &7plugin info"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &8Permission &6revenantstaffchat.admin.main"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &6/sca clearlog <chat name/all> &7Clear log command"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &8Permission &6revenantstaffchat.admin.clearlog"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &6/sca reload &7Reload command"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &8Permission &6revenantstaffchat.admin.reload"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &6/sca createcommand <name> &7Create custom SC chat command"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &8Permission &6revenantstaffchat.commands.create"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &6/ateam &7Open Admin team online menu"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &8Permission &6X"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉    &7Create by: &bRob24Dev"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉"));
                    } else {
                        String dontpermission = Main.getInstance().getConfig()
                                                    .getString("Messages.commands.main.dontpermission");
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(dontpermission)));
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("createcommand")) {
                    if(player.hasPermission("revenantstaffchat.commands.create")) {
                        if(args.length == 1) {
                           CreateCommandsMenu(player);
                        } else {
                            String use = Main.getInstance().getConfig().getString("Messages.commands.createcommand.use");
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(use)));
                        }
                    } else {
                        String dontpermission = Main.getInstance().getConfig()
                                                    .getString("Messages.commands.createcommand.dontpermission");
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(dontpermission)));
                    }
                } else if (args[0].equalsIgnoreCase("reload")) {
                    if (player.hasPermission("revenantstaffchat.admin.reload")) {
                            boolean enablediscord = Main.getInstance()
                                                        .getConfig()
                                                        .getBoolean("discordintegration.discord");
                            String reloadmessage = Main.getInstance().getConfig()
                                                       .getString("Messages.commands.reload.reloadmessage");
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(reloadmessage)));
                            reloadConfigs();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lIf you have now added a bot restart server, in this case we are not responsible for any errors!"));
                            File config = new File("plugins/RevenantStaffCH/config.yml");
                            if(!config.exists()) {
                                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "[RevenantStaffCH] &7I'm creating a new config file..."));
                                Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                                    Main.getInstance().getConfig().options().copyDefaults(true);
                                    Main.getInstance().saveDefaultConfig();
                                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "[RevenantStaffCH] &2Done!"));
                                }, 40);
                            }
                            if (enablediscord) {
                                Main.jda.shutdownNow();
                                Main.Discordstart();
                            }
                        } else {
                            String dontpermission = Main.getInstance().getConfig()
                                                        .getString("Messages.commands.reload.dontpermission");
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(dontpermission)));
                            return true;
                    }
                } else if (args[0].equalsIgnoreCase("clearlog")) {
                    if (args.length == 1) {
                        String format = Main.getInstance().getConfig().getString("Messages.commands.clearlog.use");
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(format)));
                    }
                    if (args.length == 2) {
                        if (player.hasPermission("revenantstaffchat.admin.clearlog")) {
                            FilesManager.ClearChats(args[1], player);
                        }
                    }
                }
            } else if (label.equalsIgnoreCase("ateam")) {
                Player player = (Player) sender;
                createMenu(player);
            } else if (label.equalsIgnoreCase("bc")) {
                if (!(sender instanceof Player)) {
                    String onlyforplayers = Main.getInstance().getConfig()
                                                .getString("Messages.builderchat.commands.onlyforplayers");
                    sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(onlyforplayers)));
                    return false;
                }
                Player player = (Player) sender;
                boolean enable = Main.getInstance().getConfig().getBoolean("staffchats.builders");
                if (!enable) {
                    String builderchatblocked = Main.getInstance()
                                                    .getConfig()
                                                    .getString("Messages.builderchat.blocked");
                    player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(builderchatblocked)));
                    return false;
                } else {
                    if (!player.hasPermission("revenantstaffchat.builders.use")) {
                        String dontpermission = Main.getInstance().getConfig()
                                                    .getString("Messages.builderchat.commands.dontpermission");
                        player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(dontpermission)));
                        return false;
                    }
                    if (args.length < 1) {
                        String toggletitle = Main.getInstance()
                                                 .getConfig()
                                                 .getString("Messages.builderchat.defaultmessage.Toggle.title");
                        boolean togglebold = Main.getInstance()
                                                 .getConfig()
                                                 .getBoolean("Messages.builderchat.defaultmessage.Toggle.bold");
                        String togglecolor = Main.getInstance()
                                                 .getConfig()
                                                 .getString("Messages.builderchat.defaultmessage.Toggle.color");
                        String toggledescription = Main.getInstance()
                                                       .getConfig()
                                                       .getString("Messages.builderchat.defaultmessage.Toggle.description");
                        boolean toggleitalic = Main.getInstance().getConfig().getBoolean("Messages.builderchat.defaultmessage.Toggle.italic");
                        TextComponent toggle = new TextComponent(toggletitle);
                        toggle.setColor(getColorWithString(Objects.requireNonNull(togglecolor)));
                        toggle.setBold(togglebold);
                        toggle.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bc toggle"));
                        toggle.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(toggledescription).color(getColorWithString(togglecolor))
                                                                                                                                                                .italic(toggleitalic)
                                                                                                                                                                .create()));

                        String builderchattitle = Main.getInstance()
                                                    .getConfig()
                                                    .getString("Messages.builderchat.defaultmessage.WriteStaffChat.title");
                        String builderchatdescription = Main.getInstance()
                                                          .getConfig()
                                                          .getString("Messages.builderchat.defaultmessage.WriteStaffChat.description");
                        String builderchatcolor = Main.getInstance()
                                                    .getConfig()
                                                    .getString("Messages.builderchat.defaultmessage.WriteStaffChat.color");
                        boolean builderchatitalic = Main.getInstance()
                                                      .getConfig()
                                                      .getBoolean("Messages.builderchat.defaultmessage.WriteStaffChat.italic");
                        boolean builderchatbold = Main.getInstance()
                                                    .getConfig()
                                                    .getBoolean("Messages.builderchat.defaultmessage.WriteStaffChat.bold");
                        TextComponent write = new TextComponent(builderchattitle);
                        write.setColor(getColorWithString(Objects.requireNonNull(builderchatcolor)));
                        write.setBold(builderchatbold);
                        write.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(builderchatdescription).color(getColorWithString(builderchatcolor))
                                                                                                                                    .italic(builderchatitalic).create()));
                        for(String messagetop : Main.getInstance().getConfig().getStringList("Messages.builderchat.defaultmessage.text.top"))
                        {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagetop));
                        }
                        player.spigot().sendMessage(toggle);
                        player.spigot().sendMessage(write);
                        for(String messagebottom: Main.getInstance().getConfig().getStringList("Messages.builderchat.defaultmessage.text.bottom")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagebottom));
                        }
                    } else {
                        if (args[0].equalsIgnoreCase("toggle")) {
                            String togglemodeontitle = Main.getInstance()
                                                           .getConfig()
                                                           .getString("Messages.builderchat.defaultmessage.ToggleModeON.title");
                            String togglemodeondescription = Main.getInstance()
                                                                 .getConfig()
                                                                 .getString("Messages.builderchat.defaultmessage.ToggleModeON.description");
                            String togglemodeoncolor = Main.getInstance()
                                                           .getConfig()
                                                           .getString("Messages.builderchat.defaultmessage.ToggleModeON.color");
                            boolean togglemodeonitalic = Main.getInstance()
                                                             .getConfig()
                                                             .getBoolean("Messages.builderchat.defaultmessage.ToggleModeON.italic");
                            boolean togglemodeonbold = Main.getInstance()
                                                           .getConfig()
                                                           .getBoolean("Messages.builderchat.defaultmessage.ToggleModeON.bold");
                            TextComponent on = new TextComponent(togglemodeontitle);
                            on.setColor(getColorWithString(Objects.requireNonNull(togglemodeoncolor)));
                            on.setBold(togglemodeonbold);
                            on.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bc on"));
                            on.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(togglemodeondescription).color(getColorWithString(togglemodeoncolor))
                                                                                                                                                            .italic(togglemodeonitalic)
                                                                                                                                                            .create()));
                            String togglemodeofftitle = Main.getInstance()
                                                            .getConfig()
                                                            .getString("Messages.builderchat.defaultmessage.ToggleModeOFF.title");
                            String togglemodeoffdescription = Main.getInstance()
                                                                  .getConfig()
                                                                  .getString("Messages.builderchat.defaultmessage.ToggleModeOFF.description");
                            String togglemodeoffcolor = Main.getInstance()
                                                            .getConfig()
                                                            .getString("Messages.builderchat.defaultmessage.ToggleModeOFF.color");
                            boolean togglemodeoffitalic = Main.getInstance()
                                                              .getConfig()
                                                              .getBoolean("Messages.builderchat.defaultmessage.ToggleModeOFF.italic");
                            boolean togglemodeoffbold = Main.getInstance()
                                                            .getConfig()
                                                            .getBoolean("Messages.builderchat.defaultmessage.ToggleModeOFF.bold");
                            TextComponent off = new TextComponent(togglemodeofftitle);
                            off.setColor(getColorWithString(Objects.requireNonNull(togglemodeoffcolor)));
                            off.setBold(togglemodeoffbold);
                            off.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bc off"));
                            off.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(togglemodeoffdescription).color(getColorWithString(togglemodeoffcolor))
                                                                                                                                                              .italic(togglemodeoffitalic)
                                                                                                                                                              .create()));
                            for(String messagetop : Main.getInstance().getConfig().getStringList("Messages.builderchat.defaultmessage.togglemessage.text.top"))
                            {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagetop));
                            }
                            player.spigot().sendMessage(on);
                            player.spigot().sendMessage(off);
                            for(String messagebottom : Main.getInstance().getConfig().getStringList("Messages.builderchat.defaultmessage.togglemessage.text.bottom"))
                            {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagebottom));
                            }
                            return false;
                        }
                        if (args[0].equalsIgnoreCase("on")) {
                            ToggleManagers toggleManagers = Main.toggleManagersHashMap.get(player.getUniqueId());
                            if (toggleManagers.isBuilderchattoggle()) {
                                String alreadytogglemode = Main.getInstance().getConfig()
                                                               .getString("Messages.builderchat.commands.alreadytogglemodeon");
                                player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(alreadytogglemode)));
                            } else {
                                String togglemode = Main.getInstance().getConfig()
                                                        .getString("Messages.builderchat.commands.togglemodeon");
                                player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(togglemode)));
                                toggleManagers.setBuilderchattoggle(true);
                            }
                        } else {
                            if (args[0].equalsIgnoreCase("off")) {
                                ToggleManagers toggleManagers = Main.toggleManagersHashMap.get(player.getUniqueId());
                                if (!toggleManagers.isBuilderchattoggle()) {
                                    String alreadytogglemodeoff = Main.getInstance().getConfig()
                                                                      .getString("Messages.builderchat.commands.alreadytogglemodeoff");
                                    player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(alreadytogglemodeoff)));
                                } else {
                                    String togglemodeoff = Main.getInstance().getConfig()
                                                               .getString("Messages.builderchat.commands.togglemodeoff");
                                    player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(togglemodeoff)));
                                    toggleManagers.setBuilderchattoggle(false);
                                }
                            } else {
                                String format = Main.getInstance().getConfig()
                                                    .getString("Messages.builderchat.playerwritemessage");
                                for (String message : args) {
                                    format = format + message + " ";
                                }
                                String formatdiscord = Main.getInstance().getConfig()
                                                           .getString("Messages.discord.builderchatwrite");
                                for (String message : args) {
                                    formatdiscord = formatdiscord + message + " ";
                                }
                                String finalformat = format;
                                Bukkit.getOnlinePlayers().forEach(p -> {
                                    if (p.hasPermission("revenantstaffchat.builders.view"))
                                        p.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', finalformat.replace("%player%", player.getDisplayName())));
                                });
                                DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                                LocalDateTime time = LocalDateTime.now();
                                DateTimeFormatter timeformat2 = DateTimeFormatter.ofPattern("(HH:mm:ss)");
                                LocalDateTime time2 = LocalDateTime.now();
                                FilesManager.getBCLogs()
                                            .set(timeformat.format(time) + "." + player.getName() + ".msg" + timeformat2.format(time2), format.replace("%player%", player.getName()));
                                FilesManager.saveBCLogs();
                                FilesManager.reloadBCLogs();
                                String builderchatid = Main.getInstance().getConfig()
                                                           .getString("discordintegration.builderschatid");
                                boolean enablediscord = Main.getInstance().getConfig()
                                                            .getBoolean("discordintegration.discord");
                                if (enablediscord) {
                                    TextChannel textChannel = Main.jda.getTextChannelById(Objects.requireNonNull(builderchatid));
                                    if (Objects.requireNonNull(textChannel).canTalk()) {
                                        textChannel.sendMessage(formatdiscord.replace("%player%", ChatColor.stripColor(player.getDisplayName())))
                                                   .queue();
                                    }
                                    return false;
                                }
                            }
                        }
                    }
                }
            } else if (label.equalsIgnoreCase("dc")) {
                if (!(sender instanceof Player)) {
                    String onlyforplayers = Main.getInstance().getConfig()
                                                .getString("Messages.developerchat.commands.onlyforplayers");
                    sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(onlyforplayers)));
                    return false;
                }
                Player player = (Player) sender;
                boolean enable = Main.getInstance().getConfig().getBoolean("staffchats.developers");
                if (!enable) {
                    String developerchatblocked = Main.getInstance().getConfig()
                                                      .getString("Messages.developerchat.blocked");
                    player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(developerchatblocked)));
                    return false;
                } else {
                    if (!player.hasPermission("revenantstaffchat.developers.use")) {
                        String dontpermission = Main.getInstance().getConfig()
                                                    .getString("Messages.developerchat.commands.dontpermission");
                        player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(dontpermission)));
                        return false;
                    }
                    if (args.length < 1) {
                        String toggletitle = Main.getInstance()
                                                 .getConfig()
                                                 .getString("Messages.developerchat.defaultmessage.Toggle.title");
                        boolean togglebold = Main.getInstance()
                                                 .getConfig()
                                                 .getBoolean("Messages.developerchat.defaultmessage.Toggle.bold");
                        String togglecolor = Main.getInstance()
                                                 .getConfig()
                                                 .getString("Messages.developerchat.defaultmessage.Toggle.color");
                        String toggledescription = Main.getInstance()
                                                       .getConfig()
                                                       .getString("Messages.developerchat.defaultmessage.Toggle.description");
                        boolean toggleitalic = Main.getInstance().getConfig().getBoolean("Messages.developerchat.defaultmessage.Toggle.italic");
                        TextComponent toggle = new TextComponent(toggletitle);
                        toggle.setColor(getColorWithString(Objects.requireNonNull(togglecolor)));
                        toggle.setBold(togglebold);
                        toggle.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dc toggle"));
                        toggle.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(toggledescription).color(getColorWithString(togglecolor))
                                                                                                                                                                .italic(toggleitalic)
                                                                                                                                                                .create()));
                        String developerchattitle = Main.getInstance()
                                                      .getConfig()
                                                      .getString("Messages.developerchat.defaultmessage.WriteStaffChat.title");
                        String developerchatdescription = Main.getInstance()
                                                            .getConfig()
                                                            .getString("Messages.developerchat.defaultmessage.WriteStaffChat.description");
                        String developerchatcolor = Main.getInstance()
                                                      .getConfig()
                                                      .getString("Messages.developerchat.defaultmessage.WriteStaffChat.color");
                        boolean developerchatitalic = Main.getInstance()
                                                        .getConfig()
                                                        .getBoolean("Messages.developerchat.defaultmessage.WriteStaffChat.italic");
                        boolean developerchatbold = Main.getInstance()
                                                      .getConfig()
                                                      .getBoolean("Messages.developerchat.defaultmessage.WriteStaffChat.bold");
                        TextComponent write = new TextComponent(developerchattitle);
                        write.setColor(getColorWithString(Objects.requireNonNull(developerchatcolor)));
                        write.setBold(developerchatbold);
                        write.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(developerchatdescription).color(getColorWithString(developerchatcolor))
                                                                                                                                                                             .italic(developerchatitalic)
                                                                                                                                                                             .create()));
                        for(String messagetop : Main.getInstance().getConfig().getStringList("Messages.developerchat.defaultmessage.text.top"))
                        {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagetop));
                        }
                        player.spigot().sendMessage(toggle);
                        player.spigot().sendMessage(write);
                        for(String messagebottom: Main.getInstance().getConfig().getStringList("Messages.developerchat.defaultmessage.text.bottom")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagebottom));
                        }
                    } else {
                        if (args[0].equalsIgnoreCase("toggle")) {
                            String togglemodeontitle = Main.getInstance()
                                                           .getConfig()
                                                           .getString("Messages.developerchat.defaultmessage.ToggleModeON.title");
                            String togglemodeondescription = Main.getInstance()
                                                                 .getConfig()
                                                                 .getString("Messages.developerchat.defaultmessage.ToggleModeON.description");
                            String togglemodeoncolor = Main.getInstance()
                                                           .getConfig()
                                                           .getString("Messages.developerchat.defaultmessage.ToggleModeON.color");
                            boolean togglemodeonitalic = Main.getInstance()
                                                             .getConfig()
                                                             .getBoolean("Messages.developerchat.defaultmessage.ToggleModeON.italic");
                            boolean togglemodeonbold = Main.getInstance()
                                                           .getConfig()
                                                           .getBoolean("Messages.developerchat.defaultmessage.ToggleModeON.bold");
                            TextComponent on = new TextComponent(togglemodeontitle);
                            on.setColor(getColorWithString(Objects.requireNonNull(togglemodeoncolor)));
                            on.setBold(togglemodeonbold);
                            on.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dc on"));
                            on.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(togglemodeondescription).color(getColorWithString(togglemodeoncolor))
                                                                                                                                                            .italic(togglemodeonitalic)
                                                                                                                                                            .create()));
                            String togglemodeofftitle = Main.getInstance()
                                                            .getConfig()
                                                            .getString("Messages.developerchat.defaultmessage.ToggleModeOFF.title");
                            String togglemodeoffdescription = Main.getInstance()
                                                                  .getConfig()
                                                                  .getString("Messages.developerchat.defaultmessage.ToggleModeOFF.description");
                            String togglemodeoffcolor = Main.getInstance()
                                                            .getConfig()
                                                            .getString("Messages.developerchat.defaultmessage.ToggleModeOFF.color");
                            boolean togglemodeoffitalic = Main.getInstance()
                                                              .getConfig()
                                                              .getBoolean("Messages.developerchat.defaultmessage.ToggleModeOFF.italic");
                            boolean togglemodeoffbold = Main.getInstance()
                                                            .getConfig()
                                                            .getBoolean("Messages.developerchat.defaultmessage.ToggleModeOFF.bold");
                            TextComponent off = new TextComponent(togglemodeofftitle);
                            off.setColor(getColorWithString(Objects.requireNonNull(togglemodeoffcolor)));
                            off.setBold(togglemodeoffbold);
                            off.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dc off"));
                            off.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(togglemodeoffdescription).color(getColorWithString(togglemodeoffcolor))
                                                                                                                                                              .italic(togglemodeoffitalic)
                                                                                                                                                              .create()));
                            for(String messagetop : Main.getInstance().getConfig().getStringList("Messages.developerchat.defaultmessage.togglemessage.text.top"))
                            {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagetop));
                            }
                            player.spigot().sendMessage(on);
                            player.spigot().sendMessage(off);
                            for(String messagebottom : Main.getInstance().getConfig().getStringList("Messages.developerchat.defaultmessage.togglemessage.text.bottom"))
                            {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagebottom));
                            }
                            return false;
                        }
                        if (args[0].equalsIgnoreCase("on")) {
                            ToggleManagers toggleManagers = Main.toggleManagersHashMap.get(player.getUniqueId());
                            if (toggleManagers.isDeveloperchattoggle()) {
                                String alreadytogglemode = Main.getInstance().getConfig()
                                                               .getString("Messages.developerchat.commands.alreadytogglemodeon");
                                player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(alreadytogglemode)));
                            } else {
                                String togglemode = Main.getInstance().getConfig()
                                                        .getString("Messages.developerchat.commands.togglemodeon");
                                player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(togglemode)));
                                toggleManagers.setDeveloperchattoggle(true);
                            }
                        } else {
                            if (args[0].equalsIgnoreCase("off")) {
                                ToggleManagers toggleManagers = Main.toggleManagersHashMap.get(player.getUniqueId());
                                if (!toggleManagers.isDeveloperchattoggle()) {
                                    String alreadytogglemodeoff = Main.getInstance().getConfig()
                                                                      .getString("Messages.developerchat.commands.alreadytogglemodeoff");
                                    player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(alreadytogglemodeoff)));
                                } else {
                                    String togglemodeoff = Main.getInstance().getConfig()
                                                               .getString("Messages.developerchat.commands.togglemodeoff");
                                    player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(togglemodeoff)));
                                    toggleManagers.setDeveloperchattoggle(false);
                                }
                            } else {
                                String format = Main.getInstance().getConfig()
                                                    .getString("Messages.developerchat.playerwritemessage");
                                for (String message : args) {
                                    format = format + message + " ";
                                }
                                String formatdiscord = Main.getInstance().getConfig()
                                                           .getString("Messages.discord.developerchatwrite");
                                for (String message : args) {
                                    formatdiscord = formatdiscord + message + " ";
                                }
                                String finalformat = format;
                                Bukkit.getOnlinePlayers().forEach(p -> {
                                    if (p.hasPermission("revenantstaffchat.developers.view"))
                                        p.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', finalformat.replace("%player%", player.getDisplayName())));
                                });
                                DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                                LocalDateTime time = LocalDateTime.now();
                                DateTimeFormatter timeformat2 = DateTimeFormatter.ofPattern("(HH:mm:ss)");
                                LocalDateTime time2 = LocalDateTime.now();
                                FilesManager.getDCLogs()
                                            .set(timeformat.format(time) + "." + player.getName() + ".msg" + timeformat2.format(time2), format.replace("%player%", player.getName()));
                                FilesManager.saveDCLogs();
                                FilesManager.reloadDCLogs();
                                String developerchatid = Main.getInstance().getConfig()
                                                             .getString("discordintegration.developerschatid");
                                boolean enablediscord = Main.getInstance().getConfig()
                                                            .getBoolean("discordintegration.discord");
                                if (enablediscord) {
                                    TextChannel textChannel = Main.jda.getTextChannelById(Objects.requireNonNull(developerchatid));
                                    if (Objects.requireNonNull(textChannel).canTalk()) {
                                        textChannel.sendMessage(formatdiscord.replace("%player%", ChatColor.stripColor(player.getDisplayName())))
                                                   .queue();
                                    }
                                    return false;
                                }
                            }
                        }
                    }
                }
            } else if (label.equalsIgnoreCase("hc")) {
                if (!(sender instanceof Player)) {
                    String onlyforplayers = Main.getInstance().getConfig()
                                                .getString("Messages.helperchat.commands.onlyforplayers");
                    sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(onlyforplayers)));
                    return false;
                }
                Player player = (Player) sender;
                boolean enable = Main.getInstance().getConfig().getBoolean("staffchats.helpers");
                if (!enable) {
                    String helperchatblocked = Main.getInstance().getConfig().getString("Messages.helperchat.blocked");
                    player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(helperchatblocked)));
                    return false;
                } else {
                    if (!player.hasPermission("revenantstaffchat.helpers.use")) {
                        String dontpermission = Main.getInstance().getConfig()
                                                    .getString("Messages.helperchat.commands.dontpermission");
                        player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(dontpermission)));
                        return false;
                    }
                    if (args.length < 1) {
                        String toggletitle = Main.getInstance()
                                                 .getConfig()
                                                 .getString("Messages.helperchat.defaultmessage.Toggle.title");
                        boolean togglebold = Main.getInstance()
                                                 .getConfig()
                                                 .getBoolean("Messages.helperchat.defaultmessage.Toggle.bold");
                        String togglecolor = Main.getInstance()
                                                 .getConfig()
                                                 .getString("Messages.helperchat.defaultmessage.Toggle.color");
                        String toggledescription = Main.getInstance()
                                                       .getConfig()
                                                       .getString("Messages.helperchat.defaultmessage.Toggle.description");
                        boolean toggleitalic = Main.getInstance()
                                                   .getConfig()
                                                   .getBoolean("Messages.helperchat.defaultmessage.Toggle.italic");
                        TextComponent toggle = new TextComponent(toggletitle);
                        toggle.setColor(getColorWithString(Objects.requireNonNull(togglecolor)));
                        toggle.setBold(togglebold);
                        toggle.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/hc toggle"));
                        toggle.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(toggledescription).color(getColorWithString(togglecolor))
                                                                                                                                .italic(toggleitalic)
                                                                                                                                .create()));
                        String helperchattitle = Main.getInstance()
                                                     .getConfig()
                                                     .getString("Messages.helperchat.defaultmessage.WriteStaffChat.title");
                        String helperchatdescription = Main.getInstance()
                                                           .getConfig()
                                                           .getString("Messages.helperchat.defaultmessage.WriteStaffChat.description");
                        String helperchatcolor = Main.getInstance()
                                                     .getConfig()
                                                     .getString("Messages.helperchat.defaultmessage.WriteStaffChat.color");
                        boolean helperchatitalic = Main.getInstance()
                                                       .getConfig()
                                                       .getBoolean("Messages.helperchat.defaultmessage.WriteStaffChat.italic");
                        boolean helperchatbold = Main.getInstance()
                                                     .getConfig()
                                                     .getBoolean("Messages.helperchat.defaultmessage.WriteStaffChat.bold");
                        TextComponent write = new TextComponent(helperchattitle);
                        write.setColor(getColorWithString(Objects.requireNonNull(helperchatcolor)));
                        write.setBold(helperchatbold);
                        write.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(helperchatdescription).color(getColorWithString(helperchatcolor))
                                                                                                                                   .italic(helperchatitalic)
                                                                                                                                   .create()));
                        for (String messagetop : Main.getInstance()
                                                     .getConfig()
                                                     .getStringList("Messages.helperchat.defaultmessage.text.top")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagetop));
                        }
                        player.spigot().sendMessage(toggle);
                        player.spigot().sendMessage(write);
                        for (String messagebottom : Main.getInstance()
                                                        .getConfig()
                                                        .getStringList("Messages.helperchat.defaultmessage.text.bottom")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagebottom));
                        }
                    } else {
                        if (args[0].equalsIgnoreCase("toggle")) {
                            String togglemodeontitle = Main.getInstance()
                                                           .getConfig()
                                                           .getString("Messages.helperchat.defaultmessage.ToggleModeON.title");
                            String togglemodeondescription = Main.getInstance()
                                                                 .getConfig()
                                                                 .getString("Messages.helperchat.defaultmessage.ToggleModeON.description");
                            String togglemodeoncolor = Main.getInstance()
                                                           .getConfig()
                                                           .getString("Messages.helperchat.defaultmessage.ToggleModeON.color");
                            boolean togglemodeonitalic = Main.getInstance()
                                                             .getConfig()
                                                             .getBoolean("Messages.helperchat.defaultmessage.ToggleModeON.italic");
                            boolean togglemodeonbold = Main.getInstance()
                                                           .getConfig()
                                                           .getBoolean("Messages.helperchat.defaultmessage.ToggleModeON.bold");
                            TextComponent on = new TextComponent(togglemodeontitle);
                            on.setColor(getColorWithString(Objects.requireNonNull(togglemodeoncolor)));
                            on.setBold(togglemodeonbold);
                            on.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/hc on"));
                            on.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(togglemodeondescription).color(getColorWithString(togglemodeoncolor))
                                                                                                                                      .italic(togglemodeonitalic)
                                                                                                                                      .create()));
                            String togglemodeofftitle = Main.getInstance()
                                                            .getConfig()
                                                            .getString("Messages.helperchat.defaultmessage.ToggleModeOFF.title");
                            String togglemodeoffdescription = Main.getInstance()
                                                                  .getConfig()
                                                                  .getString("Messages.helperchat.defaultmessage.ToggleModeOFF.description");
                            String togglemodeoffcolor = Main.getInstance()
                                                            .getConfig()
                                                            .getString("Messages.helperchat.defaultmessage.ToggleModeOFF.color");
                            boolean togglemodeoffitalic = Main.getInstance()
                                                              .getConfig()
                                                              .getBoolean("Messages.helperchat.defaultmessage.ToggleModeOFF.italic");
                            boolean togglemodeoffbold = Main.getInstance()
                                                            .getConfig()
                                                            .getBoolean("Messages.helperchat.defaultmessage.ToggleModeOFF.bold");
                            TextComponent off = new TextComponent(togglemodeofftitle);
                            off.setColor(getColorWithString(Objects.requireNonNull(togglemodeoffcolor)));
                            off.setBold(togglemodeoffbold);
                            off.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/hc off"));
                            off.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(togglemodeoffdescription).color(getColorWithString(togglemodeoffcolor))
                                                                                                                                        .italic(togglemodeoffitalic)
                                                                                                                                        .create()));
                            for (String messagetop : Main.getInstance()
                                                         .getConfig()
                                                         .getStringList("Messages.helperchat.defaultmessage.togglemessage.text.top")) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagetop));
                            }
                            player.spigot().sendMessage(on);
                            player.spigot().sendMessage(off);
                            for (String messagebottom : Main.getInstance()
                                                            .getConfig()
                                                            .getStringList("Messages.helperchat.defaultmessage.togglemessage.text.bottom")) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagebottom));
                            }
                            return false;
                        }
                        if (args[0].equalsIgnoreCase("on")) {
                            ToggleManagers toggleManagers = Main.toggleManagersHashMap.get(player.getUniqueId());
                            if (toggleManagers.isHelperchattoggle()) {
                                String alreadytogglemode = Main.getInstance().getConfig()
                                                               .getString("Messages.helperchat.commands.alreadytogglemodeon");
                                player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(alreadytogglemode)));
                            } else {
                                String togglemode = Main.getInstance().getConfig()
                                                        .getString("Messages.helperchat.commands.togglemodeon");
                                player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(togglemode)));
                                toggleManagers.setHelperchattoggle(true);
                            }
                        } else {
                            if (args[0].equalsIgnoreCase("off")) {
                                ToggleManagers toggleManagers = Main.toggleManagersHashMap.get(player.getUniqueId());
                                if (!toggleManagers.isHelperchattoggle()) {
                                    String alreadytogglemodeoff = Main.getInstance().getConfig()
                                                                      .getString("Messages.helperchat.commands.alreadytogglemodeoff");
                                    player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(alreadytogglemodeoff)));
                                } else {
                                    String togglemodeoff = Main.getInstance().getConfig()
                                                               .getString("Messages.helperchat.commands.togglemodeoff");
                                    player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(togglemodeoff)));
                                    toggleManagers.setHelperchattoggle(false);
                                }
                            } else {
                                String format = Main.getInstance().getConfig()
                                                    .getString("Messages.helperchat.playerwritemessage");
                                for (String message : args) {
                                    format = format + message + " ";
                                }
                                String formatdiscord = Main.getInstance().getConfig()
                                                           .getString("Messages.discord.helperchatwrite");
                                for (String message : args) {
                                    formatdiscord = formatdiscord + message + " ";
                                }
                                String finalformat = format;
                                Bukkit.getOnlinePlayers().forEach(p -> {
                                    if (p.hasPermission("revenantstaffchat.helpers.view"))
                                        p.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', finalformat.replace("%player%", player.getDisplayName())));
                                });
                                DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                                LocalDateTime time = LocalDateTime.now();
                                DateTimeFormatter timeformat2 = DateTimeFormatter.ofPattern("(HH:mm:ss)");
                                LocalDateTime time2 = LocalDateTime.now();
                                FilesManager.getHCLogs()
                                            .set(timeformat.format(time) + "." + player.getName() + ".msg" + timeformat2.format(time2), format.replace("%player%", player.getDisplayName()));
                                FilesManager.saveHCLogs();
                                FilesManager.reloadHCLogs();
                                String helperchatid = Main.getInstance().getConfig()
                                                          .getString("discordintegration.helperschatid");
                                boolean enablediscord = Main.getInstance().getConfig()
                                                            .getBoolean("discordintegration.discord");
                                if (enablediscord) {
                                    TextChannel textChannel = Main.jda.getTextChannelById(Objects.requireNonNull(helperchatid));
                                    if (Objects.requireNonNull(textChannel).canTalk()) {
                                        textChannel.sendMessage(formatdiscord.replace("%player%", ChatColor.stripColor(player.getDisplayName())))
                                                   .queue();
                                    }
                                    return false;
                                }
                            }
                        }
                    }
                }
                return true;
            } else if (label.equalsIgnoreCase("sc")) {
                if (!(sender instanceof Player)) {
                    String onlyforplayers = Main.getInstance().getConfig()
                                                .getString("Messages.staffchat.commands.onlyforplayers");
                    sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(onlyforplayers)));
                    return false;
                }
                Player player = (Player) sender;
                if (!player.hasPermission("revenantstaffchat.use")) {
                    String dontpermission = Main.getInstance().getConfig()
                                                .getString("Messages.staffchat.commands.dontpermission");
                    player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(dontpermission)));
                    return false;
                }
                if (args.length < 1) {
                    String toggletitle = Main.getInstance()
                                             .getConfig()
                                             .getString("Messages.staffchat.defaultmessage.Toggle.title");
                    boolean togglebold = Main.getInstance()
                                             .getConfig()
                                             .getBoolean("Messages.staffchat.defaultmessage.Toggle.bold");
                    String togglecolor = Main.getInstance()
                                             .getConfig()
                                             .getString("Messages.staffchat.defaultmessage.Toggle.color");
                    TextComponent toggle = new TextComponent(toggletitle);
                    toggle.setColor(getColorWithString(Objects.requireNonNull(togglecolor)));
                    toggle.setBold(togglebold);
                    toggle.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/sc toggle"));
                    String toggledescription = Main.getInstance()
                                                   .getConfig()
                                                   .getString("Messages.staffchat.defaultmessage.Toggle.description");
                    boolean toggleitalic = Main.getInstance().getConfig().getBoolean("Messages.staffchat.defaultmessage.Toggle.italic");
                    toggle.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(toggledescription).color(getColorWithString(Objects.requireNonNull(togglecolor)))
                                                                                                                            .italic(toggleitalic)
                                                                                                                            .create()));
                    String staffchattitle = Main.getInstance()
                                             .getConfig()
                                             .getString("Messages.staffchat.defaultmessage.WriteStaffChat.title");
                    String staffchatdescription = Main.getInstance()
                                                .getConfig()
                                                .getString("Messages.staffchat.defaultmessage.WriteStaffChat.description");
                    String staffchatcolor = Main.getInstance()
                                                      .getConfig()
                                                      .getString("Messages.staffchat.defaultmessage.WriteStaffChat.color");
                    boolean staffchatitalic = Main.getInstance()
                                                .getConfig()
                                                .getBoolean("Messages.staffchat.defaultmessage.WriteStaffChat.italic");
                    boolean staffchatbold = Main.getInstance()
                                             .getConfig()
                                             .getBoolean("Messages.staffchat.defaultmessage.WriteStaffChat.bold");
                    TextComponent write = new TextComponent(staffchattitle);
                    write.setColor(getColorWithString(Objects.requireNonNull(staffchatcolor)));
                    write.setBold(staffchatbold);
                    write.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(staffchatdescription).color(getColorWithString(Objects.requireNonNull(staffchatcolor)))
                                                                                                                                                                     .italic(staffchatitalic)
                                                                                                                                                                     .create()));


                    for(String messagetop : Main.getInstance().getConfig().getStringList("Messages.staffchat.defaultmessage.text.top"))
                    {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagetop));
                    }
                        player.spigot().sendMessage(toggle);
                        player.spigot().sendMessage(write);
                    for(String messagebottom: Main.getInstance().getConfig().getStringList("Messages.staffchat.defaultmessage.text.bottom")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagebottom));
                    }
                } else {
                    if (args[0].equalsIgnoreCase("toggle")) {
                        String togglemodeontitle = Main.getInstance()
                                                       .getConfig()
                                                       .getString("Messages.staffchat.defaultmessage.ToggleModeON.title");
                        String togglemodeondescription = Main.getInstance()
                                                             .getConfig()
                                                             .getString("Messages.staffchat.defaultmessage.ToggleModeON.description");
                        String togglemodeoncolor = Main.getInstance()
                                                       .getConfig()
                                                       .getString("Messages.staffchat.defaultmessage.ToggleModeON.color");
                        boolean togglemodeonitalic = Main.getInstance()
                                                         .getConfig()
                                                         .getBoolean("Messages.staffchat.defaultmessage.ToggleModeON.italic");
                        boolean togglemodeonbold = Main.getInstance()
                                                       .getConfig()
                                                       .getBoolean("Messages.staffchat.defaultmessage.ToggleModeON.bold");
                        TextComponent on = new TextComponent(togglemodeontitle);
                        on.setColor(getColorWithString(Objects.requireNonNull(togglemodeoncolor)));
                        on.setBold(togglemodeonbold);
                        on.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/sc on"));
                        on.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(togglemodeondescription).color(getColorWithString(togglemodeoncolor))
                                                                                                                                  .italic(togglemodeonitalic)
                                                                                                                                  .create()));
                        String togglemodeofftitle = Main.getInstance()
                                                        .getConfig()
                                                        .getString("Messages.staffchat.defaultmessage.ToggleModeOFF.title");
                        String togglemodeoffdescription = Main.getInstance()
                                                              .getConfig()
                                                              .getString("Messages.staffchat.defaultmessage.ToggleModeOFF.description");
                        String togglemodeoffcolor = Main.getInstance()
                                                        .getConfig()
                                                        .getString("Messages.staffchat.defaultmessage.ToggleModeOFF.color");
                        boolean togglemodeoffitalic = Main.getInstance()
                                                          .getConfig()
                                                          .getBoolean("Messages.staffchat.defaultmessage.ToggleModeOFF.italic");
                        boolean togglemodeoffbold = Main.getInstance()
                                                        .getConfig()
                                                        .getBoolean("Messages.staffchat.defaultmessage.ToggleModeOFF.bold");
                        TextComponent off = new TextComponent(togglemodeofftitle);
                        off.setColor(getColorWithString(Objects.requireNonNull(togglemodeoffcolor)));
                        off.setBold(togglemodeoffbold);
                        off.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/sc off"));
                        off.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(togglemodeoffdescription).color(getColorWithString(togglemodeoffcolor))
                                                                                                                                    .italic(togglemodeoffitalic)
                                                                                                                                    .create()));
                        for(String messagedtop : Main.getInstance().getConfig().getStringList("Messages.staffchat.defaultmessage.togglemessage.text.top"))
                        {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagedtop));
                        }
                        player.spigot().sendMessage(on);
                        player.spigot().sendMessage(off);
                        for(String messagebottom : Main.getInstance().getConfig().getStringList("Messages.staffchat.defaultmessage.togglemessage.text.bottom"))
                        {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagebottom));
                        }
                        return false;
                    }
                    if (args[0].equalsIgnoreCase("on")) {
                        ToggleManagers toggleManagers = Main.toggleManagersHashMap.get(player.getUniqueId());
                        if (toggleManagers.isStaffchattoggle()) {
                            String alreadytogglemode = Main.getInstance().getConfig()
                                                           .getString("Messages.staffchat.commands.alreadytogglemodeon");
                            player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(alreadytogglemode)));
                        } else {
                            String togglemode = Main.getInstance().getConfig()
                                                    .getString("Messages.staffchat.commands.togglemodeon");
                            player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(togglemode)));
                            toggleManagers.setStaffchattoggle(true);
                        }
                    } else {
                        if (args[0].equalsIgnoreCase("off")) {
                            ToggleManagers toggleManagers = Main.toggleManagersHashMap.get(player.getUniqueId());
                            if (!toggleManagers.isStaffchattoggle()) {
                                String alreadytogglemodeoff = Main.getInstance().getConfig()
                                                                  .getString("Messages.staffchat.commands.alreadytogglemodeoff");
                                player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(alreadytogglemodeoff)));
                            } else {
                                String togglemodeoff = Main.getInstance().getConfig()
                                                           .getString("Messages.staffchat.commands.togglemodeoff");
                                player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(togglemodeoff)));
                                toggleManagers.setStaffchattoggle(false);
                            }
                        } else {
                            String format = Main.getInstance()
                                                .getConfig()
                                                .getString("Messages.staffchat.playerwritemessage");
                            for (String message : args) {
                                format = format + message + " ";
                            }
                            String finalformat = format;
                            Bukkit.getOnlinePlayers().forEach(p -> {
                                if (p.hasPermission("revenantstaffchat.view"))
                                    p.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', finalformat.replace("%player%", player.getDisplayName())));
                            });
                            DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                            LocalDateTime time = LocalDateTime.now();
                            DateTimeFormatter timeformat2 = DateTimeFormatter.ofPattern("(HH:mm:ss)");
                            LocalDateTime time2 = LocalDateTime.now();
                            FilesManager.getSCLogs()
                                        .set(timeformat.format(time) + "." + player.getName() + ".msg" + timeformat2.format(time2), format.replace("%player%", player.getName()));
                            FilesManager.saveSCLogs();
                            FilesManager.reloadSCLogs();
                            String staffchatid = Main.getInstance()
                                                     .getConfig()
                                                     .getString("discordintegration.staffchatid");
                            boolean enablediscord = Main.getInstance()
                                                        .getConfig()
                                                        .getBoolean("discordintegration.discord");
                            if (enablediscord) {
                                TextChannel textChannel = Main.jda.getTextChannelById(Objects.requireNonNull(staffchatid));
                                if (Objects.requireNonNull(textChannel).canTalk()) {
                                    String formatdiscord = Main.getInstance().getConfig()
                                                               .getString("Messages.discord.staffchatwrite");
                                    for (String message : args) {
                                        formatdiscord = formatdiscord + message + " ";
                                    }
                                    textChannel.sendMessage(formatdiscord.replace("%player%", ChatColor.stripColor(player.getDisplayName())))
                                               .queue();
                                }
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private void CreateCommandsMenu(Player player) {
        Inventory commands = Bukkit.createInventory(null, 45, "&bRevenantStaffChat | Commands");

        ItemStack editname =new ItemStack(Material.NAME_TAG);
        ItemStack editpermissionprefix = new ItemStack(Material.PAPER);


       //Player action
        player.openInventory(commands);
    }

    private void reloadConfigs() {
        boolean developerchat = Main.getInstance().getConfig().getBoolean("staffchats.developers");
        boolean helperchat = Main.getInstance().getConfig().getBoolean("staffchats.helpers");
        boolean builderchat = Main.getInstance().getConfig().getBoolean("staffchats.builders");

        Main.getInstance().reloadConfig();
        FilesManager.saveSCLogs();
        FilesManager.reloadSCLogs();

        if (developerchat) {
            FilesManager.saveDCLogs();
            FilesManager.reloadDCLogs();
        }

        if (helperchat) {
            FilesManager.saveHCLogs();
            FilesManager.reloadHCLogs();
        }

        if (builderchat) {
            FilesManager.saveBCLogs();
            FilesManager.reloadBCLogs();
        }
    }

    public void createMenu(Player player) {
        boolean menuenable = Main.getInstance().getConfig().getBoolean("Menus.menuenable");
        if (menuenable) {
            int size = Main.getInstance().getConfig().getInt("Menus.size");
            String title = Main.getInstance().getConfig().getString("Menus.title");
            String closematerial = Main.getInstance().getConfig().getString("Menus.closeitem.material");
            boolean enableclose = Main.getInstance().getConfig().getBoolean("Menus.closeitem.enable");
            boolean enablehead = Main.getInstance().getConfig().getBoolean("Menus.ateamhead.enable");
            boolean enablepane_1 = Main.getInstance().getConfig().getBoolean("Menus.pane_1.enable");
            boolean enablepane_2 = Main.getInstance().getConfig().getBoolean("Menus.pane_2.enable");
            boolean enablepane_3 = Main.getInstance().getConfig().getBoolean("Menus.pane_3.enable");
            boolean enablepane_4 = Main.getInstance().getConfig().getBoolean("Menus.pane_4.enable");
            boolean enablepane_5 = Main.getInstance().getConfig().getBoolean("Menus.pane_5.enable");
            boolean enablepane_6 = Main.getInstance().getConfig().getBoolean("Menus.pane_6.enable");
            boolean enablepane_7 = Main.getInstance().getConfig().getBoolean("Menus.pane_7.enable");
            boolean enablepane_8 = Main.getInstance().getConfig().getBoolean("Menus.pane_8.enable");
            ItemStack closeitem = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(closematerial))));

            adminteam = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(title)));

            //Panels
            if (enablepane_1) {
                int slot18 = Main.getInstance().getConfig().getInt("Menus.pane_1.slot");
                String item18material = Main.getInstance().getConfig().getString("Menus.pane_1.material");
                ItemStack item18 = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(item18material))));
                adminteam.setItem(slot18, item18);
            }
            if (enablepane_2) {
                int slot19 = Main.getInstance().getConfig().getInt("Menus.pane_2.slot");
                String item19material = Main.getInstance().getConfig().getString("Menus.pane_2.material");
                ItemStack item19 = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(item19material))));
                adminteam.setItem(slot19, item19);
            }
            if (enablepane_3) {
                int slot20 = Main.getInstance().getConfig().getInt("Menus.pane_3.slot");
                String item20material = Main.getInstance().getConfig().getString("Menus.pane_3.material");
                ItemStack item20 = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(item20material))));
                adminteam.setItem(slot20, item20);
            }
            if (enablepane_4) {
                int slot21 = Main.getInstance().getConfig().getInt("Menus.pane_4.slot");
                String item21material = Main.getInstance().getConfig().getString("Menus.pane_4.material");
                ItemStack item21 = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(item21material))));
                adminteam.setItem(slot21, item21);
            }
            if (enablepane_5) {
                String item22material = Main.getInstance().getConfig().getString("Menus.pane_5.material");
                int slot22 = Main.getInstance().getConfig().getInt("Menus.pane_5.slot");
                ItemStack item22 = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(item22material))));
                adminteam.setItem(slot22, item22);
            }
            if (enablepane_6) {
                int slot23 = Main.getInstance().getConfig().getInt("Menus.pane_6.slot");
                String item23material = Main.getInstance().getConfig().getString("Menus.pane_6.material");
                ItemStack item23 = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(item23material))));
                adminteam.setItem(slot23, item23);
            }
            if (enablepane_7) {
                int slot24 = Main.getInstance().getConfig().getInt("Menus.pane_7.slot");
                String item24material = Main.getInstance().getConfig().getString("Menus.pane_7.material");
                ItemStack item24 = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(item24material))));
                adminteam.setItem(slot24, item24);
            }
            if (enablepane_8) {
                int slot25 = Main.getInstance().getConfig().getInt("Menus.pane_8.slot");
                String item25material = Main.getInstance().getConfig().getString("Menus.pane_8.material");
                ItemStack item25 = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(item25material))));
                adminteam.setItem(slot25, item25);
            }

            //Close Item
            if (enableclose) {
                ItemMeta closemeta = closeitem.getItemMeta();
                String displayname = Main.getInstance().getConfig().getString("Menus.closeitem.displayname");
                int slot = Main.getInstance().getConfig().getInt("Menus.closeitem.slot");
                Objects.requireNonNull(closemeta)
                       .setDisplayName(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(displayname)));
                List<String> lore = Main.getInstance().getConfig().getStringList("Menus.closeitem.lore");

                List<String> replacedLore = lore.stream().map(s -> s.replace("&", "§")).collect(Collectors.toList());

                closemeta.setLore(replacedLore);
                closeitem.setItemMeta(closemeta);
                adminteam.setItem(slot, closeitem);
            }

            //Heads
            if (enablehead) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    if (players.hasPermission("revenantstaffchat.menu.view")) {
                        String name = players.getName();
                        if (Bukkit.getVersion().contains("1.8")) {
                            setHeaditeam("SKULL_ITEM");
                        } else if (Bukkit.getVersion().contains("1.9")) {
                            setHeaditeam("SKULL_ITEM");
                        } else if (Bukkit.getVersion().contains("1.10")) {
                            setHeaditeam("SKULL_ITEM");
                        } else if (Bukkit.getVersion().contains("1.11")) {
                            setHeaditeam("SKULL_ITEM");
                        } else if (Bukkit.getVersion().contains("1.12")) {
                            setHeaditeam("SKULL_ITEM");
                        } else if (Bukkit.getVersion().contains("1.13")) {
                            setHeaditeam("LEGACY_SKULL_ITEM");
                        } else if (Bukkit.getVersion().contains("1.14")) {
                            setHeaditeam("LEGACY_SKULL_ITEM");
                        } else if (Bukkit.getVersion().contains("1.15")) {
                            setHeaditeam("LEGACY_SKULL_ITEM");
                        } else if (Bukkit.getVersion().contains("1.16")) {
                            setHeaditeam("LEGACY_SKULL_ITEM");
                        } else if (Bukkit.getVersion().contains("1.17")) {
                            setHeaditeam("LEGACY_SKULL_ITEM");
                        } else if (Bukkit.getVersion().contains("1.18")) {
                            setHeaditeam("LEGACY_SKULL_ITEM");
                         } else if (Bukkit.getVersion().contains("1.19")) {
                        setHeaditeam("LEGACY_SKULL_ITEM");
                        }
                        ItemStack skull = new ItemStack(Objects.requireNonNull(Material.getMaterial(headiteam)), 1, (short) 3);
                        SkullMeta meta = (SkullMeta) skull.getItemMeta();
                        List<String> lore = Main.getInstance().getConfig().getStringList("Menus.ateamhead.lore");

                        List<String> replacedLore = lore.stream()
                                                        .map(s -> s.replace("&", "§"))
                                                        .collect(Collectors.toList());
                        Objects.requireNonNull(meta).setOwner(name);
                        String headdisplayname = Objects.requireNonNull(Main.getInstance().getConfig()
                                                                            .getString("Menus.ateamhead.displayname"))
                                                        .replace("%staffchat_ateamname%", name);

                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', headdisplayname));
                        meta.setLore(replacedLore.stream()
                                                 .map(s -> s.replace("%staffchat_playerdisplayname%", players.getDisplayName()))
                                                 .collect(Collectors.toList()));
                        skull.setItemMeta(meta);

                        adminteam.addItem(skull);
                    }
                }
            }
            player.openInventory(adminteam);
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fUnkown command"));
        }
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {

        String title = Main.getInstance().getConfig().getString("Menus.title");
        int slot = Main.getInstance().getConfig().getInt("Menus.closeitem.slot");
        if (e.getView()
             .getTitle()
             .equalsIgnoreCase(org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(title)))) {
            if (e.getCurrentItem() == null) return;
            if (e.getCurrentItem() == null) return;

            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();

            if (e.getSlot() == slot) {
                player.closeInventory();
            }

        }
    }

    private ChatColor getColorWithString(String color) {
        if (color.equalsIgnoreCase("&0")) {
            return ChatColor.BLACK;
        } else if (color.equalsIgnoreCase("&1")) {
            return ChatColor.DARK_BLUE;
        } else if (color.equalsIgnoreCase("&2")) {
            return ChatColor.DARK_GREEN;
        } else if (color.equalsIgnoreCase("&3")) {
            return ChatColor.DARK_AQUA;
        } else if (color.equalsIgnoreCase("&4")) {
            return ChatColor.DARK_RED;

        } else if (color.equalsIgnoreCase("&5")) {
            return ChatColor.DARK_PURPLE;
        } else if (color.equalsIgnoreCase("&6")) {
            return ChatColor.GOLD;
        } else if (color.equalsIgnoreCase("&7")) {
            return ChatColor.GRAY;
        } else if (color.equalsIgnoreCase("&8")) {
            return ChatColor.DARK_GRAY;
        } else if (color.equalsIgnoreCase("&9")) {
            return ChatColor.BLUE;
        } else if (color.equalsIgnoreCase("&c")) {
            return ChatColor.RED;
        } else if (color.equalsIgnoreCase("&e")) {
            return ChatColor.YELLOW;
        } else if (color.equalsIgnoreCase("&a")) {
            return ChatColor.GREEN;
        } else if (color.equalsIgnoreCase("&b")) {
            return ChatColor.AQUA;
        } else if (color.equalsIgnoreCase("&f")) {
            return ChatColor.WHITE;
        } else if (color.equalsIgnoreCase("&d")) {
            return ChatColor.LIGHT_PURPLE;
        }
        return null;
    }
}