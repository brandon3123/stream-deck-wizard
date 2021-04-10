package enums.uuid;

public enum SystemUUID {
    OPEN("com.elgato.streamdeck.system.open"),
    WEBSITE("com.elgato.streamdeck.system.website");

    private String uuid;

    SystemUUID(String uuid) {
        this.uuid = uuid;
    }

    public String uuid() {
        return this.uuid;
    }
}