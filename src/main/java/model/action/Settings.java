package model.action;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import model.routine.Routine;
import model.routine.RoutineAlt;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Settings {

    private String profileUUID;
    private List<Routine> routine;
    private List<RoutineAlt> routineAlt;

    public Settings(String profileUUID) {
        this.profileUUID = profileUUID;
    }

    public Settings(List<Routine> routine) {
        this.routine = routine;
    }

    public List<Routine> getRoutine() {
        return routine;
    }

    public void setRoutine(List<Routine> routine) {
        this.routine = routine;
    }

    public List<RoutineAlt> getRoutineAlt() {
        return routineAlt;
    }

    public void setRoutineAlt(List<RoutineAlt> routineAlt) {
        this.routineAlt = routineAlt;
    }

    public String getProfileUUID() {
        return profileUUID;
    }

    public void setProfileUUID(String profileUUID) {
        this.profileUUID = profileUUID;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "routine=" + routine +
                ", routineAlt=" + routineAlt +
                '}';
    }
}