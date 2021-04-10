package model.action;

import enums.name.action.ActionName;
import enums.uuid.ProfileUUID;
import model.common.State;

import java.util.List;

public class BackToParent extends Action {

    public BackToParent(Settings settings, List<State> states) {
        super(ActionName.OPEN_FOLDER.getName(), settings, 0, states, ProfileUUID.BACK_TO_PARENT.uuid());
    }
}