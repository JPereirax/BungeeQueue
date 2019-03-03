package com.jpereirax.queue.listener;

import com.jpereirax.queue.Main;
import com.jpereirax.queue.manager.QueueManager;
import com.jpereirax.queue.model.Queue;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.List;

public class GlobalListener implements Listener {

    private QueueManager manager = Main.getQueue();

    @EventHandler
    public void onServerSwitch(ServerSwitchEvent event) {
        ProxiedPlayer proxiedPlayer = event.getPlayer();

        Queue queue = manager.getByKey(proxiedPlayer);

        if (queue != null) {
            manager.remove(queue);
        }
    }

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer proxiedPlayer = event.getPlayer();

        Queue playerQueue = manager.getByKey(proxiedPlayer);
        if (manager.exists(playerQueue)) {
            manager.remove(playerQueue);
        }

        List<Queue> queues = manager.getByValue(proxiedPlayer.getServer());
        Queue queue = queues.stream().findFirst().get();

        if (queue != null) {
            ProxiedPlayer target = queue.getPlayer();
            target.connect(queue.getServer());
            target.sendMessage(new TextComponent(ChatColor.GREEN + "[Queue] Connected the server."));

            manager.remove(queue);
        }

        queues.forEach(it -> it.getPlayer()
                .sendMessage(new TextComponent(ChatColor.YELLOW + "[Queue] Queue position: #" + manager.getPosition(it))));
    }

}