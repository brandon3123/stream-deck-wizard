package model.action;

import model.common.State;
import model.routine.DelayRoutine;
import model.routine.LaunchRhapsodyConsoleBrowserRoutine;
import model.routine.RhapsodyConsoleTunnelRoutine;

import java.util.Arrays;

public class RhapsodyConsoleMultiAction extends MultiAction {
    public RhapsodyConsoleMultiAction(
            String scriptPath,
            String title) {
        super(
                new Settings(
                        Arrays.asList(
                                new RhapsodyConsoleTunnelRoutine(scriptPath, title),
                                new DelayRoutine(),
                                new LaunchRhapsodyConsoleBrowserRoutine()
                        )
                ),
                0,
                Arrays.asList(State.builder().build()));
    }
}
