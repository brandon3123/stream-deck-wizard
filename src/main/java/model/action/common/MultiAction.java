package model.action.common;

import enums.name.action.ActionName;
import enums.uuid.MultiActionUUID;
import model.common.State;

import java.util.List;

public class MultiAction extends Action {
    public MultiAction(Settings settings, List<State> states) {
        super(ActionName.MULTI_ACTION.getName(), settings, states, MultiActionUUID.ROUTINE.uuid());
    }
}