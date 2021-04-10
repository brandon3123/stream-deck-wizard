package model.tunnel;

public class RhapsodyConsole {

    private String name;
    private ServerDetails rootTunnel;
    private ServerDetails prod;
    private ServerDetails beta;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServerDetails getProd() {
        return prod;
    }

    public void setProd(ServerDetails prod) {
        this.prod = prod;
    }

    public ServerDetails getBeta() {
        return beta;
    }

    public void setBeta(ServerDetails beta) {
        this.beta = beta;
    }

    public ServerDetails getRootTunnel() {
        return rootTunnel;
    }

    public void setRootTunnel(ServerDetails rootTunnel) {
        this.rootTunnel = rootTunnel;
    }

    @Override
    public String toString() {
        return "RhapsodyConsole{" +
                "name='" + name + '\'' +
                ", rootTunnel=" + rootTunnel +
                ", prod=" + prod +
                ", beta=" + beta +
                '}';
    }
}