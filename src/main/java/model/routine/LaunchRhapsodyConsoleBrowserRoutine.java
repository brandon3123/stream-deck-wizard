package model.routine;

import enums.name.routine.RoutineName;
import enums.uuid.SystemUUID;
import model.common.State;

import java.util.Arrays;

public class LaunchRhapsodyConsoleBrowserRoutine extends Routine{
    public LaunchRhapsodyConsoleBrowserRoutine() {
        super(
                RoutineName.WEBSITE.getName(),
                0,
                new RoutineSettings(true, "http://localhost:8081", 0),
                0,
                Arrays.asList(State.builder().build()),
                SystemUUID.WEBSITE.uuid()
        );
    }
}
