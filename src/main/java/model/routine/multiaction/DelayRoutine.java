package model.routine.multiaction;

import enums.name.routine.RoutineName;
import enums.uuid.MultiActionUUID;
import model.common.State;
import model.routine.common.Routine;
import model.routine.common.RoutineSettings;

import java.util.Arrays;

public class DelayRoutine extends Routine {
    public DelayRoutine(long delay) {
        super(RoutineName.DElAY.getName(),
                new RoutineSettings(delay),
                Arrays.asList(State.builder().build()),
                MultiActionUUID.DELAY.uuid());
    }
}
