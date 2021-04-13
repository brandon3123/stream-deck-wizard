package service;

import model.action.Action;
import model.action.BackToParent;
import model.action.OpenChild;
import model.action.Settings;
import model.common.State;

import java.util.Arrays;

public class ActionService {

    public Action openChildAction(String profileUUID, String title) {
        OpenChild openChild = new OpenChild(
                new Settings(profileUUID),
                Arrays.asList(State
                        .builder()
                        .title(title)
                        .fSize("11")
                        .fUnderline("off")
                        .titleAlignment("bottom")
                        .titleShow("show")
                        .build()));
        return openChild;
    }

    public Action backToParentAction() {
        BackToParent backToParent = new BackToParent(null, Arrays.asList(State.builder().titleAlignment("bottom").build()));
        return backToParent;
    }
}
