package scriptgenerator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Logger;

public abstract class AbstractScriptGeneratorService {
    private VelocityEngine engine = new VelocityEngine();

    protected final Logger log = Logger.getLogger(this.getClass().getSimpleName());

    protected abstract String templateLocation();

    protected File generateScriptAtPath(VelocityContext context, File scriptPath, String scriptName) {
       File script = new File(scriptPath, scriptName + ".sh");
        try(FileWriter fileWriter = new FileWriter(script)) {
            Template template = engine.getTemplate(templateLocation());
            template.merge(context, fileWriter);
            Path path = Paths.get(script.getAbsolutePath());
            Set<PosixFilePermission> posixFilePermissions = Files.getPosixFilePermissions(path);
            posixFilePermissions.add(PosixFilePermission.OWNER_EXECUTE);
            Files.setPosixFilePermissions(path, posixFilePermissions);
            script = path.toFile();
        } catch (IOException e) {
            log.warning(Arrays.toString(e.getStackTrace()));
        }

        return script;
    }
}