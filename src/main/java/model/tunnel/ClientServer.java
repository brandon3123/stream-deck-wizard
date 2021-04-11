package model.tunnel;

import java.util.List;

public class ClientServer {
    private String environment;
    private ServerDetails rootTunnel;
    private List<ServerDetails> servers;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public ServerDetails getRootTunnel() {
        return rootTunnel;
    }

    public void setRootTunnel(ServerDetails rootTunnel) {
        this.rootTunnel = rootTunnel;
    }

    public List<ServerDetails> getServers() {
        return servers;
    }

    public void setServers(List<ServerDetails> servers) {
        this.servers = servers;
    }
}