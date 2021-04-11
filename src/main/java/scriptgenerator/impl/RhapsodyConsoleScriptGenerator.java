package scriptgenerator.impl;

import enums.velocity.VelocityParameter;
import model.profile.RhapsodyConsoleProfile;
import model.tunnel.RhapsodyConsole;
import model.tunnel.ServerDetails;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import scriptgenerator.ScriptGeneratorService;
import util.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class RhapsodyConsoleScriptGenerator implements ScriptGeneratorService<RhapsodyConsoleProfile> {
    private VelocityEngine engine = new VelocityEngine();

    private static final String RHAPSODY_TUNNEL_TEMPLATE = "src/main/resources/templates/velocity/rhapsodyTunnel.vm";
    private static final Logger log = Logger.getLogger(RhapsodyConsoleScriptGenerator.class.getSimpleName());

    @Override
    public void generateTunnelScripts(RhapsodyConsoleProfile profile, File topPath) {
        List<RhapsodyConsole> rhapsodyConsoles = profile.getRhapsodyConsoles();

        if (CollectionUtils.isNotEmpty(rhapsodyConsoles)) {

            File rhapsodyDirectory = FileUtil.getDirectoryInPath(topPath, "rhapsody-consoles");
            FileUtil.createDirectoryIfNotPresent(rhapsodyDirectory);

            Template template = scriptTemplate();
            for (RhapsodyConsole rhapsodyConsole : rhapsodyConsoles) {
                VelocityContext context = new VelocityContext();

                File specificConsoleDirectory = FileUtil.getDirectoryInPath(rhapsodyDirectory, rhapsodyConsole.getName());
                FileUtil.createDirectoryIfNotPresent(specificConsoleDirectory);

                ServerDetails rootTunnel = rhapsodyConsole.getRootTunnel();

                context.put(VelocityParameter.ROOT_USER.parameter(), rootTunnel.getUser());
                context.put(VelocityParameter.ROOT_PASSWORD.parameter(), rootTunnel.getPassword());
                context.put(VelocityParameter.ROOT_HOST.parameter(), rootTunnel.getHost());

                context.put(VelocityParameter.PORT_FORWARDS.parameter(), rhapsodyConsole.getPortForwards());

                try(FileWriter fileWriter = new FileWriter(specificConsoleDirectory.getAbsolutePath() + "/" + rhapsodyConsole.getName().toLowerCase() + ".sh")) {
                    template.merge(context, fileWriter);
                } catch (IOException e) {
                    log.warning(Arrays.toString(e.getStackTrace()));
                }
            }
        }
    }

    @Override
    public Template scriptTemplate() {
        return engine.getTemplate(RHAPSODY_TUNNEL_TEMPLATE);
    }
}