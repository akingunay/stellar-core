package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.instance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema;
import uk.ac.lancaster.scc.turtles.stellar.core.util.StringBuilderHelper;

public class BSPLReferenceSchemaTestInstance extends BSPLReferenceSchema {

    protected final static String K_A = "ka";
    protected final static String K_B = "kb";
    protected final static String P_A = "pa";
    protected final static String P_B = "pb";
    protected final static String P_C = "pc";
    protected final static String P_D = "pd";
    
    
    private final List<String> canonicalParameterList;
    private final String create;
    private final String drop;
    
    public BSPLReferenceSchemaTestInstance(String name, String senderRole, String receiverRole, List<String> keyParameters, List<String> inParameters, List<String> outParameters, List<String> nilParameters) {
        super(name, senderRole, receiverRole, keyParameters, inParameters, outParameters, nilParameters);
        this.canonicalParameterList = createCanonicalParameterList();
        this.create = compileCreate();
        this.drop = compileDrop();
    }

    
    public String create() {
        return create;
    }
    
    public String drop() {
        return drop;
    }
    
    public String insert(List<List<String>> tuples) {
        StringBuilder str = new StringBuilder("INSERT INTO ");
        str.append(getName()).append("(");
        for (String parameter : canonicalParameterList) {
            str.append(parameter).append(", ");
        }
        StringBuilderHelper.trimSuffix(str, ", ").append(") VALUES ");
        for (List<String> tuple : tuples) {
            str.append("(");
            for (String value : tuple) {
                str.append(value == null ? "NULL" : "\"" + value + "\"").append(", ");
            }
            StringBuilderHelper.trimSuffix(str, ", ").append("), ");
        }
        return StringBuilderHelper.trimSuffix(str, ", ").toString();
    }
    
    public List<String> getCanonicalParameterList() {
        return Collections.unmodifiableList(canonicalParameterList);
    }
    
    private List<String> createCanonicalParameterList() {
        List<String> sortedKeys = new ArrayList<>(getKeyParameters());
        Collections.sort(sortedKeys);
        List<String> sortedIns = new ArrayList<>(getNonKeyInParameters());
        Collections.sort(sortedIns);
        List<String> sortedNils = new ArrayList<>(getNilParameters());
        Collections.sort(sortedNils);
        List<String> sortedOuts = new ArrayList<>(getNonKeyOutParameters());
        Collections.sort(sortedOuts);
        List<String> parameters = new ArrayList<>(sortedKeys);
        parameters.addAll(sortedIns);
        parameters.addAll(sortedNils);
        parameters.addAll(sortedOuts);
        return parameters;
    }
    
    private String compileCreate() {
        StringBuilder str = new StringBuilder("CREATE TABLE ");
        str.append(getName()).append("(");
        for (String p : canonicalParameterList) {
            str.append(p).append(isNilParameter(p) ? " varchar(20)," : " varchar(20) NOT NULL, ");
        }
        str.append("primary key (");
        for (String p : canonicalParameterList) {
            str.append(isKeyParameter(p) ? p + ", " : "");
        }
        return  StringBuilderHelper.trimSuffix(str, ", ").append("))").toString();
    }
    
    private String compileDrop() {
        return "DROP TABLE IF EXISTS " + getName();
    }
    
}
