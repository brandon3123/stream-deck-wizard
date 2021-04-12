package model.routine;

import enums.name.routine.RoutineName;
import enums.uuid.SystemUUID;
import model.common.State;

import java.util.Arrays;
import java.util.List;

public class LaunchRhapsodyConsoleBrowserRoutine extends Routine{
    public LaunchRhapsodyConsoleBrowserRoutine() {
        super(
                RoutineName.WEBSITE.getName(),
                0,
                new RoutineSettings(true, "httpL//localhost:8081", 0),
                0,
                Arrays.asList(new State()),
                SystemUUID.WEBSITE.uuid()
        );
    }
}
