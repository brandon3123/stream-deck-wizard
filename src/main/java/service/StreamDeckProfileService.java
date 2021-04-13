package service;

import model.common.Manifest;
import model.profile.impl.StrataHealthProfile;
import scriptgenerator.impl.ClientTunnelScriptGenerator;
import scriptgenerator.impl.RhapsodyConsoleScriptGenerator;
import util.FileUtil;

import java.io.File;

public class StreamDeckProfileService {

    private ManifestService manifestService = new ManifestService();

    public void createProfileFromConfig(StrataHealthProfile profile) {
        Manifest manifest = new Manifest(null, profile.getName());

        File testDirectory = new File(profile.getProfileLocation() + "test.sdProfile");

        FileUtil.createDirectoryIfNotPresent(testDirectory);

        RhapsodyConsoleScriptGenerator rhapsodyGenerator = new RhapsodyConsoleScriptGenerator();
        rhapsodyGenerator.generateTunnelScripts(profile, testDirectory, manifest);

//        RhapsodyConsoleProfileGenerator rhapsodyProfileGenerator = new RhapsodyConsoleProfileGenerator();
//        rhapsodyProfileGenerator.generateProfileStructureForConfig(profile, testDirectory);

        ClientTunnelScriptGenerator clientTunnelGenerator = new ClientTunnelScriptGenerator();
//        clientTunnelGenerator.generateTunnelScripts(profile, testDirectory, manifest);

        manifestService.createManifestAtPath(testDirectory, manifest);
    }
}
