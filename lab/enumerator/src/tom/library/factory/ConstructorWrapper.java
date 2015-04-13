package tom.library.factory;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * This class wraps the java.lang.reflect.Constructor object It adds some
 * functionalities like getting the curried representation of the constructor
 * 
 * @author Ahmad
 * 
 */
public class ConstructorWrapper<T> {

    /**
     * the wrapped constructor object it must be set on instantiation
     */
    private final Constructor<T> constructor;
    
    /**
     * The parsed class containing this constructor
     */
    private ParsedClass declaringClass;
    
    /**
     * represents list of parameters of the constructor
     */
    private final List<ParamWrapper> parameters;

    /**
     * name of the variable representing this constructor in the generated
     * Factory
     */
    private String variableName;

    /**
     * name of the enumuration generated from this constructor
     */
    private String enumName;

    /**
     * instantiating ConstructorWrapper to wrap a constructor that have
     * parameters
     * 
     * @param cons
     *            The constructor object to wrap
     * @param index
     *            The index of the constructor in the constructor in the
     *            constructors list of the class
     * @param declaringClass
     *            The parsed class containing this constructor
     */
    public ConstructorWrapper(Constructor<T> cons, int index, ParsedClass declaringClass) {
        this.constructor = cons;
        this.declaringClass = declaringClass;
        this.variableName = "cons" + (index + 1);
        this.enumName = this.variableName + "Enum";
        this.parameters = new ArrayList<ParamWrapper>();
        for (int i = 0; i < cons.getParameterTypes().length; i++) {
            this.parameters.add(ParamFactory.createParamWrapper(cons.getParameterTypes()[i],
                i,
                this));
        }
    }

    /**
     * getter method for the underlying constructor
     * 
     * @return java.lang.reflect.Constructor
     */
    public Constructor<T> getConstructor() {
        return constructor;
    }
    
    /**
     * getter method for the declaring class
     * @return the parsed class which declared this constructor
     */
    public ParsedClass getDeclaringClass() {
        return declaringClass;
    }

    /**
     * getter for parameters
     * 
     * @return list of ParameterWrapper object corresponding to the constructor
     *         parameters
     */
    public List<ParamWrapper> getParameters() {
        return parameters;
    }

    /**
     * getter for variable name
     */
    public String getVariableName() {
        return variableName;
    }

    /**
     * getter for enum name
     * 
     * @return name of the enumeration generated from this constructor
     */
    public String getEnumName() {
        return enumName;
    }

    /**
     * generates a string representing the type of the constructor in curried
     * form i.e. F<arg1, F<arg2,...,F<argn, T>...> for a constructor with n
     * arguments that generates object T
     * 
     * @param index
     *            the starting point of currying. index 0 start from first
     *            argument (i.e. returns F<arg1, F<arg2,...,F<argn, T>...>)
     *            index 1 start from second argument(i.e. returns
     *            F<arg2,..,F<argn, T>..>) and so on...
     * @return the type of the constructor in curried form
     */
    public String getCurriedType(int index) {

        StringBuilder curriedType = new StringBuilder();
        curriedType.append("F<");
        curriedType.append(this.parameters.get(index).getType());
        curriedType.append(", ");
        if (index == this.parameters.size() - 1) {
            // last input parameter, so append the output (of type T)
            curriedType.append(this.constructor.getName());
        } else {
            // partial application, append the currying of the output (a new function)
            curriedType.append(this.getCurriedType(index + 1));
        }
        curriedType.append(">");
        return curriedType.toString();
    }

    /**
     * generates the representation of curried constructor call
     * 
     * @param index
     *            the starting point of currying.
     * @return string representing curried function application of the
     *         constructor
     * @see getCurriedType(int index)
     */
    public String getCurriedDefinition(int index) {
        StringBuilder curriedDef = new StringBuilder();

        if (index == this.parameters.size() - 1) {
            // last parameter, apply the constructor with all the parameters
            curriedDef.append("public ");
            curriedDef.append(this.constructor.getName());
            curriedDef.append(" apply(final ");
            curriedDef.append(this.parameters.get(index).getType());
            curriedDef.append(" ");
            curriedDef.append(this.parameters.get(index).getName());
            curriedDef.append(") { return new ");
            curriedDef.append(this.constructor.getName());
            curriedDef.append("(");

            for (ParamWrapper param: parameters) {
                curriedDef.append(param.getName());
                curriedDef.append(", ");
            }
            curriedDef.replace(curriedDef.length()-2,curriedDef.length(),"); }");
        } else {
            // partial application
            curriedDef.append("public ");
            curriedDef.append(this.getCurriedType(index + 1));
            curriedDef.append(" apply(final ");
            curriedDef.append(this.parameters.get(index).getType());
            curriedDef.append(" ");
            curriedDef.append(this.parameters.get(index).getName());
            curriedDef.append(") { return new ");
            curriedDef.append(this.getCurriedType(index + 1));
            curriedDef.append("() {");
            curriedDef.append(this.getCurriedDefinition(index + 1));
            curriedDef.append(" }; }");
        }
        return curriedDef.toString();

    }

    /**
     * generates the apply expression for applying the constructor
     * 
     * @return String represents the application statement of the constructor
     */
    public String getEnumerationConstruction() {
        String applyExpr = "Enumeration.singleton(" + this.variableName + ")";

        for (ParamWrapper param: parameters) {
            applyExpr = "Enumeration.apply(" + applyExpr + ", " + param.getName() + "Enum)";
        }
//        for (int i = 0; i < this.parameters.size(); i++) {
//            applyExpr = "Enumeration.apply(" + applyExpr + ", " + this.parameters.get(i).getName() + "Enum)";
//        }

        return applyExpr;

    }

}
