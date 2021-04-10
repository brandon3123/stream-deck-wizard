package model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Stream Deck uses an upper camel case strategy.
 */
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class Manifest {

    private Actions actions;
    private String deviceModel;
    private String deviceUUID;
    private String name;
    private String version;

    public Manifest(model.Actions actions, String deviceModel, String deviceUUID, String name, String version) {
        this.actions = actions;
        this.deviceModel = deviceModel;
        this.deviceUUID = deviceUUID;
        this.name = name;
        this.version = version;
    }

    public model.Actions getActions() {
        return actions;
    }

    public void setActions(model.Actions actions) {
        this.actions = actions;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceUUID() {
        return deviceUUID;
    }

    public void setDeviceUUID(String deviceUUID) {
        this.deviceUUID = deviceUUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Manifest{" +
                "actions=" + actions +
                ", deviceModel='" + deviceModel + '\'' +
                ", deviceUUID='" + deviceUUID + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
