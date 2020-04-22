package ifpb.gpes.domain.jcf;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 11/10/2017, 15:32:12
 */
public class Source {
 
    public void m1() {
        new Target().getEl().add(null);
        new Target().getEl().set(1, null);
        new Target().getEl().add(null);
        new Target().loop().loop().loop();
    }

    public void m2() {
        Target target = new Target();
        target.getEl().add(null);
        Runnable r = () -> target.getEl().set(1, null);
    }
}
