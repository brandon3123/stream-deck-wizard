package enums.name.profile;

public enum ProfileName {
    POST_FIX(".sdProfile"),
    RHAPSODY("Rhapsody" + POST_FIX.profileName),
    CLIENTS("Clients" + POST_FIX.profileName);

    private String profileName;

    ProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String profileName() {
        return profileName;
    }
}