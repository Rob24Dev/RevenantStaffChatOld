package rob24Dev.revenantstaffchat.Managers.Files;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import rob24Dev.revenantstaffchat.Main;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FilesManager {

    private static File SCFile;
    private static File DCFile;
    private static File HCFile;
    private static File BCFile;
    private static FileConfiguration SCLogsFile;
    private static FileConfiguration DCLogsFile;
    private static FileConfiguration HCLogsFile;
    private static FileConfiguration BCLogsFile;

    public static void setupSCLogs() {
        SCFile = new File("plugins/RevenantStaffCH/logs/SCLogs.yml");

        if (!SCFile.exists()) {
            try {
                SCFile.createNewFile();
            } catch (IOException e) {

            }
        }
        SCLogsFile = YamlConfiguration.loadConfiguration(SCFile);
    }

    public static FileConfiguration getSCLogs() {
        return SCLogsFile;
    }

    public static void saveSCLogs() {
        try {
            SCLogsFile.save(SCFile);
        } catch (IOException e) {
            System.out.println("Couldn't save file");
        }
    }

    public static void reloadSCLogs() {
        SCLogsFile = YamlConfiguration.loadConfiguration(SCFile);
    }

    public static File SCfile() {
        return SCFile;
    }


    public static void setupDCLogs() {
        DCFile = new File("plugins/RevenantStaffCH/logs/DCLogs.yml");

        if (!DCFile.exists()) {
            try {
                DCFile.createNewFile();
            } catch (IOException e) {

            }
        }
        DCLogsFile = YamlConfiguration.loadConfiguration(DCFile);
    }

    public static FileConfiguration getDCLogs() {
        return DCLogsFile;
    }

    public static void saveDCLogs() {
        try {
            DCLogsFile.save(DCFile);
        } catch (IOException e) {
            System.out.println("Couldn't save file");
        }
    }

    public static void reloadDCLogs() {
        DCLogsFile = YamlConfiguration.loadConfiguration(DCFile);
    }

    public static File DCfile() {
        return DCFile;
    }


    public static void setupHCLogs() {
        HCFile = new File("plugins/RevenantStaffCH/logs/HCLogs.yml");

        if (!HCFile.exists()) {
            try {
                HCFile.createNewFile();
            } catch (IOException e) {

            }
        }
        HCLogsFile = YamlConfiguration.loadConfiguration(HCFile);
    }

    public static FileConfiguration getHCLogs() {
        return HCLogsFile;
    }

    public static void saveHCLogs() {
        try {
            HCLogsFile.save(HCFile);
        } catch (IOException e) {
            System.out.println("Couldn't save file");
        }
    }

    public static void reloadHCLogs() {
        HCLogsFile = YamlConfiguration.loadConfiguration(HCFile);
    }

    public static File HCfile() {
        return HCFile;
    }


    public static void setupBCLogs() {
        BCFile = new File("plugins/RevenantStaffCH/logs/BCLogs.yml");

        if (!BCFile.exists()) {
            try {
                BCFile.createNewFile();
            } catch (IOException e) {

            }
        }
        BCLogsFile = YamlConfiguration.loadConfiguration(BCFile);
    }

    public static FileConfiguration getBCLogs() {
        return BCLogsFile;
    }

    public static void saveBCLogs() {
        try {
            BCLogsFile.save(BCFile);
        } catch (IOException e) {
            System.out.println("Couldn't save file");
        }
    }

    public static void reloadBCLogs() {
        BCLogsFile = YamlConfiguration.loadConfiguration(BCFile);
    }

    public static File BCfile() {
        return BCFile;
    }

    public static void ClearChats(String chat, Player player) {
        if (chat.equalsIgnoreCase("all")) {
            SCfile().delete();
            setupSCLogs();
            saveSCLogs();
            reloadSCLogs();

            HCfile().delete();
            setupHCLogs();
            saveHCLogs();
            reloadHCLogs();

            BCfile().delete();
            setupBCLogs();
            saveBCLogs();
            reloadBCLogs();

            DCfile().delete();
            setupDCLogs();
            saveDCLogs();
            reloadDCLogs();

            String successfully = Objects.requireNonNull(Main.getInstance()
                                                             .getConfig()
                                                             .getString("Messages.commands.clearlog.successfully"))
                                         .replace("%chat%", chat);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', successfully));
        } else if(chat.equalsIgnoreCase("scchat")) {
            SCfile().delete();
            setupSCLogs();
            saveSCLogs();
            reloadSCLogs();

            String successfully = Objects.requireNonNull(Main.getInstance()
                                                             .getConfig()
                                                             .getString("Messages.commands.clearlog.successfully"))
                                         .replace("%chat%", chat);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', successfully));
        } else if(chat.equalsIgnoreCase("hcchat")) {
            HCfile().delete();
            setupHCLogs();
            saveHCLogs();
            reloadHCLogs();

            String successfully = Objects.requireNonNull(Main.getInstance()
                                                             .getConfig()
                                                             .getString("Messages.commands.clearlog.successfully"))
                                         .replace("%chat%", chat);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', successfully));
        } else if(chat.equalsIgnoreCase("bcchat")) {
            BCfile().delete();
            setupBCLogs();
            saveBCLogs();
            reloadBCLogs();

            String successfully = Objects.requireNonNull(Main.getInstance()
                                                             .getConfig()
                                                             .getString("Messages.commands.clearlog.successfully"))
                                         .replace("%chat%", chat);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', successfully));
        } else if(chat.equalsIgnoreCase("dcchat")) {
            DCfile().delete();
            setupDCLogs();
            saveDCLogs();
            reloadDCLogs();

            String successfully = Objects.requireNonNull(Main.getInstance()
                                                             .getConfig()
                                                             .getString("Messages.commands.clearlog.successfully"))
                                         .replace("%chat%", chat);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', successfully));
        } else {
            String use = Main.getInstance().getConfig().getString("Messages.commands.clearlog.use");
            String chatslist = Main.getInstance().getConfig().getString("Messages.commands.clearlog.chatslist");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(use)));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(chatslist)));
        }
    }
}
