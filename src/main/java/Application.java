import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Actions;
import model.Manifest;

public class Application {
    public static void main(String args[]) throws JsonProcessingException {
        Actions actions = new Actions("testAction", "settings");
        Manifest manifest = new Manifest(actions,
                "test",
                "test",
                "",
                "1.0");
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(manifest);
        System.out.println(json);
    }
}
