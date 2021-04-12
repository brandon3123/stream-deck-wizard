package scriptgenerator;

import model.profile.StreamDeckProfile;
import model.profile.impl.StrataHealthProfile;

import java.io.File;

public interface StreamDeckProfileGeneratorService<T> {
    void generateProfileStructureForConfig(T profile, File topPath);
}
