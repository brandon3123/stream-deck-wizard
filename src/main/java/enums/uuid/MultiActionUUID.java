package enums.uuid;

public enum MultiActionUUID {
    ROUTINE("com.elgato.streamdeck.multiactions.routine"),
    DELAY("com.elgato.streamdeck.multiactions.delay");

    private String uuid;

    MultiActionUUID(String uuid) {
        this.uuid = uuid;
    }

    public String uuid() {
        return this.uuid;
    }
}