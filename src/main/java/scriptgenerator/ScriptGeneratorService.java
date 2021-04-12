package scriptgenerator;

import model.common.Manifest;
import org.apache.velocity.Template;

import java.io.File;

public interface ScriptGeneratorService<T> {
    void generateTunnelScripts(T profile, File directory, Manifest manifest);
    Template scriptTemplate();
}