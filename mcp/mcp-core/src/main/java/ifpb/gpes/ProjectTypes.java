package ifpb.gpes;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 06/07/2017, 19:05:42
 */
public enum ProjectTypes implements ProjectType {
    MAVEN {
        @Override
        public void addSources(String source) {
        }

        @Override
        public String sources() {
            return "src/main/java/";
        }
    }
}
