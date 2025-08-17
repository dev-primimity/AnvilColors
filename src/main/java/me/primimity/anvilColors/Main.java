package me.primimity.anvilColors;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);

        System.out.println("\n" +
                getDescription().getName() + " v" + getDescription().getVersion() + "\n" +
                "Created by " + getDescription().getAuthors() + "\n" +
                "Need a custom plugin? Discord me @primimity\n");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    // AnvilColors
    @EventHandler
    public void onAnvil(PrepareAnvilEvent e) {
        var p = (Player) e.getView().getPlayer();

        String rename = e.getView().getRenameText();

        var res = e.getResult();
        if (res == null) return;
        if (rename == null || rename.isBlank()) return;
        if (!p.hasPermission("anvilcolors.use")
                && getConfig().getBoolean("enablePermission", true)) return;
        var out = res.clone();
        var meta = out.getItemMeta();
        if (meta == null) return;

        String colored = ChatColor.translateAlternateColorCodes('&', rename);
        meta.setDisplayName(colored);
        out.setItemMeta(meta);

        e.setResult(out);
    }
}