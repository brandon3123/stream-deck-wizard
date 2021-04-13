package service;

import model.action.Action;
import model.action.RhapsodyConsoleMultiAction;

public class RhapsodyActionService extends ActionService {

    public Action rhapsodyConsoleMultiAction(String rhapsodyScriptPath, String title) {
        RhapsodyConsoleMultiAction rhapsodyAction = new RhapsodyConsoleMultiAction(rhapsodyScriptPath, title);
        return rhapsodyAction;
    }

}
