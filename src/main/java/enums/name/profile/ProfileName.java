package enums.name.profile;

public enum ProfileName {
    POST_FIX(".sdProfile"),
    RHAPSODY("Rhapsody");

    private String profileName;

    ProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String folderName() {
        return profileName + POST_FIX.profileName;
    }
}