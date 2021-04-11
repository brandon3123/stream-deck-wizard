import com.fasterxml.jackson.databind.ObjectMapper;
import enums.config.DotEnvProperty;
import io.github.cdimascio.dotenv.Dotenv;
import model.common.Actions;
import model.common.Manifest;
import model.common.State;
import model.action.BackToParent;
import model.profile.impl.StrataHealthProfile;
import scriptgenerator.impl.ClientTunnelScriptGenerator;
import scriptgenerator.impl.RhapsodyConsoleScriptGenerator;
import util.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Application {

    private static final Dotenv dotenv = Dotenv.load();

    public static void main(String args[]) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File strataProfile = new File("src/main/resources/templates/configuration/profiles/strata.json");

        StrataHealthProfile strataHealthProfile = objectMapper.readValue(strataProfile, StrataHealthProfile.class);

        BackToParent backToParent = new BackToParent(null, Arrays.asList(new State("bottom")));

        Actions actions = Actions.builder()
                .action0_0(backToParent)
                .build();

        Manifest manifest = new Manifest(actions,
                DotEnvProperty.DEVICE_MODEL.name(),
                dotenv.get(DotEnvProperty.DEVICE_UUID.name()),
                "test-name",
                "1.0");

        String manifestJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(manifest);

        System.out.println(manifestJson);

        File testDirectory = new File(strataHealthProfile.getProfileLocation() + "test.sdProfile");

        FileUtil.createDirectoryIfNotPresent(testDirectory);

        FileWriter manifestWriter = new FileWriter(testDirectory.getAbsolutePath() + "/manifest.json");

        manifestWriter.write(manifestJson);

        manifestWriter.close();

        RhapsodyConsoleScriptGenerator rhapsodyGenerator = new RhapsodyConsoleScriptGenerator();
        rhapsodyGenerator.generateTunnelScripts(strataHealthProfile, testDirectory);

        ClientTunnelScriptGenerator clientTunnelGenerator = new ClientTunnelScriptGenerator();
        clientTunnelGenerator.generateTunnelScripts(strataHealthProfile, testDirectory);
    }
}
