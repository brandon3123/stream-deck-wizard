package enums.name.routine;

public enum RoutineName {
    OPEN("Open");

    private String name;

    RoutineName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}