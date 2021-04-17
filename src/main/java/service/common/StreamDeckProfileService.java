package service.common;

import enums.name.profile.ProfileName;
import model.common.Actions;
import model.common.Manifest;
import model.profile.impl.StrataHealthProfile;
import service.client.ClientTunnelProfileService;
import service.rhapsody.RhapsodyConsoleProfileService;
import util.FileUtil;

import java.io.File;

public class StreamDeckProfileService {
    private ManifestService manifestService = new ManifestService();
    private RhapsodyConsoleProfileService rhapsodyConsoleProfileService = new RhapsodyConsoleProfileService();
    private ClientTunnelProfileService clientTunnelProfileService = new ClientTunnelProfileService();

    public void createProfileFromConfig(StrataHealthProfile profile) {
        File profileDirectory = new File(profile.getProfileLocation(), profile.getName() + ProfileName.POST_FIX.profileName());
        FileUtil.createDirectoryIfNotPresent(profileDirectory);

        Actions actions = Actions.builder()
                .nextSpot(clientTunnelProfileService.createClientTunnelProfile(profile, profileDirectory))
                .nextSpot(rhapsodyConsoleProfileService.createRhapsodyProfile(profile, profileDirectory))
                .build();

        Manifest manifest = new Manifest(actions, profile.getName());

        manifestService.createManifestAtPathAndFolderElgatoStructure(profileDirectory, manifest);
    }
}