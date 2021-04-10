package model.action;

import enums.name.action.ActionName;
import enums.uuid.MultiActionUUID;
import model.common.State;

import java.util.List;

public class MultiAction extends Action {
    public MultiAction(Settings settings, int state, List<State> states) {
        super(ActionName.MULTI_ACTION.getName(), settings, state, states, MultiActionUUID.ROUTINE.uuid());
    }
}