package service;

import enums.name.action.ActionName;
import enums.name.folder.FolderName;
import enums.name.profile.ProfileName;
import model.action.Action;
import model.common.Actions;
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
    private ActionService actionService = new ActionService();
    private ManifestService manifestService = new ManifestService();

    public void createClientTunnelProfile(ClientTunnelProfile profile, File topPath, Manifest topManifest) {
        if (CollectionUtils.isNotEmpty(profile.getClientTunnels())) {
            File profilesTopDirectory = FileUtil.createProfilesDirectoryIfNotPresentAtPath(topPath);
            File clientTunnelsProfileDirectory = FileUtil.createDirectoryIfNotPresentAtPath(profilesTopDirectory, ProfileName.CLIENTS.profileName());

            File clientsTopProfileDirectory = FileUtil.createProfilesDirectoryIfNotPresentAtPath(clientTunnelsProfileDirectory);

            // Top manifest file, move this later.
            Actions topManifestActions = Actions.builder()
                    .action0_0(actionService.openChildAction(FolderName.CLIENTS.folderName(), FolderName.CLIENTS.folderName()))
                    .action1_0(topManifest.getActions().getAction1_0())
                    .build();

            topManifest.setActions(topManifestActions);

            FileUtil.createDirectoryIfNotPresentAtPath(topPath, ActionName.ACTION_0_0.getName());

            for (ClientTunnel clientTunnel : profile.getClientTunnels()) {
                String clientTunnelProfileUUID = clientTunnel.getClient() + ProfileName.POST_FIX.profileName();
                File clientProfileDirectory = FileUtil.createDirectoryIfNotPresentAtPath(clientsTopProfileDirectory, clientTunnelProfileUUID);

                File clientDirectory = FileUtil.createDirectoryIfNotPresentAtPath(clientProfileDirectory, clientTunnel.getClient());

                File clientScriptsDirectory = FileUtil.createDirectoryIfNotPresentAtPath(clientDirectory, FolderName.SCRIPTS.folderName());

                Manifest clientsTopManifest = new Manifest(null, clientTunnel.getClient() + " Profile");

                Actions.Builder clientActionsBuilder = Actions
                        .builder()
                        .action0_0(actionService.backToParentAction());

                // Create a new open folder action for a specific client.
                Action openClientAction = actionService.openChildAction(clientTunnelProfileUUID, clientTunnel.getClient());
                clientActionsBuilder = clientActionsBuilder.nextSpot(openClientAction);

                for (ClientServer clientServer : clientTunnel.getTunnels()) {
                    File clientEnvironment = FileUtil.createDirectoryIfNotPresentAtPath(clientScriptsDirectory, clientServer.getEnvironment());

                    for (ServerDetails serverDetails : clientServer.getServers()) {
                        File clientTunnelScript = scriptGenerator.generateScript(clientServer, serverDetails, clientEnvironment);
                    }
                }

                //create buttons inside each client directory

                Actions clientActions = clientActionsBuilder.build();

                clientsTopManifest.setActions(clientActions);

                manifestService.createManifestAtPath(clientProfileDirectory, clientsTopManifest);
            }

        }
    }
}