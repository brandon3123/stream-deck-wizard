package service.action;

import model.action.common.Action;
import model.action.rhapsody.RhapsodyConsoleMultiAction;

public class RhapsodyActionService extends ActionService {

    public Action rhapsodyConsoleMultiAction(String rhapsodyScriptPath, String title) {
        RhapsodyConsoleMultiAction rhapsodyAction = new RhapsodyConsoleMultiAction(rhapsodyScriptPath, title);
        return rhapsodyAction;
    }
}
