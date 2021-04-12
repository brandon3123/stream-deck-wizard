package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import enums.config.DotEnvProperty;
import io.github.cdimascio.dotenv.Dotenv;
import model.action.BackToParent;
import model.action.OpenChild;
import model.action.Settings;
import model.common.Actions;
import model.common.Manifest;
import model.common.State;
import model.profile.impl.StrataHealthProfile;
import scriptgenerator.impl.ClientTunnelScriptGenerator;
import scriptgenerator.impl.RhapsodyConsoleScriptGenerator;
import util.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class StreamDeckProfileService {

    private ObjectMapper mapper = new ObjectMapper();
    private static final Dotenv dotenv = Dotenv.load();

    public void createProfileFromConfig(StrataHealthProfile profile) throws IOException {
        BackToParent backToParent = new BackToParent(null, Arrays.asList(new State("bottom")));
        OpenChild openChild = new OpenChild(
                new Settings("6BB4FA3D-0F69-4B27-8DF6-3FBCC56D0A97"),
                Arrays.asList(new State("", "11", "", "off", "", "Rhapsody", "bottom", "", "show"))
        );

        Actions actions = Actions.builder()
                .action0_0(openChild)
//                .action0_0(backToParent)
                .build();

        Manifest manifest = new Manifest(actions,
                DotEnvProperty.DEVICE_MODEL.name(),
                dotenv.get(DotEnvProperty.DEVICE_UUID.name()),
                profile.getName(),
                "1.0");

        String manifestJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(manifest);

        File testDirectory = new File(profile.getProfileLocation() + "test.sdProfile");

        FileUtil.createDirectoryIfNotPresent(testDirectory);

        FileWriter manifestWriter = new FileWriter(testDirectory.getAbsolutePath() + "/manifest.json");

        manifestWriter.write(manifestJson);

        manifestWriter.close();

        RhapsodyConsoleScriptGenerator rhapsodyGenerator = new RhapsodyConsoleScriptGenerator();
//        rhapsodyGenerator.generateTunnelScripts(profile, testDirectory);

        ClientTunnelScriptGenerator clientTunnelGenerator = new ClientTunnelScriptGenerator();
//        clientTunnelGenerator.generateTunnelScripts(profile, testDirectory);
    }
}
