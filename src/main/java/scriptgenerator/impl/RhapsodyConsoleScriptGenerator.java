package scriptgenerator.impl;

import enums.name.action.ActionName;
import enums.name.folder.FolderName;
import enums.name.profile.ProfileName;
import enums.velocity.VelocityParameter;
import model.action.Action;
import model.common.Actions;
import model.common.Manifest;
import model.profile.RhapsodyConsoleProfile;
import model.tunnel.RhapsodyConsole;
import model.tunnel.ServerDetails;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import scriptgenerator.ScriptGeneratorService;
import service.ManifestService;
import service.RhapsodyActionService;
import util.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class RhapsodyConsoleScriptGenerator implements ScriptGeneratorService<RhapsodyConsoleProfile> {
    private VelocityEngine engine = new VelocityEngine();
    private RhapsodyActionService actionService = new RhapsodyActionService();
    private ManifestService manifestService = new ManifestService();

    private static final String RHAPSODY_TUNNEL_TEMPLATE = "src/main/resources/templates/velocity/rhapsodyTunnel.vm";
    private static final Logger log = Logger.getLogger(RhapsodyConsoleScriptGenerator.class.getSimpleName());

    @Override
    public void generateTunnelScripts(RhapsodyConsoleProfile profile, File topPath, Manifest topManifest) {
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

            Template template = scriptTemplate();

            List<Action> rhapsodyActions = new ArrayList<>();

            for (RhapsodyConsole rhapsodyConsole : rhapsodyConsoles) {
                VelocityContext context = new VelocityContext();

                File specificConsoleDirectory = FileUtil.getDirectoryInPath(rhapsodyScriptsDirectory, rhapsodyConsole.getName());
                FileUtil.createDirectoryIfNotPresent(specificConsoleDirectory);

                ServerDetails rootTunnel = rhapsodyConsole.getRootTunnel();

                context.put(VelocityParameter.ROOT_USER.parameter(), rootTunnel.getUser());
                context.put(VelocityParameter.ROOT_PASSWORD.parameter(), rootTunnel.getPassword());
                context.put(VelocityParameter.ROOT_HOST.parameter(), rootTunnel.getHost());

                context.put(VelocityParameter.PORT_FORWARDS.parameter(), rhapsodyConsole.getPortForwards());

                String rhapsodyScriptPath = specificConsoleDirectory.getAbsolutePath() + "/" + rhapsodyConsole.getName().toLowerCase() + ".sh";

                try(FileWriter fileWriter = new FileWriter(rhapsodyScriptPath)) {
                    template.merge(context, fileWriter);
                    Path path = Paths.get(rhapsodyScriptPath);
                    Set<PosixFilePermission> posixFilePermissions = Files.getPosixFilePermissions(path);
                    posixFilePermissions.add(PosixFilePermission.OWNER_EXECUTE);
                    Files.setPosixFilePermissions(path, posixFilePermissions);
                } catch (IOException e) {
                    log.warning(Arrays.toString(e.getStackTrace()));
                }

                Action rhapsodyAction = actionService.rhapsodyConsoleMultiAction(rhapsodyScriptPath, rhapsodyConsole.getName());

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

    @Override
    public Template scriptTemplate() {
        return engine.getTemplate(RHAPSODY_TUNNEL_TEMPLATE);
    }
}