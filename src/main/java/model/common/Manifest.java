package model.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import util.DotEnvUtil;

/**
 * Stream Deck uses an upper camel case strategy.
 */
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class Manifest {

    private Actions actions;
    private String deviceModel = DotEnvUtil.getDeviceModel();
    private String deviceUUID = DotEnvUtil.getDeviceUUID();
    private String name;
    private String version = "1.0";

    public Manifest(Actions actions, String name) {
        this.actions = actions;
        this.name = name;
    }

    public Actions getActions() {
        return actions;
    }

    public void setActions(Actions actions) {
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
