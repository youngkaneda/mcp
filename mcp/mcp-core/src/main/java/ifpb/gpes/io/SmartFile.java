package ifpb.gpes.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/06/2017, 16:32:32
 */
public class SmartFile {

    private final Path path;

    private SmartFile(Path path) {
        this.path = path;
    }

    public static SmartFile from(Path path) {
        if (path == null) {
            throw new IllegalArgumentException("path is null");
        }
        return new SmartFile(path);
    }

    public Stream<Path> files() {
        return extension("");
    }

    public Stream<Path> extension(String extensionFile) {
        List<Path> paths = new ArrayList<>();
        
        try {
            Files.walkFileTree(this.path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (fileWithExtension(file, extensionFile)) {
                        paths.add(file);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(SmartFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paths.stream();
    }

    private boolean fileWithExtension(Path file, String extension) {
        return file.toFile()
                .getName()
                .endsWith(extension);
    }

    class FileTypesFilter implements FileFilter {

        String[] types;

        FileTypesFilter(String[] types) {
            this.types = types;
        }

        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            for (String type : types) {
                if (f.getName().endsWith(type)) {
                    return true;
                }
            }
            return false;
        }
    }
}
