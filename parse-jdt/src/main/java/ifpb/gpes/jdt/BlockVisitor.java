package ifpb.gpes.jdt;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/06/2017, 11:20:18
 */
public class BlockVisitor extends ASTVisitor {

    private final List<No> ns;
    private final MethodDeclaration methodDeclarion;

    protected BlockVisitor(MethodDeclaration methodDeclarion, List<No> no) {
        this.methodDeclarion = methodDeclarion;
        this.ns = no;
    }

    @Override
    public boolean visit(MethodInvocation mi) {
        No no = new No();
        String a = "SAD";
        String returnType = "SADNESS";
        IMethodBinding imb = mi.resolveMethodBinding();
        ITypeBinding[] bindings = {};
        if (imb != null) {
            bindings = imb.getParameterTypes();
            a = imb.getDeclaringClass().getBinaryName();
            returnType = imb.getReturnType().getQualifiedName();
        }

        no.setA(a);
        no.setRt(returnType);

        String m = fillMethodName(mi.getName().toString(), bindings);
        no.setM(m);

        String c = methodDeclarion.resolveBinding().getDeclaringClass().getQualifiedName();
        no.setC(c);

        bindings = methodDeclarion.resolveBinding().getParameterTypes();

        String m1 = fillMethodName(methodDeclarion.getName().getIdentifier(), bindings);
        no.setM1(m1);

        ns.add(no);
        int count = ns.size();

        no.setInv(updateInv(mi));

        String methodInvocation = getMethodInvocation(count, mi.getName().toString());
        ns.get(count - 1).setMi(methodInvocation);

        return super.visit(mi);
    }

    private String updateInv(MethodInvocation mi) {
        Expression inv = mi.getExpression();

        if (inv == null) {
            return "nothing here";
        }
        
        String[] ms = inv.toString().split("\\.");
        int size = ms.length;
        
        return ms[size - 1];
    }

    private String getMethodInvocation(int count, String methodName) {
        if (count <= 1) {
//            return "null";
            return null;
        }

        if (!ns.get(count - 2).getInv().contains(methodName)) {
//            return "null";
            return null;
        }

        return ns.get(count - 2).getM();
    }

    private String fillMethodName(String methodName, ITypeBinding[] bindings) {
        String prefix = methodName + "[";
        String sufix = "]";
        return Arrays
                .asList(bindings)
                .stream()
                .map(b -> b.getQualifiedName())
                .collect(Collectors.joining(", ", prefix, sufix));
    }
}
