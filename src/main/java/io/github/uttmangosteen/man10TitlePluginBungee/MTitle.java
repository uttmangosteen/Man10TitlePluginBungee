package io.github.uttmangosteen.man10TitlePluginBungee;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Map;

public class MTitle implements Listener {
    @EventHandler
    public void on(PluginMessageEvent e) {
        if (!e.getTag().equalsIgnoreCase("mtitle:channel")) return;
        if (!(e.getSender() instanceof Server)) return;
        ByteArrayDataInput in = ByteStreams.newDataInput(e.getData());
        String subChannel = in.readUTF();
        if (subChannel.equalsIgnoreCase("MTitle")) {
            String title = in.readUTF();
            String subTitle = in.readUTF();
            int fadeIn = in.readInt();
            int stay = in.readInt();
            int fadeOut = in.readInt();
            sendCustomData(title, subTitle, fadeIn, stay, fadeOut);
        }
    }

    public void sendCustomData(String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        Map<String, ServerInfo> servers = ProxyServer.getInstance().getServers();
        if (servers == null || servers.isEmpty()) return;
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("MTitle");
        out.writeUTF(title);
        out.writeUTF(subTitle);
        out.writeInt(fadeIn);
        out.writeInt(stay);
        out.writeInt(fadeOut);
        for (ServerInfo server : servers.values()) {
            server.sendData("mtitle:channel", out.toByteArray());
        }
    }
}