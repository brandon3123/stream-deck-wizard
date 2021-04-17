package model.routine.common;

import enums.name.routine.RoutineName;
import enums.uuid.SystemUUID;
import model.common.State;

import java.util.Arrays;

public class ScriptOpenAction extends Routine {
    public ScriptOpenAction(String scriptPath, String title) {
        super(RoutineName.OPEN.getName(),
                new RoutineSettings(true, "." + scriptPath, 0),
                Arrays.asList(State
                        .builder()
                        .title(title)
                        .build()),
                SystemUUID.OPEN.uuid());
    }
}
