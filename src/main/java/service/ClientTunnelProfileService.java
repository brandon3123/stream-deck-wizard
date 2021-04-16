package service;

import enums.name.action.ActionName;
import enums.name.folder.FolderName;
import enums.name.profile.ProfileName;
import model.action.Action;
import model.action.ClientTunnelMultiAction;
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
import java.util.ArrayList;

public class ClientTunnelProfileService {
    private ClientTunnelScriptGenerator scriptGenerator = new ClientTunnelScriptGenerator();
    private ActionService actionService = new ActionService();
    private ManifestService manifestService = new ManifestService();

    public void createClientTunnelProfile(ClientTunnelProfile profile, File topPath, Manifest topManifest) {
        if (CollectionUtils.isNotEmpty(profile.getClientTunnels())) {
            File profilesTopDirectory = FileUtil.createProfilesDirectoryIfNotPresentAtPath(topPath);
            File clientTunnelsProfileDirectory = FileUtil.createDirectoryIfNotPresentAtPath(profilesTopDirectory, ProfileName.CLIENTS.profileName());

            // Top manifest file, move this later.
            Actions topManifestActions = Actions.builder()
                    .action0_0(actionService.openChildAction(FolderName.CLIENTS.folderName(), FolderName.CLIENTS.folderName()))
                    .action1_0(topManifest.getActions().getAction1_0())
                    .build();

            topManifest.setActions(topManifestActions);

            FileUtil.createDirectoryIfNotPresentAtPath(topPath, ActionName.ACTION_0_0.getName());

            // Client Profiles Root Manifest
            Actions.Builder clientProfilesRootActionsBuilder = Actions.builder()
                    .action0_0(actionService.backToParentAction());

            Manifest clientsProfilesRootManifest = new Manifest(null, FolderName.CLIENTS.folderName() + " Tunnels Profile");

            for (ClientTunnel clientTunnel : profile.getClientTunnels()) {
                String clientTunnelProfileUUID = clientTunnel.getClient() + ProfileName.POST_FIX.profileName();
                File clientProfileDirectory = FileUtil.createDirectoryIfNotPresentAtPath(profilesTopDirectory, clientTunnelProfileUUID);

                // Main Client Profile Manifest Updating
                Action openClientAction = actionService.openChildAction(clientTunnel.getClient(), clientTunnel.getClient());
                clientProfilesRootActionsBuilder.nextSpot(openClientAction);

                File clientScriptsDirectory = FileUtil.createDirectoryIfNotPresentAtPath(clientProfileDirectory, FolderName.SCRIPTS.folderName());

                Manifest clientsManifest = new Manifest(null, clientTunnel.getClient() + " Profile");

                Actions.Builder clientActionsBuilder = Actions
                        .builder()
                        .action0_0(actionService.backToParentAction());

                // Create a new open folder action for a specific client.
                for (ClientServer clientServer : clientTunnel.getTunnels()) {
                    File clientEnvironment = FileUtil.createDirectoryIfNotPresentAtPath(clientScriptsDirectory, clientServer.getEnvironment());

                    ArrayList<String> scriptPaths = new ArrayList<>();

                    for (ServerDetails serverDetails : clientServer.getServers()) {
                        File clientTunnelScript = scriptGenerator.generateScript(clientServer, serverDetails, clientEnvironment);
                        scriptPaths.add(clientTunnelScript.getAbsolutePath());
                    }

                    ClientTunnelMultiAction clientTunnelMultiAction = new ClientTunnelMultiAction(scriptPaths, clientServer.getEnvironment());
                    clientActionsBuilder.nextSpot(clientTunnelMultiAction);
                }

                //Create the actions/directory structure for the main clients root profile folder. Clients.sdProfile
                Actions clientActions = clientActionsBuilder.build();
                FileUtil.buildDirectoriesAtPathForActions(clientProfileDirectory, clientActions);
                clientsManifest.setActions(clientActions);
                manifestService.createManifestAtPath(clientProfileDirectory, clientsManifest);
            }
            //Create the actions/directory structure for the main clients root profile folder. Clients.sdProfile
            Actions clientProfilesRootActions = clientProfilesRootActionsBuilder.build();
            FileUtil.buildDirectoriesAtPathForActions(clientTunnelsProfileDirectory, clientProfilesRootActions);
            clientsProfilesRootManifest.setActions(clientProfilesRootActions);
            manifestService.createManifestAtPath(clientTunnelsProfileDirectory, clientsProfilesRootManifest);

        }
    }
}