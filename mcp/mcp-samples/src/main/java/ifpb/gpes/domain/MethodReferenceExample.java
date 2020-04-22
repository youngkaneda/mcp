package ifpb.gpes.domain;

import java.util.function.Predicate;

public class MethodReferenceExample {

    public void chamadaComReference() {
        HasJCFObject a = new HasJCFObject();
        a.getElements().forEach(System.out::println);
    }

    public static void m1(Predicate<HasJCFObject> a) {
        new HasJCFObject().getElements().size();
    }
}
