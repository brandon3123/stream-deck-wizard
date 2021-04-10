package model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Stream Deck uses an upper camel case strategy.
 */
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class Actions {

    private String Name;
    private String Settings;

    public Actions(String name, String settings) {
        Name = name;
        Settings = settings;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSettings() {
        return Settings;
    }

    public void setSettings(String settings) {
        Settings = settings;
    }

    @Override
    public String toString() {
        return "Actions{" +
                "Name='" + Name + '\'' +
                ", Settings='" + Settings + '\'' +
                '}';
    }
}
