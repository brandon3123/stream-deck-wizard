package service.client;

import enums.name.folder.FolderName;
import enums.name.profile.ProfileName;
import model.action.common.Action;
import model.action.client.ClientTunnelMultiAction;
import model.common.Actions;
import model.common.Manifest;
import model.profile.ClientTunnelProfile;
import model.routine.common.Routine;
import model.routine.common.ScriptOpenAction;
import model.tunnel.client.ClientServer;
import model.tunnel.client.ClientTunnel;
import model.tunnel.common.ServerDetails;
import org.apache.commons.collections.CollectionUtils;
import scriptgenerator.impl.ClientTunnelScriptGenerator;
import service.action.ActionService;
import service.common.ManifestService;
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

                // Create the actions/directory structure for the main clients root profile folder. Clients.sdProfile
                Manifest clientsManifest = new Manifest(clientActionsBuilder.build(), clientTunnel.getClient());
                manifestService.createManifestAtPathAndFolderElgatoStructure(clientProfileDirectory, clientsManifest);
            }

            // Create the actions/directory structure for the main clients root profile folder. Clients.sdProfile
            Manifest clientsProfilesRootManifest = new Manifest(clientProfilesRootActionsBuilder.build(), FolderName.CLIENTS.folderName());
            manifestService.createManifestAtPathAndFolderElgatoStructure(clientTunnelsProfileDirectory, clientsProfilesRootManifest);
        }

        return actionService.openChildAction(FolderName.CLIENTS.folderName(), FolderName.CLIENTS.folderName());
    }

    private List<Action> buildClientMultiActionsForServers(List<ClientServer> clientServers, File clientScriptsDirectory) {
        ArrayList<Action> clientTunnelMultiActions = new ArrayList<>();
        for (ClientServer clientServer : clientServers) {
            File clientEnvironment = FileUtil.createDirectoryIfNotPresentAtPath(clientScriptsDirectory, clientServer.getEnvironment());
            ArrayList<Routine> scriptRoutines = new ArrayList<>();
            for (ServerDetails serverDetails : clientServer.getServers()) {
                File clientTunnelScript = scriptGenerator.generateScript(clientServer, serverDetails, clientEnvironment);
                Routine openScriptRoutine = new ScriptOpenAction(clientTunnelScript.getAbsolutePath(), serverDetails.getHost());
                scriptRoutines.add(openScriptRoutine);
            }
            clientTunnelMultiActions.add(new ClientTunnelMultiAction(scriptRoutines, clientServer.getEnvironment()));
        }
        return clientTunnelMultiActions;
    }
}