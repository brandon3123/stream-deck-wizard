package model.tunnel;

import java.util.List;

public class ClientTunnel {
    private String client;
    private List<ClientServer> tunnels;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public List<ClientServer> getTunnels() {
        return tunnels;
    }

    public void setTunnels(List<ClientServer> tunnels) {
        this.tunnels = tunnels;
    }
}