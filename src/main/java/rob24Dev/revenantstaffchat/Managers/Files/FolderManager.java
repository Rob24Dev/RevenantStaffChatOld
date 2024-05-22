package rob24Dev.revenantstaffchat.Managers.Files;



import org.bukkit.Bukkit;

import java.io.File;
import java.util.Objects;

public class FolderManager {

    public static void setup() {
        File logsFolder = new File(Objects.requireNonNull(Bukkit.getServer()
                                                                .getPluginManager()
                                                                .getPlugin("RevenantStaffCH"))
                                          .getDataFolder(), "logs");

        if (!logsFolder.exists()) {
            logsFolder.mkdirs();
        }
    }
}