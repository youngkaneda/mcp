package ifpb.gpes.jdt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/06/2017, 14:49:57
 */
public class DefaultASTParser {

    private final ASTParser parser = ASTParser.newParser(AST.JLS8);
    private final String[] sources;
    private Hashtable options = JavaCore.getOptions();
    private final String[] classpath = {System.getProperty("java.home") + "/lib/rt.jar"};

    public static DefaultASTParser createParse(String[] sources) {

        if (sources == null) {
            throw new IllegalArgumentException("sources is null");
        }

        return new DefaultASTParser(sources);
    }

    public static DefaultASTParser from(String... sources) {

        if (sources == null) {
            throw new IllegalArgumentException("path is null");
        }

        return new DefaultASTParser(sources);
    }

    private DefaultASTParser(String[] sources) {
        this.sources = sources;
        this.parser.setKind(ASTParser.K_COMPILATION_UNIT);
        options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8);
        options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
    }

    public void updateUnitName(Path fileJava) {    
        try {
            byte[] readAllBytes = Files.readAllBytes(fileJava);
            String str = new String(readAllBytes);
            this.parser.setBindingsRecovery(true);
            this.parser.setResolveBindings(true);
            this.parser.setUnitName(fileJava.getFileName().toString());
            this.parser.setEnvironment(classpath, sources, new String[]{"UTF-8"}, true);
            this.parser.setSource(str.toCharArray());
        } catch (IOException ex) {
            Logger.getLogger(DefaultASTParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void acceptVisitor(ASTVisitor visitor) {
        //resolve problema do "unknow source"
        this.parser.setCompilerOptions(options);
        //--
        ASTNode createAST = parser.createAST(null);
        createAST.accept(visitor);
    }
}
