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
import scriptgenerator.impl.RhapsodyConsoleProfileGenerator;
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
        Manifest manifest = new Manifest(null,
                DotEnvProperty.DEVICE_MODEL.name(),
                dotenv.get(DotEnvProperty.DEVICE_UUID.name()),
                profile.getName(),
                "1.0");


        File testDirectory = new File(profile.getProfileLocation() + "test.sdProfile");

        FileUtil.createDirectoryIfNotPresent(testDirectory);

        RhapsodyConsoleScriptGenerator rhapsodyGenerator = new RhapsodyConsoleScriptGenerator();
        rhapsodyGenerator.generateTunnelScripts(profile, testDirectory, manifest);

//        RhapsodyConsoleProfileGenerator rhapsodyProfileGenerator = new RhapsodyConsoleProfileGenerator();
//        rhapsodyProfileGenerator.generateProfileStructureForConfig(profile, testDirectory);

        ClientTunnelScriptGenerator clientTunnelGenerator = new ClientTunnelScriptGenerator();
//        clientTunnelGenerator.generateTunnelScripts(profile, testDirectory, manifest);

        String manifestJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(manifest);

        FileWriter manifestWriter = new FileWriter(testDirectory.getAbsolutePath() + "/manifest.json");

        manifestWriter.write(manifestJson);

        manifestWriter.close();
    }
}
