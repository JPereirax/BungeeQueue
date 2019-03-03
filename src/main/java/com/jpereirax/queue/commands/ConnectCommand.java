package com.jpereirax.queue.commands;

import com.jpereirax.queue.Main;
import com.jpereirax.queue.manager.QueueManager;
import com.jpereirax.queue.model.Queue;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ConnectCommand extends Command {

    public ConnectCommand() {
        super("connect");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Only players execute this command."));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        QueueManager manager = Main.getQueue();

        if (args.length == 1) {
            ServerInfo server = Main.getInstance().getProxy().getServers().get(args[0]);
            if (server != null) {
                Queue queue = new Queue(player, server);

                if (!manager.exists(queue)) {
                    if (manager.getByValue(server).size() >= 1) {
                        manager.add(queue);
                        player.sendMessage(
                                new TextComponent(ChatColor.GREEN + "[Queue] Queue position: #" + manager.getPosition(queue)));
                    } else {
                        player.connect(server);
                    }
                } else {
                    player.sendMessage(
                            new TextComponent(ChatColor.RED + "You already in queue."));
                }
            } else {
                player.sendMessage(
                        new TextComponent(ChatColor.RED + "Server not found."));
            }
        } else {
            player.sendMessage(new TextComponent(ChatColor.RED + "Use: /connect <server>"));
        }
    }

}