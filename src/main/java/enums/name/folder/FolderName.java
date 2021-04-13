package enums.name.folder;

public enum FolderName {
    PROJECTS("Projects"),
    RHAPSODY("Rhapsody"),
    SCRIPTS("scripts");

    private String folderName;

    FolderName(String folderName) {
        this.folderName = folderName;
    }

    public String folderName() {
        return folderName;
    }
}