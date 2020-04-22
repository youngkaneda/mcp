package ifpb.gpes.graph.io;

import ifpb.gpes.Call;
import ifpb.gpes.ExportManager;
import ifpb.gpes.filter.FilterByMethod;
import ifpb.gpes.filter.FilterClassType;
import ifpb.gpes.graph.*;
import ifpb.gpes.io.FileExportManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author juan
 */
public class BrokeExportManager implements ExportManager {

    private final String MATRIX_FILE_NAME = "matrix.csv";
    private final String METRICS_FILE_NAME = "metrics.txt";
    private final String BROKEN_FILE_NAME = "file.txt";
    private String outputDir;

    public BrokeExportManager(String outputDir) {
        this.outputDir = outputDir;
    }

    @Override
    public void export(List<Call> elements) {

        File file = Paths.get(handleOutputFilePath(outputDir, "")).toFile();
        if(!file.exists()) {
            file.mkdirs();
        }

        StringBuilder result = new StringBuilder();

        result.append("All Method Calls (").append(elements.size()).append(")\n\n");
        elements.stream().map(Call::callGraph).forEach((call) -> result.append(call).append("\n"));

        Predicate<Call> predicate = new FilterClassType("java.util.Map").or(new FilterClassType("java.util.Collection"));

        long count = elements.stream().filter(predicate).count();

        result.append("\n\nCalls That Belongs To JCF (").append(count).append(")\n\n");
        elements.stream().filter(predicate).forEach((call) -> result.append(call.callGraph()).append("\n"));

        Path path = Paths.get(handleOutputFilePath(outputDir, MATRIX_FILE_NAME));
        Graph graph = new AdapterGraph().apply(elements);

        List<Call> candidates = graph.getCandidates();

        predicate = predicate.and(new Predicate<Call>() {
            @Override
            public boolean test(Call call) {
                return elements.contains(call);
            }
        });

        candidates = candidates.stream().filter(predicate).collect(Collectors.toList());

        result.append("\n\nCalls That Are Candidatas (").append(candidates.size()).append(")\n\n");
        candidates.forEach((call) -> result.append(call.callGraph()).append("\n"));

        candidates = candidates.stream().filter(new FilterByMethod()).collect(Collectors.toList());

        List<Integer> indices = new ArrayList<>();
        Matrix matrix = graph.toMatrix();
        List<Node> columnsList = Arrays.asList(matrix.getColumns());
        List<Call> brokers = new ArrayList<>();
        for (Call candidate : candidates) {
            for (Node node : columnsList) {
                if (nodeFromCall(candidate, node)) {
                    brokers.add(candidate);
                    indices.add(columnsList.indexOf(node));
                }
            }
        }

        result.append("\n\nCalls That Broken Confinement (").append(brokers.size()).append(")\n\n");
        brokers.forEach((call) -> {
            result.append(call.callGraph()).append("\n");
        });
        write(result.toString(), Paths.get(handleOutputFilePath(outputDir, BROKEN_FILE_NAME)));
        //criando arquivos html,json,js
        new JsonMatrix(matrix).toJson(indices, handleOutputFilePath(outputDir, ""));
        //salvando matrix no arquivo
        StringBuffer buffer = new StringBuffer();
        for (int[] line : matrix.toArray()) {
            for (int column : line) {
                buffer.append(String.valueOf(column));
                buffer.append(",");
            }
            buffer.append("\n");
        }
        write(buffer.toString(), Paths.get(handleOutputFilePath(outputDir, MATRIX_FILE_NAME)));
        //salvando metricas no arquivo
        buffer = new StringBuffer();
        for (Metric metric : matrix.computeMetric()) {
            buffer.append(metric.toString());
            buffer.append("\n");
        }
        write(buffer.toString(), Paths.get(handleOutputFilePath(outputDir, METRICS_FILE_NAME)));
    }

    private void write(String text, Path path) {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(text);
        } catch (IOException ex) {
            Logger.getLogger(FileExportManager.class.getName()).log(Level.SEVERE, "Problem writing a file in " + path.getFileName().toString() + " path.");
        }
    }

    public boolean nodeFromCall(Call call, Node node) {
        if (!call.getMethodName().equals(node.getMethodName())) {
            return false;
        }
        if (!call.getClassType().equals(node.getClassName())) {
            return false;
        }
        if (!call.getReturnType().equals(node.getReturnType())) {
            return false;
        }
        if (!call.getInvokedBy().equals(node.getInvokedBy())) {
            return false;
        }
        return true;
    }
}
