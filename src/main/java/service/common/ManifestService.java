package service.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.common.Manifest;
import util.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ManifestService {

    private ObjectMapper mapper = new ObjectMapper();

    public void createManifestAtPath(File path, Manifest manifest) {
        try ( FileWriter manifestWriter = getManifestFileWriter(path)) {
            String manifestJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(manifest);
            manifestWriter.write(manifestJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createManifestAtPathAndFolderElgatoStructure(File path, Manifest manifest) {
        createManifestAtPath(path, manifest);
        FileUtil.buildDirectoriesAtPathForActions(path, manifest.getActions());
    }

    private FileWriter getManifestFileWriter(File path) throws IOException {
        return new FileWriter(path.getAbsolutePath() + "/manifest.json");
    }
}
