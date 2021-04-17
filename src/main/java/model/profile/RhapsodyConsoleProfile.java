package model.profile;

import model.tunnel.rhapsody.RhapsodyConsole;

import java.util.List;

public interface RhapsodyConsoleProfile {
    List<RhapsodyConsole> getRhapsodyConsoles();
    void setRhapsodyConsoles(List<RhapsodyConsole> rhapsodyConsoles);
}