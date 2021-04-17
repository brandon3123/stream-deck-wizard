package scriptgenerator.impl;

import enums.velocity.VelocityParameter;
import model.tunnel.rhapsody.RhapsodyConsole;
import model.tunnel.common.ServerDetails;
import org.apache.velocity.VelocityContext;
import scriptgenerator.AbstractScriptGeneratorService;

import java.io.File;

public class RhapsodyConsoleScriptGenerator extends AbstractScriptGeneratorService {
    private static final String RHAPSODY_TUNNEL_TEMPLATE = "src/main/resources/templates/velocity/rhapsodyTunnel.vm";

    public File generateScript(RhapsodyConsole rhapsodyConsole, File scriptDirectory) {
        VelocityContext context = contextForRhapsodyConsole(rhapsodyConsole);
        return generateScriptAtPath(context, scriptDirectory, rhapsodyConsole.getName().toLowerCase());
    }

    @Override
    protected String templateLocation() {
        return RHAPSODY_TUNNEL_TEMPLATE;
    }

    private VelocityContext contextForRhapsodyConsole(RhapsodyConsole rhapsodyConsole) {
        VelocityContext context = new VelocityContext();
        ServerDetails rootTunnel = rhapsodyConsole.getRootTunnel();
        context.put(VelocityParameter.ROOT_USER.parameter(), rootTunnel.getUser());
        context.put(VelocityParameter.ROOT_PASSWORD.parameter(), rootTunnel.getPassword());
        context.put(VelocityParameter.ROOT_HOST.parameter(), rootTunnel.getHost());
        context.put(VelocityParameter.PORT_FORWARDS.parameter(), rhapsodyConsole.getPortForwards());
        return context;
    }
}