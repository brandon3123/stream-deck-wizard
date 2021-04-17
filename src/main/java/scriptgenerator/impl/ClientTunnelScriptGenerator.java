package scriptgenerator.impl;

import enums.velocity.VelocityParameter;
import model.tunnel.client.ClientServer;
import model.tunnel.common.ServerDetails;
import org.apache.velocity.VelocityContext;
import scriptgenerator.AbstractScriptGeneratorService;

import java.io.File;

public class ClientTunnelScriptGenerator extends AbstractScriptGeneratorService {
    private static final String CLIENT_TUNNEL_TEMPLATE = "src/main/resources/templates/velocity/clientTunnel.vm";

    @Override
    protected String templateLocation() {
        return CLIENT_TUNNEL_TEMPLATE;
    }

    public File generateScript(ClientServer clientServer, ServerDetails serverDetails, File topPath) {
        VelocityContext context = contextForClientServer(clientServer, serverDetails);
        return generateScriptAtPath(context, topPath, serverDetails.getHost().toLowerCase());
    }

    private VelocityContext contextForClientServer(ClientServer clientServer, ServerDetails serverDetails) {
        VelocityContext context = new VelocityContext();
        context.put(VelocityParameter.ROOT_USER.parameter(), clientServer.getRootTunnel().getUser());
        context.put(VelocityParameter.ROOT_PASSWORD.parameter(), clientServer.getRootTunnel().getPassword());
        context.put(VelocityParameter.ROOT_HOST.parameter(), clientServer.getRootTunnel().getHost());
        context.put(VelocityParameter.CLIENT_USER.parameter(), serverDetails.getUser());
        context.put(VelocityParameter.CLIENT_PASSWORD.parameter(), serverDetails.getPassword());
        context.put(VelocityParameter.CLIENT_HOST.parameter(), serverDetails.getHost());
        return context;
    }
}