package model.routine;

import enums.name.routine.RoutineName;
import enums.uuid.MultiActionUUID;
import model.common.State;

import java.util.Arrays;

public class DelayRoutine extends Routine {
    public DelayRoutine() {
        super(RoutineName.DElAY.getName(),
                0,
                new RoutineSettings(5500),
                0,
                Arrays.asList(State.builder().build()),
                MultiActionUUID.DELAY.uuid());
    }
}
