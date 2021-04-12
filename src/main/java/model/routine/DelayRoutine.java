package model.routine;

import enums.name.routine.RoutineName;
import enums.uuid.MultiActionUUID;
import model.common.State;

import java.util.Arrays;
import java.util.List;

public class DelayRoutine extends Routine {
    public DelayRoutine() {
        super(RoutineName.DElAY.getName(),
                0,
                new RoutineSettings(5500),
                0,
                Arrays.asList(new State()),
                MultiActionUUID.DELAY.uuid());
    }
}
