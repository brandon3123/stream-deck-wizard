package scriptgenerator;

import org.apache.velocity.Template;

import java.io.File;

public interface ScriptGeneratorService<T> {
    void generateTunnelScripts(T profile, File directory);
    Template scriptTemplate();
}