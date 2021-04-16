package model.action;

import model.common.State;
import model.routine.DelayRoutine;
import model.routine.LaunchRhapsodyConsoleBrowserRoutine;
import model.routine.ScriptOpenAction;

import java.util.Arrays;

public class RhapsodyConsoleMultiAction extends MultiAction {
    public RhapsodyConsoleMultiAction(
            String scriptPath,
            String title) {
        super(
                new Settings(
                        Arrays.asList(
                                new ScriptOpenAction(scriptPath, title),
                                new DelayRoutine(),
                                new LaunchRhapsodyConsoleBrowserRoutine()
                        )
                ),
                0,
                Arrays.asList(State
                        .builder()
                        .title(title)
                        .build()));
    }
}
