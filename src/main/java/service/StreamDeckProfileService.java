package service;

import model.common.Actions;
import model.common.Manifest;
import model.profile.impl.StrataHealthProfile;
import util.FileUtil;

import java.io.File;

public class StreamDeckProfileService {
    private ManifestService manifestService = new ManifestService();
    private RhapsodyConsoleProfileService rhapsodyConsoleProfileService = new RhapsodyConsoleProfileService();
    private ClientTunnelProfileService clientTunnelProfileService = new ClientTunnelProfileService();

    public void createProfileFromConfig(StrataHealthProfile profile) {
        File testDirectory = new File(profile.getProfileLocation() + "test.sdProfile");
        FileUtil.createDirectoryIfNotPresent(testDirectory);

        Actions actions = Actions.builder()
                .nextSpot(clientTunnelProfileService.createClientTunnelProfile(profile, testDirectory))
                .nextSpot(rhapsodyConsoleProfileService.createRhapsodyProfile(profile, testDirectory))
                .build();

        Manifest manifest = new Manifest(actions, profile.getName());

        manifestService.createManifestAtPathAndFolderElgatoStructure(testDirectory, manifest);
    }
}