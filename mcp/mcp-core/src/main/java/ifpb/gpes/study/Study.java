package ifpb.gpes.study;

import ifpb.gpes.io.FileExportManager;
import ifpb.gpes.io.PrintOutManager;
import ifpb.gpes.Call;
import ifpb.gpes.ExportManager;
import ifpb.gpes.Parse;
import ifpb.gpes.ParseStrategy;
import ifpb.gpes.Project;
import java.util.Collections;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 07/07/2017, 14:44:47
 */
public class Study {

    private final ExportManager strategy;
    private final Parse parse;
    private final Project project;

    public Study(Project project) {
        this(new PrintOutManager(), Parse.with(new DefaultStrategy()), project);
    }

    private Study(ExportManager strategy, Parse parse, Project project) {
        this.strategy = strategy;
        this.parse = parse;
        this.project = project;
    }

    public void execute() {
        this.strategy.export(parse.from(project));
    }

    public static Study of(Project project) {
        return new Study(project);
    }

    public Study with(Parse parse) {
        return new Study(this.strategy, parse, this.project);
    }

    public Study analysis(ExportManager strategy) {
        return new Study(strategy, this.parse, this.project);
    }

    public Study toFile(String fileName) {
        return new Study(FileExportManager.name(fileName + ".txt"), this.parse, this.project);
    }

    private static class DefaultStrategy implements ParseStrategy {

        @Override
        public List<Call> from(Project project) {
            return Collections.EMPTY_LIST;
        }

    }

}
