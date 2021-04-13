package util;

import enums.config.DotEnvProperty;
import io.github.cdimascio.dotenv.Dotenv;

public class DotEnvUtil {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getDeviceModel() {
        return dotenv.get(DotEnvProperty.DEVICE_MODEL.name());
    }

    public static String getDeviceUUID() {
        return dotenv.get(DotEnvProperty.DEVICE_UUID.name());
    }
}