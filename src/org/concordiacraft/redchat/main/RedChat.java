package org.concordiacraft.redchat.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.concordiacraft.redchat.listeners.ChatListener;
import org.concordiacraft.redrealms.main.RedRealms;
import org.concordiacraft.redutils.main.RedPlugin;
import org.concordiacraft.redutils.utils.RedLog;

public class RedChat extends JavaPlugin implements RedPlugin {

    // Fields
        // Простите за Б*гохульство...
    private static Boolean isDebug;
    private static RedLog redLog;

    public static String playerColor = "#7D828E";
    public static String playerColorChat = "#CED0D4";

    public static String adminColor = "#895BF3";
    public static String adminColorChat = "#D3C1FA";

    @Override
    public void onEnable() {
        isDebug = true;

        redLog = new RedLog(this);
        redLog.showPluginTitle();

        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean isDebug() {
        return isDebug;
    }

    @Override
    public void setDebug(boolean b) {

    }

    @Override
    public RedLog getRedLogger() {
        return redLog;
    }

    public static RedChat getPlugin()  {
        return RedRealms.getPlugin(RedChat.class);
    }
}
