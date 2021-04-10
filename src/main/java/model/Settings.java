package model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Collections;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class Settings {

    private List<Routine> routine;
    private List<RoutineAlt> routineAlt = Collections.emptyList();

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

    @Override
    public String toString() {
        return "Settings{" +
                "routine=" + routine +
                ", routineAlt=" + routineAlt +
                '}';
    }
}