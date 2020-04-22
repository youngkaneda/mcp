package ifpb.gpes.domain;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/05/2017, 21:32:40
 */
public class SampleObject {
    private HasJCFObject a = new HasJCFObject();
    public void teste() {
        a.getElements().add(new HasJCFObject());
    }

    public HasJCFObject m1() {
        return this.a;
    }
}
