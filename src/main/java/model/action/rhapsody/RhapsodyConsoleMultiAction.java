package model.action.rhapsody;

import model.action.common.MultiAction;
import model.action.common.Settings;
import model.common.State;
import model.routine.multiaction.FiveSecondDelayRoutine;
import model.routine.rhapsody.RhapsodyConsoleBrowserRoutine;
import model.routine.common.ScriptOpenAction;

import java.util.Arrays;

public class RhapsodyConsoleMultiAction extends MultiAction {
    public RhapsodyConsoleMultiAction(
            String scriptPath,
            String title) {
        super(
                new Settings(
                        Arrays.asList(
                                new ScriptOpenAction(scriptPath, title),
                                new FiveSecondDelayRoutine(),
                                new RhapsodyConsoleBrowserRoutine()
                        )
                ),
                Arrays.asList(State
                        .builder()
                        .title(title)
                        .build()));
    }
}
