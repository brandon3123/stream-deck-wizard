package model.profile;

import model.tunnel.client.ClientTunnel;

import java.util.List;

public interface ClientTunnelProfile {
    List<ClientTunnel> getClientTunnels();
    void setClientTunnels(List<ClientTunnel> clientTunnels);
}