package enums.name.action;

public enum ActionName {
    OPEN_FOLDER("Open Folder"),
    CREATE_FOLDER("Create Folder"),
    MULTI_ACTION("Multi Action");

    private String name;

    ActionName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}