package service;

import model.common.Manifest;
import model.profile.ClientTunnelProfile;
import model.tunnel.ClientServer;
import model.tunnel.ClientTunnel;
import model.tunnel.ServerDetails;
import org.apache.commons.collections.CollectionUtils;
import scriptgenerator.impl.ClientTunnelScriptGenerator;
import util.FileUtil;

import java.io.File;

public class ClientTunnelProfileService {
    private ClientTunnelScriptGenerator scriptGenerator = new ClientTunnelScriptGenerator();
    private ManifestService manifestService = new ManifestService();

    public void createClientTunnelProfile(ClientTunnelProfile profile, File topPath, Manifest topManifest) {
        if (CollectionUtils.isNotEmpty(profile.getClientTunnels())) {
            File clientTunnelsDirectory = FileUtil.getDirectoryInPath(topPath, "client-tunnels");
            FileUtil.createDirectoryIfNotPresent(clientTunnelsDirectory);

            for (ClientTunnel clientTunnel : profile.getClientTunnels()) {
                File clientDirectory = FileUtil.getDirectoryInPath(clientTunnelsDirectory, clientTunnel.getClient());
                FileUtil.createDirectoryIfNotPresent(clientDirectory);

                for (ClientServer clientServer : clientTunnel.getTunnels()) {
                    File clientEnvDirectory = FileUtil.getDirectoryInPath(clientDirectory, clientServer.getEnvironment());
                    FileUtil.createDirectoryIfNotPresent(clientEnvDirectory);

                    for (ServerDetails serverDetails : clientServer.getServers()) {
                        File clientTunnelScript = scriptGenerator.generateScript(clientServer, serverDetails, clientEnvDirectory);
                    }
                }
            }
        }
    }
}