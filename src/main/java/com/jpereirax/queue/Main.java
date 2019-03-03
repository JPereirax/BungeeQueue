package com.jpereirax.queue;

import com.jpereirax.queue.commands.ConnectCommand;
import com.jpereirax.queue.listener.GlobalListener;
import com.jpereirax.queue.manager.QueueManager;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

    private static Main instance;
    private static final QueueManager queue = new QueueManager();

    @Override
    public void onEnable() {
        instance = this;

        getProxy().getPluginManager().registerListener(this, new GlobalListener());
        getProxy().getPluginManager().registerCommand(this, new ConnectCommand());
    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }

    public static QueueManager getQueue() {
        return queue;
    }

}