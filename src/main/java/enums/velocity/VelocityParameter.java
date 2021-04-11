package enums.velocity;

public enum VelocityParameter {
    ROOT_USER("rootUser"),
    ROOT_PASSWORD("rootPassword"),
    ROOT_HOST("rootHost"),
    PORT_FORWARDS("portForwards"),
    CLIENT_USER("clientUser"),
    CLIENT_PASSWORD("clientPassword"),
    CLIENT_HOST("clientHost");

    private String parameter;

    VelocityParameter(String parameter) {
        this.parameter = parameter;
    }

    public String parameter() {
        return parameter;
    }
}