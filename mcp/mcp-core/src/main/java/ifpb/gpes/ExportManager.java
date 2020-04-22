package ifpb.gpes;

import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 07/07/2017, 15:22:14
 */
public interface ExportManager {

    public void export(List<Call> elements);

    public default String handleOutputFilePath(String dir, String filename) {
        return dir.endsWith("/") ? dir + filename: dir + '/' + filename;
    }
}
