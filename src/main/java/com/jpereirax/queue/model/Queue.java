package com.jpereirax.queue.model;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Queue {

    private ProxiedPlayer player;

    private ServerInfo server;

    public Queue(ProxiedPlayer player, ServerInfo server) {
        this.player = player;
        this.server = server;
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }

    public void setPlayer(ProxiedPlayer player) {
        this.player = player;
    }

    public ServerInfo getServer() {
        return server;
    }

    public void setServer(ServerInfo server) {
        this.server = server;
    }

}