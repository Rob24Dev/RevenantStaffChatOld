package rob24Dev.revenantstaffchat;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import rob24Dev.revenantstaffchat.Commands.Commands;
import rob24Dev.revenantstaffchat.Discord.BuilderChatListener;
import rob24Dev.revenantstaffchat.Discord.DeveloperChatListener;
import rob24Dev.revenantstaffchat.Discord.HelperChatListener;
import rob24Dev.revenantstaffchat.Discord.StaffChatListener;
import rob24Dev.revenantstaffchat.Listeners.Listeners;
import rob24Dev.revenantstaffchat.Managers.Files.FilesManager;
import rob24Dev.revenantstaffchat.Managers.Files.FolderManager;
import rob24Dev.revenantstaffchat.Managers.ToggleManagers;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public final class Main extends JavaPlugin {
    public static HashMap<UUID, ToggleManagers> toggleManagersHashMap;
    public static JDA jda;
    private static Main instance;

    public static final String version = "1.4";
    public Main() {
        instance = this;
        toggleManagersHashMap = new HashMap<>();
    }

    private boolean firstenable;
    @Override
    public void onEnable() {
        // Plugin startup logic
        if (Bukkit.getVersion().contains("1.8")) {
            Pluginstart();
        } else if (Bukkit.getVersion().contains("1.9")) {
            Pluginstart();
        } else if (Bukkit.getVersion().contains("1.10")) {
            Pluginstart();
        } else if (Bukkit.getVersion().contains("1.11")) {
            Pluginstart();
        } else if (Bukkit.getVersion().contains("1.12")) {
            Pluginstart();
        } else if (Bukkit.getVersion().contains("1.13")) {
            Pluginstart();
        } else if (Bukkit.getVersion().contains("1.14")) {
            Pluginstart();
        } else if (Bukkit.getVersion().contains("1.15")) {
            Pluginstart();
        } else if (Bukkit.getVersion().contains("1.16")) {
            Pluginstart();
        } else if (Bukkit.getVersion().contains("1.17")) {
            Pluginstart();
        } else if (Bukkit.getVersion().contains("1.18")) {
            Pluginstart();
        } else if (Bukkit.getVersion().contains("1.19")) {
            Pluginstart();
        } else {
            Bukkit.getConsoleSender()
                  .sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉"));
            Bukkit.getConsoleSender()
                  .sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &b&l★★★ &6Revenant&aStaff&cChat &7v 1.0 &b&l★★★"));
            Bukkit.getConsoleSender()
                  .sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &c&lOur plugin does not support your server version!(1.8-1.19)"));
            Bukkit.getConsoleSender()
                  .sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &c&lNáš plugin nepodporuje vaši verzi serveru!(1.8-1.19)"));
            Bukkit.getConsoleSender()
                  .sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉"));
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    private void Pluginstart() {
        Bukkit.getConsoleSender()
              .sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉"));
        Bukkit.getConsoleSender()
              .sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &b&l★★★ &6Revenant&aStaff&cChat &7v 1.0 &b&l★★★"));
        Bukkit.getConsoleSender()
              .sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &7Plugin by: &6Rob24Dev"));
        Bukkit.getConsoleSender()
              .sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &7Version: &6 " + version));
        Bukkit.getConsoleSender()
              .sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &7Plugin has been &aenabled!"));
        Bukkit.getConsoleSender()
              .sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉"));
        setupFiles();
        Objects.requireNonNull(getCommand("sc")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("hc")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("dc")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("bc")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("sca")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("ateam")).setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getServer().getPluginManager().registerEvents(new Commands(), this);
        if (firstenable) {
            firstenable = false;
        } else {
            firstenable = true;
        }
        boolean enablediscord = getConfig().getBoolean("discordintegration.discord");
        if (enablediscord) {
            Discordstart();
        } else {
            Bukkit.getConsoleSender()
                  .sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Discord integration enable: &bfalse"));
        }
        if (firstenable) {
            if (Bukkit.getVersion().contains("1.13") | Bukkit.getVersion().contains("1.14") | Bukkit.getVersion()
                                                                                                    .contains("1.15") | Bukkit.getVersion()
                                                                                                                              .contains("1.16") | Bukkit.getVersion()
                                                                                                                                                        .contains("1.17") | Bukkit.getVersion()
                                                                                                                                                                                  .contains("1.18") | Bukkit.getVersion()
                                                                                                                                                                                                             .contains("1.19")){
                String pane_1 = this.getConfig().getString("Menus.pane_1.material");
                String pane_2 = this.getConfig().getString("Menus.pane_2.material");
                String pane_3 = this.getConfig().getString("Menus.pane_3.material");
                String pane_4 = this.getConfig().getString("Menus.pane_4.material");
                String pane_5 = this.getConfig().getString("Menus.pane_5.material");
                String pane_6 = this.getConfig().getString("Menus.pane_6.material");
                String pane_7 = this.getConfig().getString("Menus.pane_7.material");
                String pane_8 = this.getConfig().getString("Menus.pane_8.material");
                if (Objects.requireNonNull(pane_1).equalsIgnoreCase("STAINED_GLASS_PANE")) {
                    this.getConfig().set("Menus.pane_1.material", "WHITE_STAINED_GLASS_PANE");
                }
                if (Objects.requireNonNull(pane_2).equalsIgnoreCase("STAINED_GLASS_PANE")) {
                    this.getConfig().set("Menus.pane_2.material", "WHITE_STAINED_GLASS_PANE");
                }
                if (Objects.requireNonNull(pane_3).equalsIgnoreCase("STAINED_GLASS_PANE")) {
                    this.getConfig().set("Menus.pane_3.material", "WHITE_STAINED_GLASS_PANE");
                }
                if (Objects.requireNonNull(pane_4).equalsIgnoreCase("STAINED_GLASS_PANE")) {
                    this.getConfig().set("Menus.pane_4.material", "WHITE_STAINED_GLASS_PANE");
                }
                if (Objects.requireNonNull(pane_5).equalsIgnoreCase("STAINED_GLASS_PANE")) {
                    this.getConfig().set("Menus.pane_5.material", "WHITE_STAINED_GLASS_PANE");
                }
                if (Objects.requireNonNull(pane_6).equalsIgnoreCase("STAINED_GLASS_PANE")) {
                    this.getConfig().set("Menus.pane_6.material", "WHITE_STAINED_GLASS_PANE");
                }
                if (Objects.requireNonNull(pane_7).equalsIgnoreCase("STAINED_GLASS_PANE")) {
                    this.getConfig().set("Menus.pane_7.material", "WHITE_STAINED_GLASS_PANE");
                }
                if (Objects.requireNonNull(pane_8).equalsIgnoreCase("STAINED_GLASS_PANE")) {
                    this.getConfig().set("Menus.pane_8.material", "WHITE_STAINED_GLASS_PANE");
                }
                saveConfig();
                reloadConfig();
            }
        }
    }

    private void setupFiles() {
        boolean developerchat = this.getConfig().getBoolean("staffchats.developers");
        boolean helperchat = this.getConfig().getBoolean("staffchats.helpers");
        boolean builderchat = this.getConfig().getBoolean("staffchats.builders");
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        FolderManager.setup();
        FilesManager.setupSCLogs();
        FilesManager.saveSCLogs();
        FilesManager.reloadSCLogs();

        if(developerchat) {
            FilesManager.setupDCLogs();
            FilesManager.saveDCLogs();
            FilesManager.reloadDCLogs();
        }

        if(helperchat) {
            FilesManager.setupHCLogs();
            FilesManager.saveHCLogs();
            FilesManager.reloadHCLogs();
        }

        if(builderchat) {
            FilesManager.setupBCLogs();
            FilesManager.saveBCLogs();
            FilesManager.reloadBCLogs();
        }
    }

    public static void Discordstart() {
        String token =  instance.getConfig().getString("discordintegration.token");
            try {
                jda = (new JDABuilder(AccountType.BOT)).setToken(token).build();
                Bukkit.getConsoleSender()
                      .sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Discord integration enable: &btrue"));
            } catch (LoginException e) {
                e.printStackTrace();
            }
            jda.addEventListener(new StaffChatListener());
            jda.addEventListener(new HelperChatListener());
            jda.addEventListener(new DeveloperChatListener());
            jda.addEventListener(new BuilderChatListener());
        }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        toggleManagersHashMap.clear();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉"));
        Bukkit.getConsoleSender()
              .sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &b&l★★★ &6Revenant&aStaff&cChat &7v 1.0 &b&l★★★"));
        Bukkit.getConsoleSender()
              .sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &7Plugin has been &cdisabled!"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &7Version: &6 " + version));
        Bukkit.getConsoleSender()
              .sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉ &7Thank You for usage!"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&b▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉"));
    }

    public static Main getInstance() {
        return instance;
    }
}