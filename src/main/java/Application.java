import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import enums.config.DotEnvProperty;
import io.github.cdimascio.dotenv.Dotenv;
import model.Actions;
import model.Manifest;
import model.State;
import model.action.BackToParent;

import java.util.Arrays;

public class Application {

    private static final Dotenv dotenv = Dotenv.load();

    public static void main(String args[]) throws JsonProcessingException {
        Actions actions = new Actions("testAction", "settings");

        BackToParent backToParent = new BackToParent(null, Arrays.asList(new State("bottom")));



        Manifest manifest = new Manifest(actions,
                DotEnvProperty.DEVICE_MODEL.name(),
                dotenv.get(DotEnvProperty.DEVICE_UUID.name()),
                "test-name",
                "1.0");


        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(manifest);
        String backTo = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(backToParent);
        System.out.println(json);
        System.out.println(backTo);
    }
}
