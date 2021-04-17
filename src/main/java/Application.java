import com.fasterxml.jackson.databind.ObjectMapper;
import model.profile.impl.StrataHealthProfile;
import service.common.StreamDeckProfileService;

import java.io.File;
import java.io.IOException;

public class Application {
    private static final String STRATA_PROFILE = "src/main/resources/templates/configuration/profiles/strata/strataProfile.json";

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File strataProfile = new File(STRATA_PROFILE);
        StrataHealthProfile strataHealthProfile = objectMapper.readValue(strataProfile, StrataHealthProfile.class);
        StreamDeckProfileService profileService = new StreamDeckProfileService();
        profileService.createProfileFromConfig(strataHealthProfile);
    }
}