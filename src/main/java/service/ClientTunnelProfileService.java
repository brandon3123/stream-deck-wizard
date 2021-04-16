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
import java.util.List;

public class ClientTunnelProfileService {
    private ClientTunnelScriptGenerator scriptGenerator = new ClientTunnelScriptGenerator();
    private ActionService actionService = new ActionService();
    private ManifestService manifestService = new ManifestService();

    public Action createClientTunnelProfile(ClientTunnelProfile profile, File topPath) {
        if (CollectionUtils.isNotEmpty(profile.getClientTunnels())) {
            File profilesTopDirectory = FileUtil.createProfilesDirectoryIfNotPresentAtPath(topPath);
            File clientTunnelsProfileDirectory = FileUtil.createDirectoryIfNotPresentAtPath(profilesTopDirectory, ProfileName.CLIENTS.profileName());

            // Client Profiles Root Manifest
            Actions.Builder clientProfilesRootActionsBuilder = Actions.builder()
                    .action0_0(actionService.backToParentAction());

            for (ClientTunnel clientTunnel : profile.getClientTunnels()) {
                String clientTunnelProfileUUID = clientTunnel.getClient() + ProfileName.POST_FIX.profileName();
                File clientProfileDirectory = FileUtil.createDirectoryIfNotPresentAtPath(profilesTopDirectory, clientTunnelProfileUUID);

                // Main Client Profile Manifest Updating
                Action openClientAction = actionService.openChildAction(clientTunnel.getClient(), clientTunnel.getClient());
                clientProfilesRootActionsBuilder.nextSpot(openClientAction);

                File clientScriptsDirectory = FileUtil.createDirectoryIfNotPresentAtPath(clientProfileDirectory, FolderName.SCRIPTS.folderName());

                Actions.Builder clientActionsBuilder = Actions
                        .builder()
                        .action0_0(actionService.backToParentAction());

                // Create Environment script actions for each client. prod/dev/beta etc.
                List<Action> clientEnvironmentMultiActions = buildClientMultiActionsForServers(clientTunnel.getTunnels(), clientScriptsDirectory);
                clientEnvironmentMultiActions.forEach(clientActionsBuilder::nextSpot);

                //Create the actions/directory structure for the main clients root profile folder. Clients.sdProfile
                Manifest clientsManifest = new Manifest(clientActionsBuilder.build(), clientTunnel.getClient() + " Profile");
                manifestService.createManifestAtPathAndFolderElgatoStructure(clientProfileDirectory, clientsManifest);
            }

            //Create the actions/directory structure for the main clients root profile folder. Clients.sdProfile
            Manifest clientsProfilesRootManifest = new Manifest(clientProfilesRootActionsBuilder.build(), FolderName.CLIENTS.folderName() + " Tunnels Profile");
            manifestService.createManifestAtPathAndFolderElgatoStructure(clientTunnelsProfileDirectory, clientsProfilesRootManifest);
        }

        return actionService.openChildAction(FolderName.CLIENTS.folderName(), FolderName.CLIENTS.folderName());
    }

    private List<Action> buildClientMultiActionsForServers(List<ClientServer> clientServers, File clientScriptsDirectory) {
        ArrayList<Action> clientTunnelMultiActions = new ArrayList<>();
        for (ClientServer clientServer : clientServers) {
            File clientEnvironment = FileUtil.createDirectoryIfNotPresentAtPath(clientScriptsDirectory, clientServer.getEnvironment());
            ArrayList<String> scriptPaths = new ArrayList<>();
            for (ServerDetails serverDetails : clientServer.getServers()) {
                File clientTunnelScript = scriptGenerator.generateScript(clientServer, serverDetails, clientEnvironment);
                scriptPaths.add(clientTunnelScript.getAbsolutePath());
            }
            clientTunnelMultiActions.add(new ClientTunnelMultiAction(scriptPaths, clientServer.getEnvironment()));
        }
        return clientTunnelMultiActions;
    }
}