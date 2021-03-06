package model.action.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import enums.manifest.JsonField;
import model.common.State;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Action {
    private String name;
    private Settings settings;
    private int state;
    private List<State> states;
    private String uuid;

    public Action(String name, Settings settings, List<State> states, String uuid) {
        this.name = name;
        this.settings = settings;
        this.states = states;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
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

    @JsonProperty(value = JsonField.JsonFieldConstant.UUID)
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Action{" +
                "name='" + name + '\'' +
                ", settings=" + settings +
                ", state=" + state +
                ", states=" + states +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}