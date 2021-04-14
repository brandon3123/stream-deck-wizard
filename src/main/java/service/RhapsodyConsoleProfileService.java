package service;

import enums.name.action.ActionName;
import enums.name.folder.FolderName;
import enums.name.profile.ProfileName;
import model.action.Action;
import model.common.Actions;
import model.common.Manifest;
import model.profile.RhapsodyConsoleProfile;
import model.tunnel.RhapsodyConsole;
import org.apache.commons.collections.CollectionUtils;
import scriptgenerator.impl.RhapsodyConsoleScriptGenerator;
import util.FileUtil;

import java.io.File;
import java.util.List;

public class RhapsodyConsoleProfileService {
    private RhapsodyConsoleScriptGenerator scriptGenerator = new RhapsodyConsoleScriptGenerator();
    private RhapsodyActionService actionService = new RhapsodyActionService();
    private ManifestService manifestService = new ManifestService();

    public void createRhapsodyProfile(RhapsodyConsoleProfile profile, File topPath, Manifest topManifest) {
        List<RhapsodyConsole> rhapsodyConsoles = profile.getRhapsodyConsoles();

        if (CollectionUtils.isNotEmpty(rhapsodyConsoles)) {
            File topProfileDirectory = FileUtil.createProfilesDirectoryIfNotPresentAtPath(topPath);

            File rhapsodyDirectory = FileUtil.createDirectoryIfNotPresentAtPath(topProfileDirectory, ProfileName.RHAPSODY.profileName());

            Actions topManifestActions = Actions.builder()
                    .action1_0(actionService.openChildAction(FolderName.RHAPSODY.folderName(), FolderName.RHAPSODY.folderName()))
                    .build();

            topManifest.setActions(topManifestActions);

            FileUtil.createDirectoryIfNotPresentAtPath(topPath, ActionName.ACTION_1_0.getName());

            File rhapsodyScriptsDirectory = FileUtil.createDirectoryIfNotPresentAtPath(rhapsodyDirectory, FolderName.SCRIPTS.folderName());

            Manifest rhapsodyManifest = new Manifest(null, "Rhapsody Profile");

            Actions.Builder actionsBuilder = Actions
                    .builder()
                    .action0_0(actionService.backToParentAction());

            for (RhapsodyConsole rhapsodyConsole : rhapsodyConsoles) {
                File specificConsoleDirectory = FileUtil.getDirectoryInPath(rhapsodyScriptsDirectory, rhapsodyConsole.getName());
                FileUtil.createDirectoryIfNotPresent(specificConsoleDirectory);
                File rhapsodyTunnelScript = scriptGenerator.generateScript(rhapsodyConsole, specificConsoleDirectory);
                Action rhapsodyAction = actionService.rhapsodyConsoleMultiAction(rhapsodyTunnelScript.getAbsolutePath(), rhapsodyConsole.getName());
                actionsBuilder = actionsBuilder.nextSpot(rhapsodyAction);
            }

            Actions actions = actionsBuilder.build();

            FileUtil.buildDirectoriesAtPathForActions(rhapsodyDirectory, actions);

            rhapsodyManifest.setActions(actions);

            manifestService.createManifestAtPath(rhapsodyDirectory, rhapsodyManifest);
        }
    }
}