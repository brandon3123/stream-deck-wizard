package enums.uuid;

public enum ProfileUUID {
    BACK_TO_PARENT("com.elgato.streamdeck.profile.backtoparent");

    private String uuid;

    ProfileUUID(String uuid) {
        this.uuid = uuid;
    }

    public String uuid() {
        return this.uuid;
    }
}