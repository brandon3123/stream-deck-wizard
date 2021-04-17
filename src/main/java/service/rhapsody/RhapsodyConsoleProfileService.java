package service.rhapsody;

import enums.name.folder.FolderName;
import enums.name.profile.ProfileName;
import model.action.common.Action;
import model.common.Actions;
import model.common.Manifest;
import model.profile.RhapsodyConsoleProfile;
import model.tunnel.rhapsody.RhapsodyConsole;
import org.apache.commons.collections.CollectionUtils;
import scriptgenerator.impl.RhapsodyConsoleScriptGenerator;
import service.common.ManifestService;
import service.action.RhapsodyActionService;
import util.FileUtil;

import java.io.File;
import java.util.List;

public class RhapsodyConsoleProfileService {
    private RhapsodyConsoleScriptGenerator scriptGenerator = new RhapsodyConsoleScriptGenerator();
    private RhapsodyActionService actionService = new RhapsodyActionService();
    private ManifestService manifestService = new ManifestService();

    public Action createRhapsodyProfile(RhapsodyConsoleProfile profile, File topPath) {
        List<RhapsodyConsole> rhapsodyConsoles = profile.getRhapsodyConsoles();

        if (CollectionUtils.isNotEmpty(rhapsodyConsoles)) {
            File topProfileDirectory = FileUtil.createProfilesDirectoryIfNotPresentAtPath(topPath);

            File rhapsodyDirectory = FileUtil.createDirectoryIfNotPresentAtPath(topProfileDirectory, ProfileName.RHAPSODY.profileName());

            File rhapsodyScriptsDirectory = FileUtil.createDirectoryIfNotPresentAtPath(rhapsodyDirectory, FolderName.SCRIPTS.folderName());

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

            Manifest rhapsodyManifest = new Manifest(actionsBuilder.build(), ProfileName.RHAPSODY.profileName());
            manifestService.createManifestAtPathAndFolderElgatoStructure(rhapsodyDirectory, rhapsodyManifest);
        }

        return actionService.openChildAction(FolderName.RHAPSODY.folderName(), FolderName.RHAPSODY.folderName());
    }
}