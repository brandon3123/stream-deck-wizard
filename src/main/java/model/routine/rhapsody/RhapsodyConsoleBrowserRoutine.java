package model.routine.rhapsody;

import model.routine.system.WebsiteBrowserRoutine;

public class RhapsodyConsoleBrowserRoutine extends WebsiteBrowserRoutine {
    private static final String CONSOLE_URL = "http://localhost:8081";

    public RhapsodyConsoleBrowserRoutine() {
        super(CONSOLE_URL);
    }
}
