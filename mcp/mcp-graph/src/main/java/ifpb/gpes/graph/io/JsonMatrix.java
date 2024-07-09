package ifpb.gpes.graph.io;

import ifpb.gpes.graph.Matrix;
import ifpb.gpes.graph.Node;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.function.IntFunction;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 25/11/2017, 09:56:38
 */
public class JsonMatrix {

    private Matrix matrix;

    public JsonMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public void toJson(List<Integer> indices, String outputDir) {
        String nodes = nodesToJson(matrix, indices);
        String edges = edgesToJson(matrix);
        generateFiles(nodes, edges, matrix.namesColumns(), outputDir);
    }

    private static String edgesToJson(Matrix matrix) {
        int[][] matrixs = matrix.toArray();
        return IntStream.range(0, matrixs.length)
            .mapToObj(x -> IntStream.range(0, matrixs.length)
                .filter(f -> matrixs[x][f] != 0)
                .mapToObj(y -> new EdgeVis(x, y, matrixs[x][y]))
                .collect(Collectors.toList()))
            .flatMap(v -> v.stream())
            .map(EdgeVis::toJson)
            .collect(Collectors.joining(", ", "[", "]"));
    }

    private String nodesToJson(Matrix matrix, List<Integer> indices) {
        String[] namesColumns = matrix.namesColumns();
        return IntStream.range(0, namesColumns.length)
            .filter(this.matrix::conectado)
            .mapToObj((i) -> {
                Node node = matrix.getColumns()[i];
                if (indices.contains(i)) {
                    return String.format("{\"id\":\"%d\", \"label\":\"%s\", \"methodName\": \"%s\", \"className\": \"%s\", \"invokedBy\": \"%s\", "
                        + "\"font\": {\"color\": \"white\", \"bold\": true}, \"color\": {\"border\": \"black\", \"background\": \"red\"}}",
                        i, i, normalizeToJson(node.getMethodName()), normalizeToJson(node.getClassName()), normalizeToJson(node.getInvokedBy()));
                }
                return String.format("{\"id\":\"%d\", \"label\":\"%s\", \"methodName\": \"%s\", \"className\": \"%s\", \"invokedBy\": \"%s\"}",
                    i, i, normalizeToJson(node.getMethodName()), normalizeToJson(node.getClassName()), normalizeToJson(node.getInvokedBy()));
            })
            .collect(Collectors.joining(", ", "[", "]"));
    }

    public String normalizeToJson(String value) {
        return value == null ? "" : value.replace("\"", "\\\"");
    }

    private void generateFiles(String nodes, String edges, String[] namesColumns, String outputDir) {
        Path script = Paths.get(outputDir + "script.js");
        Path elements = Paths.get(outputDir + "elements.json");
        Path page = Paths.get(outputDir + "graph.html");
        //
        String elementsFile = "{" + "\"nodes\":" + nodes + "," + "\"edges\":" + edges + "}";
        createJson(elementsFile, elements);
        createFileCopy(page, "graph.html");
        createFileCopy(script, "script.js");
    }

    private void createJson(String texto, Path path) {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(texto);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "problem writing file, the directory was not found or not exist.");
        }
    }

    private void createFileCopy(Path path, String resourceFilename) {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(resourceFilename);
            Files.copy(stream, path, REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "problem writing file, probably the directory was not found or not exist.");
        }
    }

    private String colunas(String[] namesColumns) {
        String collect = IntStream.range(0, namesColumns.length)
            .mapToObj(x -> String.format("%d - %s", x, namesColumns[x]))
            .collect(Collectors.joining(" "));
        return collect;
    }

    private String colunasFormatadasHtml(String[] namesColumns) {
        return IntStream.range(0, namesColumns.length)
            .mapToObj(x -> String.format("<p class=\"col-md-4\"><span class=\"badge\">%d</span> %s</p>", x, namesColumns[x]))
            .collect(Collectors.joining(""));
    }

    private static class EdgeVis {

        private final String from;
        private final String to;
        private final String label;
        private final String arrows = "to";

        public EdgeVis(String from, String to, String label) {
            this.from = from;
            this.to = to;
            this.label = label;
        }

        public EdgeVis() {
            this("", "", "");
        }

        private EdgeVis(int from, int to, int label) {
            this(String.valueOf(from),
                String.valueOf(to),
                String.valueOf(label));
        }

        @Override
        public String toString() {
            return "EdgeVis{" + "from=" + from + ", to=" + to + ", label=" + label + ", arrows=" + arrows + '}';
        }

        public String toJson() {
            return String.format("{\"from\":\"%s\", \"to\":\"%s\", \"arrows\":\"to\", \"label\":\"%s\"}", from, to, label);
        }
    }
}
