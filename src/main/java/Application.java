import com.fasterxml.jackson.databind.ObjectMapper;
import enums.config.DotEnvProperty;
import io.github.cdimascio.dotenv.Dotenv;
import model.common.Actions;
import model.common.Manifest;
import model.common.State;
import model.action.BackToParent;
import model.tunnel.RhapsodyConsole;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

public class Application {

    private static final Dotenv dotenv = Dotenv.load();

    public static void main(String args[]) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File mtlRhap = new File("src/main/resources/templates/configuration/rhapsody/mtlRhap.json");

        RhapsodyConsole rhapsodyConsole = objectMapper.readValue(mtlRhap, RhapsodyConsole.class);

        System.out.println(rhapsodyConsole);

        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();

        Template template = velocityEngine.getTemplate("src/main/resources/templates/velocity/rhapsodyTunnel.vm");

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("user", rhapsodyConsole.getRootTunnel().getUser());
        velocityContext.put("password", rhapsodyConsole.getRootTunnel().getPassword());
        velocityContext.put("rootHost", rhapsodyConsole.getRootTunnel().getHost());

        velocityContext.put("prodRhapPort", rhapsodyConsole.getProd().getPort());
        velocityContext.put("prodRhapHost", rhapsodyConsole.getProd().getHost());

        velocityContext.put("betaRhapPort", rhapsodyConsole.getBeta().getPort());
        velocityContext.put("betaRhapHost", rhapsodyConsole.getBeta().getHost());


        StringWriter stringWriter = new StringWriter();
        FileWriter fileWriter = new FileWriter("src/main/resources/testRhapTunnel.sh");
        template.merge(velocityContext, fileWriter);

        System.out.println(stringWriter);

        fileWriter.close();

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

        FileWriter manifestWriter = new FileWriter("src/main/resources/testManifest.json");

        manifestWriter.write(manifestJson);

        manifestWriter.close();
    }
}
