package enums.uuid;

public enum SystemUUID {
    OPEN("com.elgato.streamdeck.system.open"),
    HOT_KEY("com.elgato.streamdeck.system.hotkey"),
    MULTI_MEDIA("com.elgato.streamdeck.system.multimedia"),
    WEBSITE("com.elgato.streamdeck.system.website");

    private String uuid;

    SystemUUID(String uuid) {
        this.uuid = uuid;
    }

    public String uuid() {
        return this.uuid;
    }
}