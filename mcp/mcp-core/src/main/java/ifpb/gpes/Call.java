package ifpb.gpes;

import ifpb.gpes.filter.AssignVerifier;

import java.util.Objects;

/**
 *
 * @author Juan
 */
public class Call {

    private String classType;
    private String methodName;
    private String returnType;
    private String calledInClass;
    private String calledInMethod;
    private String calledInMethodReturnType;
    private String callMethod;
    private String invokedBy;

    public Call() {
    }

    public Call(String classType, String methodName, String returnType, String calledInClass, String calledInMethod, String calledInMethodReturnType, String callMethod, String invokedBy) {
        this.classType = classType;
        this.methodName = methodName;
        this.returnType = returnType;
        this.calledInClass = calledInClass;
        this.calledInMethod = calledInMethod;
        this.calledInMethodReturnType = calledInMethodReturnType;
        this.callMethod = callMethod;
        this.invokedBy = invokedBy;
    }

    public static Call of(String classType, String methodName, String returnType, String calledInClass,
                          String calledInMethod, String calledInMethodReturnType, String callMethod, String invokedBy) {
        return new Call(classType, methodName, returnType, calledInClass, calledInMethod, calledInMethodReturnType, callMethod, invokedBy);
    }

    //TODO: refatorar
    public static Call of(String line) {
        String[] fields = line.trim().split(";");
        return new Call(fields[0].trim(), fields[1].trim(), fields[2].trim(), fields[3].trim(), fields[4].trim(), fields[5].trim(), fields[6].trim().equals("null") ? null : fields[6].trim(), fields[7].trim());
    }

    public boolean isFrom(String classe) {
        String nomeDaClasse = classType.split("<")[0];
        AssignVerifier verifier = new AssignVerifier(classe);
        return verifier.isAssignable(nomeDaClasse);
    }

    public String getInvokedBy() {
        return invokedBy;
    }

    public void setInvokedBy(String invokedBy) {
        this.invokedBy = invokedBy;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getCalledInClass() {
        return calledInClass;
    }

    public void setCalledInClass(String calledInClass) {
        this.calledInClass = calledInClass;
    }

    public String getCalledInMethod() {
        return calledInMethod;
    }

    public void setCalledInMethod(String calledInMethod) {
        this.calledInMethod = calledInMethod;
    }

    public String getCallMethod() {
        return callMethod;
    }

    public void setCallMethod(String callMethod) {
        this.callMethod = callMethod;
    }

    public String getCalledInMethodReturnType() {
        return calledInMethodReturnType;
    }

    public void setCalledInMethodReturnType(String calledInMethodReturnType) {
        this.calledInMethodReturnType = calledInMethodReturnType;
    }

    @Override
    public String toString() {
        return "Call{" + "classType=" + classType + ", methodName=" + methodName + ", returnType=" + returnType + ", calledInClass=" + calledInClass + ", calledInMethod=" + calledInMethod + ", calledInMethodReturnType=" + calledInMethodReturnType + ", callMethod=" + callMethod + ", invokedBy=" + invokedBy + '}';
    }
    
    public String callGraph() {
        return "<" + classType + "; " + methodName + "; " + returnType + "; " + calledInClass + "; " + calledInMethod + "; " + calledInMethodReturnType + "; " + callMethod + "; " + invokedBy + ">";
    }

    public String noOf() {
        return "Call.of(\"" + classType + "\"; \"" + methodName + "\";\"" + returnType + "\";\"" + calledInClass + "\";\"" + calledInMethod + "\";\"" + calledInMethodReturnType + "\";\"" + callMethod + "\";\"" + invokedBy + "\")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(classType, methodName, returnType, calledInClass, calledInMethod, calledInMethodReturnType, callMethod, invokedBy);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Call other = (Call) obj;
        if (!Objects.equals(this.classType, other.classType)) {
            return false;
        }
        if (!Objects.equals(this.methodName, other.methodName)) {
            return false;
        }
        if (!Objects.equals(this.returnType, other.returnType)) {
            return false;
        }
        if (!Objects.equals(this.calledInClass, other.calledInClass)) {
            return false;
        }
        if (!Objects.equals(this.calledInMethod, other.calledInMethod)) {
            return false;
        }
        if (!Objects.equals(this.callMethod, other.callMethod)) {
            return false;
        }
        if (!Objects.equals(this.invokedBy, other.invokedBy)) {
            return false;
        }
        return true;
    }

}
