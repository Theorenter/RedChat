package org.concordiacraft.redchat.listeners;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.concordiacraft.redchat.main.RedChat;
import org.concordiacraft.redrealms.data.RedData;
import org.concordiacraft.redrealms.data.RedPlayer;

public class ChatListener implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);

        Player p = e.getPlayer();
        RedPlayer rp = RedData.loadPlayer(p);

        TextComponent tPrefix;

        String prefix;
        String clr;
        String chatColor;


        if (p.isOp()) {
            prefix = "֋ ";
            clr = RedChat.adminColor;
            chatColor = RedChat.adminColorChat;
        } else {
            prefix = "՗ ";
            clr = RedChat.playerColor;
            chatColor = RedChat.playerColorChat;
        }

        tPrefix = new TextComponent(p.getName());
        tPrefix.setColor(ChatColor.of(clr));

        //e.setFormat(prefix + p.getName() + chatColor + ": " + e.getMessage());

        TextComponent tMessage = new TextComponent();
        tMessage.addExtra(prefix);

        HoverEvent he = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(prefix + p.getName()));

        // content
        if (rp.hasTown()) {
            String town = "\nГражданин города " + ChatColor.BLUE + rp.getTownName();
            if (rp.isMayor())
                town = "\nМэр города " + ChatColor.BLUE + rp.getTownName();
            he.addContent(new Text(town));
        }

        tPrefix.setHoverEvent(he);

        TextComponent tContent = new TextComponent(": " + e.getMessage());
        tContent.setColor(ChatColor.of(chatColor));

        tMessage.addExtra(tPrefix); tMessage.addExtra(tContent);

        // Broadcast
        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.spigot().sendMessage(tMessage);
        }

        /*TextComponent tc = new TextComponent();

        Text txt = new Text("");


        String town;
        boolean mayor = false;

        if (rp.hasTown()) {
            town = "Гражданин города " + rp.getTownName();
            if (rp.isMayor()) {
                town = "Мэр города " + rp.getTownName();
            }
            txt = new Text(txt.getValue().toString() + town);
        }


        tc.addExtra(": " + e.getMessage());
        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.spigot().sendMessage(tc);
        }*/
    }
}
