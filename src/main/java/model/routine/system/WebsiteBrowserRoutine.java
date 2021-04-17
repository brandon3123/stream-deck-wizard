package model.routine.system;

import enums.name.routine.RoutineName;
import enums.uuid.SystemUUID;
import model.common.State;
import model.routine.common.Routine;
import model.routine.common.RoutineSettings;

import java.util.Arrays;

public class WebsiteBrowserRoutine extends Routine {

    public WebsiteBrowserRoutine(String url) {
        super(
                RoutineName.WEBSITE.getName(),
                new RoutineSettings(true, url, 0),
                Arrays.asList(State.builder().build()),
                SystemUUID.WEBSITE.uuid()
        );
    }
}