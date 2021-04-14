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
            FileUtil.createProfilesDirectoryIfNotPresentAtPath(topPath);
            File topProfileDirectory = FileUtil.getProfilesDirectoryInPath(topPath);

            File rhapsodyDirectory = FileUtil.getDirectoryInPath(topProfileDirectory, ProfileName.RHAPSODY.folderName());
            FileUtil.createDirectoryIfNotPresent(rhapsodyDirectory);

            Actions topManifestActions = Actions.builder()
                    .action0_1(actionService.openChildAction(FolderName.RHAPSODY.folderName(), FolderName.SCRIPTS.folderName()))
                    .build();

            topManifest.setActions(topManifestActions);

            File zero_one = FileUtil.getDirectoryInPath(topPath, ActionName.ACTION_0_1.getName());
            FileUtil.createDirectoryIfNotPresent(zero_one);

            File rhapsodyScriptsDirectory = FileUtil.getDirectoryInPath(rhapsodyDirectory, FolderName.SCRIPTS.folderName());
            FileUtil.createDirectoryIfNotPresent(rhapsodyScriptsDirectory);

            Manifest rhapsodyManifest = new Manifest(null, "Rhapsody Profile");

            for (RhapsodyConsole rhapsodyConsole : rhapsodyConsoles) {
                File specificConsoleDirectory = FileUtil.getDirectoryInPath(rhapsodyScriptsDirectory, rhapsodyConsole.getName());
                FileUtil.createDirectoryIfNotPresent(specificConsoleDirectory);

                File rhapsodyTunnelScript = scriptGenerator.generateScript(rhapsodyConsole, specificConsoleDirectory);

                Action rhapsodyAction = actionService.rhapsodyConsoleMultiAction(rhapsodyTunnelScript.getAbsolutePath(), rhapsodyConsole.getName());

                Actions actions = Actions
                        .builder()
                        .action0_1(rhapsodyAction)
                        .build();

                rhapsodyManifest.setActions(actions);
            }

            File zero_one_rhapsody = FileUtil.getDirectoryInPath(rhapsodyDirectory, ActionName.ACTION_0_1.getName());
            FileUtil.createDirectoryIfNotPresent(zero_one_rhapsody);

            manifestService.createManifestAtPath(rhapsodyDirectory, rhapsodyManifest);
        }
    }
}