package icu.h2l.multilogin.vc.main;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import icu.h2l.multilogin.vc.impl.VelocityServer;
import icu.h2l.multilogin.vc.logger.Slf4jLoggerBridge;
import icu.h2l.api.event.connection.OnlineAuthEvent;
import lombok.Getter;
import moe.caa.multilogin.api.internal.auth.AuthResult;
import moe.caa.multilogin.api.internal.logger.LoggerProvider;
import moe.caa.multilogin.api.internal.main.MultiCoreAPI;
import moe.caa.multilogin.api.internal.plugin.IPlugin;
import moe.caa.multilogin.api.profile.GameProfile;
import moe.caa.multilogin.loader.main.PluginLoader;
import net.kyori.adventure.text.Component;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Collectors;

/**
 * Velocity Main
 */
public class MultiLoginVelocity implements IPlugin {
    @Getter
    private static MultiLoginVelocity instance;
    private final Path dataDirectory;
    @Getter
    private final ProxyServer server;
    @Getter
    private final VelocityServer runServer;
    private final PluginLoader pluginLoader;
    @Getter
    private MultiCoreAPI multiCoreAPI;

    @Inject
    public MultiLoginVelocity(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        instance = this;
        this.server =  server;
        this.runServer = new VelocityServer(this.server);
        this.dataDirectory = dataDirectory;
        LoggerProvider.setLogger(new Slf4jLoggerBridge(logger));
        this.pluginLoader = new PluginLoader(this);
        try {
            pluginLoader.load();
        } catch (Exception e) {
            LoggerProvider.getLogger().error("An exception was encountered while initializing the plugin.", e);
            server.shutdown();
        }
    }

    @Subscribe
    public void onInitialize(ProxyInitializeEvent event) {
        try {
            multiCoreAPI = pluginLoader.getCoreObject();
            multiCoreAPI.load();
        } catch (Throwable e) {
            LoggerProvider.getLogger().error("An exception was encountered while loading the plugin.", e);
            server.shutdown();
            return;
        }
        new GlobalListener(this).register();
        new CommandHandler(this).register("multilogin");
    }

    @Subscribe
    public void onLogin(OnlineAuthEvent event) {
        AuthResult result = multiCoreAPI.getAuthHandler().auth(event.getUserName(), event.getServerId(), event.getPlayerIp());
        if (result.getResult() == AuthResult.Result.ALLOW) {
            event.setGameProfile(generateGameProfile(result.getResponse()));
        } else {
            event.setAllow(false);
            event.setDisconnectMessage(Component.text(result.getKickMessage()));
        }
    }

    private com.velocitypowered.api.util.GameProfile generateGameProfile(GameProfile response) {
        return new com.velocitypowered.api.util.GameProfile(
                response.getId(),
                response.getName(),
                response.getPropertyMap().values().stream().map(s ->
                        new com.velocitypowered.api.util.GameProfile.Property(s.getName(), s.getValue(), s.getSignature())
                ).collect(Collectors.toList())
        );
    }

    @Subscribe
    public void onDisable(ProxyShutdownEvent event) {
        try {
            multiCoreAPI.close();
            pluginLoader.close();
        } catch (Exception e) {
            LoggerProvider.getLogger().error("An exception was encountered while close the plugin", e);
        } finally {
            multiCoreAPI = null;
            server.shutdown();
        }
    }

    @Override
    public File getDataFolder() {
        return dataDirectory.toFile();
    }

    @Override
    public File getTempFolder() {
        return new File(getDataFolder(), "tmp");
    }
}
