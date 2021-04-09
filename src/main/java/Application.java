import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import enums.DotEnvProperty;
import io.github.cdimascio.dotenv.Dotenv;
import model.Actions;
import model.Manifest;

public class Application {

    private static final Dotenv dotenv = Dotenv.load();

    public static void main(String args[]) throws JsonProcessingException {
        Actions actions = new Actions("testAction", "settings");
        Manifest manifest = new Manifest(actions,
                DotEnvProperty.DEVICE_MODEL.name(),
                dotenv.get(DotEnvProperty.DEVICE_UUID.name()),
                "test-name",
                "1.0");
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(manifest);
        System.out.println(json);
    }
}
