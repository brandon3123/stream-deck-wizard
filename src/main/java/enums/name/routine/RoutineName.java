package enums.name.routine;

public enum RoutineName {
    OPEN("Open"),
    DElAY("Delay"),
    WEBSITE("Website");

    private String name;

    RoutineName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}