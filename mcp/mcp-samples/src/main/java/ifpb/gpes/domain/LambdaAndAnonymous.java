package ifpb.gpes.domain;

import java.util.List;

/**
 *
 * @author juan
 */
public class LambdaAndAnonymous {

    private SampleObject d = new SampleObject();
    private Interface i;

    public void testeOutro() {
        this.d.teste();
    }

    public void m3() {
        i.semRetorno();
        Interface seg = () -> new HasJCFObject().getElements().iterator();
        AbstractClass b = new AbstractClass() {
            @Override
            public String m5() {
                new HasJCFObject().getElements()
                        .stream()
                        .forEach((HasJCFObject ts) -> {
                            ts.m6(ts).negate();
                            ts.m6(ts).hashCode();
                        });
                return "2";
            }
        };
        HasJCFObject a = new HasJCFObject();
        listar(a.getElements());
    }

    private void listar(List<HasJCFObject> lista) {
        lista.get(0);
    }
}
