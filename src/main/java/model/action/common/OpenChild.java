package model.action.common;

import enums.name.action.ActionName;
import enums.uuid.ProfileUUID;
import model.common.State;

import java.util.List;

public class OpenChild extends Action {

    public OpenChild(Settings settings, List<State> states) {
        super(ActionName.CREATE_FOLDER.getName(), settings, states, ProfileUUID.OPEN_CHILD.uuid());
    }
}