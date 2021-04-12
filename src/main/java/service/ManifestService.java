package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.common.Manifest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ManifestService {

    private ObjectMapper mapper = new ObjectMapper();

    public void createManifestAtPath(File path, Manifest manifest) {
        try {
            String manifestJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(manifest);
            FileWriter manifestWriter = new FileWriter(path.getAbsolutePath() + "/manifest.json");

            manifestWriter.write(manifestJson);

            manifestWriter.close();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
