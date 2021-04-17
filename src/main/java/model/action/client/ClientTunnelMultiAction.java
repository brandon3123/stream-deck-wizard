package model.action.client;

import model.action.common.MultiAction;
import model.action.common.Settings;
import model.common.State;
import model.routine.common.Routine;

import java.util.Arrays;
import java.util.List;

public class ClientTunnelMultiAction extends MultiAction {
    public ClientTunnelMultiAction(List<Routine> scriptOpenActions, String title) {
        super(
                new Settings(scriptOpenActions),
                Arrays.asList(State
                        .builder()
                        .fSize("10")
                        .title(title)
                        .build()));
    }
}