package model.routine;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import model.common.State;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Routine {

    private String name;
    private int overrideState;
    private RoutineSettings settings;
    private int state;
    private List<State> states;
    private String uuid;

    public Routine(String name, int overrideState, RoutineSettings settings, int state, List<State> states, String uuid) {
        this.name = name;
        this.overrideState = overrideState;
        this.settings = settings;
        this.state = state;
        this.states = states;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOverrideState() {
        return overrideState;
    }

    public void setOverrideState(int overrideState) {
        this.overrideState = overrideState;
    }

    public RoutineSettings getSettings() {
        return settings;
    }

    public void setSettings(RoutineSettings settings) {
        this.settings = settings;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public String getUuid() {
        return uuid;
    }

    @JsonProperty(value = "UUID")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Routine{" +
                "name='" + name + '\'' +
                ", overrideState=" + overrideState +
                ", settings=" + settings +
                ", state='" + state + '\'' +
                ", states=" + states +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}