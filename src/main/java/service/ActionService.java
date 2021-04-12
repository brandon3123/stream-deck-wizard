package service;

import model.action.Action;
import model.action.BackToParent;
import model.action.OpenChild;
import model.action.RhapsodyConsoleMultiAction;
import model.action.Settings;
import model.common.State;

import java.util.Arrays;

public class ActionService {

    public Action openChildAction(String profileUUID, String title) {
        OpenChild openChild = new OpenChild(
                new Settings(profileUUID),
                Arrays.asList(new State("", "11", "", "off", "", title, "bottom", "", "show"))
        );
        return openChild;
    }

    public Action backToParentAction() {
        BackToParent backToParent = new BackToParent(null, Arrays.asList(new State("bottom")));
        return backToParent;
    }
}
