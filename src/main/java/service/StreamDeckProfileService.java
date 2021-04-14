package service;

import model.common.Manifest;
import model.profile.impl.StrataHealthProfile;
import util.FileUtil;

import java.io.File;

public class StreamDeckProfileService {
    private ManifestService manifestService = new ManifestService();

    public void createProfileFromConfig(StrataHealthProfile profile) {
        Manifest manifest = new Manifest(null, profile.getName());

        File testDirectory = new File(profile.getProfileLocation() + "test.sdProfile");

        FileUtil.createDirectoryIfNotPresent(testDirectory);

        RhapsodyConsoleProfileService rhapsodyGenerator = new RhapsodyConsoleProfileService();
        rhapsodyGenerator.createRhapsodyProfile(profile, testDirectory, manifest);

        ClientTunnelProfileService clientTunnelProfileService = new ClientTunnelProfileService();
        clientTunnelProfileService.createClientTunnelProfile(profile, testDirectory, manifest);

        manifestService.createManifestAtPath(testDirectory, manifest);
    }
}
