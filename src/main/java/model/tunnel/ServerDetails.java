package model.tunnel;

public class ServerDetails {

    private String host;
    private String user;
    private String password;
    private long port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public long getPort() {
        return port;
    }

    public void setPort(long port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ServerDetails{" +
                "host='" + host + '\'' +
                ", username='" + user + '\'' +
                ", password='" + password + '\'' +
                ", port=" + port +
                '}';
    }
}