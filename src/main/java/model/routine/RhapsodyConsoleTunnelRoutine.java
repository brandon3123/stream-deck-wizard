package model.routine;

import enums.name.routine.RoutineName;
import enums.uuid.SystemUUID;
import model.common.State;

import java.util.Arrays;

public class RhapsodyConsoleTunnelRoutine extends Routine {
    public RhapsodyConsoleTunnelRoutine(String scriptPath, String title) {
        super(RoutineName.OPEN.getName(),
                0,
                new RoutineSettings(true, "." + scriptPath, 0),
                0,
                Arrays.asList(
                        new State("", "", "", "", "", title, "", "", "")
                ),
                SystemUUID.OPEN.uuid());
    }
}
