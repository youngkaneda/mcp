package ifpb.gpes.io;

import ifpb.gpes.Call;
import ifpb.gpes.ExportManager;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 07/07/2017, 15:33:48
 */
public class FileExportManager implements ExportManager {

    private String name;

    protected FileExportManager(String name) {
        this.name = name;
    }

    public static ExportManager name(String name) {
        return new FileExportManager(name);
    }

    @Override
    public void export(List<Call> elements) {
        String text = elements.stream()
                .map(Call::callGraph)
                .collect(Collectors.joining("\n"));

        write(text);
    }

    protected void write(String text) {
        Path path = Paths.get(name);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(text);
        } catch (IOException ex) {
            Logger.getLogger(FileExportManager.class.getName()).log(Level.SEVERE, "problem write file", ex);
        }
    }
}
