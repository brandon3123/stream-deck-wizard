package scriptgenerator.impl;

import model.profile.RhapsodyConsoleProfile;
import model.tunnel.RhapsodyConsole;
import org.apache.commons.collections.CollectionUtils;
import scriptgenerator.StreamDeckProfileGeneratorService;
import util.FileUtil;

import java.io.File;
import java.util.List;

public class RhapsodyConsoleProfileGenerator implements StreamDeckProfileGeneratorService<RhapsodyConsoleProfile> {
    @Override
    public void generateProfileStructureForConfig(RhapsodyConsoleProfile profile, File topPath) {
        List<RhapsodyConsole> rhapsodyConsoles = profile.getRhapsodyConsoles();

        if (CollectionUtils.isNotEmpty(rhapsodyConsoles)) {
            File rhapsodyDirectory = FileUtil.getDirectoryInPath(topPath, "Rhapsody");
            FileUtil.createProfilesDirectoryIfNotPresentAtPath(topPath);

            for (RhapsodyConsole rhapsodyConsole : rhapsodyConsoles) {
                File specificConsoleDirectory = FileUtil.getDirectoryInPath(rhapsodyDirectory, rhapsodyConsole.getName());
            }
        }
    }
}
