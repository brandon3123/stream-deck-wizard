package model.action;

import model.common.State;
import model.routine.ScriptOpenAction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientTunnelMultiAction extends MultiAction{
    public ClientTunnelMultiAction(
            List<String> scriptPaths,
            String title) {
        super(
                new Settings(
                        scriptPaths
                                .stream()
                                .map(path -> new ScriptOpenAction(path, title))
                                .collect(Collectors.toList())
                ),
                0,
                Arrays.asList(State
                        .builder()
                        .fSize("10")
                        .title(title)
                        .build()));
    }
}