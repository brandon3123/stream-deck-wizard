package scriptgenerator.impl;

import enums.velocity.VelocityParameter;
import model.profile.ClientTunnelProfile;
import model.tunnel.ClientServer;
import model.tunnel.ClientTunnel;
import model.tunnel.ServerDetails;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import scriptgenerator.ScriptGeneratorService;
import util.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class ClientTunnelScriptGenerator implements ScriptGeneratorService<ClientTunnelProfile> {
    private VelocityEngine engine = new VelocityEngine();

    private static final String CLIENT_TUNNEL_TEMPLATE = "src/main/resources/templates/velocity/clientTunnel.vm";
    private static final Logger log = Logger.getLogger(ClientTunnelScriptGenerator.class.getSimpleName());

    @Override
    public void generateTunnelScripts(ClientTunnelProfile profile, File topDirectory) {
        if (CollectionUtils.isNotEmpty(profile.getClientTunnels())) {
            Template template = scriptTemplate();

            File clientTunnelsDirectory = FileUtil.getDirectoryInPath(topDirectory, "client-tunnels");
            FileUtil.createDirectoryIfNotPresent(clientTunnelsDirectory);

            for (ClientTunnel clientTunnel : profile.getClientTunnels()) {
                VelocityContext context = new VelocityContext();

                File clientDirectory = FileUtil.getDirectoryInPath(clientTunnelsDirectory, clientTunnel.getClient());
                FileUtil.createDirectoryIfNotPresent(clientDirectory);

                for (ClientServer clientServer : clientTunnel.getTunnels()) {
                    File clientEnvDirectory = FileUtil.getDirectoryInPath(clientDirectory, clientServer.getEnvironment());
                    FileUtil.createDirectoryIfNotPresent(clientEnvDirectory);

                    context.put(VelocityParameter.ROOT_USER.parameter(), clientServer.getRootTunnel().getUser());
                    context.put(VelocityParameter.ROOT_PASSWORD.parameter(), clientServer.getRootTunnel().getPassword());
                    context.put(VelocityParameter.ROOT_HOST.parameter(), clientServer.getRootTunnel().getHost());

                    for (ServerDetails serverDetails : clientServer.getServers()) {
                        context.put(VelocityParameter.CLIENT_USER.parameter(), serverDetails.getUser());
                        context.put(VelocityParameter.CLIENT_PASSWORD.parameter(), serverDetails.getPassword());
                        context.put(VelocityParameter.CLIENT_HOST.parameter(), serverDetails.getHost());

                        try(FileWriter fileWriter = new FileWriter(clientEnvDirectory.getAbsolutePath() + "/" + serverDetails.getHost().toLowerCase() + ".sh")) {
                            template.merge(context, fileWriter);
                        } catch (IOException e) {
                            log.warning(Arrays.toString(e.getStackTrace()));
                        }
                    }
                }
            }
        }
    }

    @Override
    public Template scriptTemplate() {
        return engine.getTemplate(CLIENT_TUNNEL_TEMPLATE);
    }
}