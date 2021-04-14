package enums.name.folder;

public enum FolderName {
    PROFILES("Profiles"),
    RHAPSODY("Rhapsody"),
    CLIENTS("Clients"),
    SCRIPTS("scripts");

    private String folderName;

    FolderName(String folderName) {
        this.folderName = folderName;
    }

    public String folderName() {
        return folderName;
    }
}