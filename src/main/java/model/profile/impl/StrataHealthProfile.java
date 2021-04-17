package model.profile.impl;

import model.profile.ClientTunnelProfile;
import model.profile.RhapsodyConsoleProfile;
import model.profile.StreamDeckProfile;
import model.tunnel.client.ClientTunnel;
import model.tunnel.rhapsody.RhapsodyConsole;

import java.util.List;

public class StrataHealthProfile extends StreamDeckProfile implements RhapsodyConsoleProfile, ClientTunnelProfile {
    private List<ClientTunnel> clientTunnels;
    private List<RhapsodyConsole> rhapsodyConsoles;

    @Override
    public List<RhapsodyConsole> getRhapsodyConsoles() {
        return rhapsodyConsoles;
    }

    @Override
    public void setRhapsodyConsoles(List<RhapsodyConsole> rhapsodyConsoles) {
        this.rhapsodyConsoles = rhapsodyConsoles;
    }

    @Override
    public List<ClientTunnel> getClientTunnels() {
        return clientTunnels;
    }

    @Override
    public void setClientTunnels(List<ClientTunnel> clientTunnels) {
        this.clientTunnels = clientTunnels;
    }

    @Override
    public String toString() {
        return "StrataHealthProfile{" +
                "clientTunnels=" + clientTunnels +
                ", rhapsodyConsoles=" + rhapsodyConsoles +
                "} " + super.toString();
    }
}