package model.tunnel;

import java.util.List;

public class RhapsodyConsole {
    private String name;
    private ServerDetails rootTunnel;
    private List<ServerDetails> portForwards;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServerDetails getRootTunnel() {
        return rootTunnel;
    }

    public void setRootTunnel(ServerDetails rootTunnel) {
        this.rootTunnel = rootTunnel;
    }

    public List<ServerDetails> getPortForwards() {
        return portForwards;
    }

    public void setPortForwards(List<ServerDetails> portForwards) {
        this.portForwards = portForwards;
    }

    @Override
    public String toString() {
        return "RhapsodyConsole{" +
                "name='" + name + '\'' +
                ", rootTunnel=" + rootTunnel +
                ", portForwards=" + portForwards +
                '}';
    }
}