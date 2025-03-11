package io.github.uttmangosteen.man10TitlePluginBungee;

import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
    @Override
    public void onEnable() {
        getProxy().registerChannel("mtitle:channel");
        getProxy().getPluginManager().registerListener(this, new MTitle());
    }
}