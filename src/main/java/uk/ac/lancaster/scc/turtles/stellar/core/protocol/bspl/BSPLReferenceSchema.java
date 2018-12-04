package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BSPLReferenceSchema {

    private final String name;
    private final String sender;
    private final String receiver;
    private final List<String> keyParametes;
    private final List<String> inParameters;
    private final List<String> outParameters;
    private final List<String> nilParameters;
    private final List<String> nonKeyOutParamters;
    private final List<String> nonKeyInParamters;
    private final List<String> keyOutParameters;
    private final List<String> keyInParameters;
    private final Set<String> paramteters;
    private final List<String> nonKeyParameters;
    
    protected BSPLReferenceSchema(String name, String senderRole, String receiverRole, List<String> keyParameters,
            List<String> inParameters, List<String> outParameters, List<String> nilParameters) {
        this.name = name;
        this.sender = senderRole;
        this.receiver = receiverRole;
        this.keyParametes = new ArrayList<>(keyParameters);
        this.inParameters = new ArrayList<>(inParameters);
        this.outParameters = new ArrayList<>(outParameters);
        this.nilParameters = new ArrayList<>(nilParameters);

        this.paramteters = new HashSet<>();
        this.paramteters.addAll(this.inParameters);
        this.paramteters.addAll(this.outParameters);
        this.paramteters.addAll(this.nilParameters);

        this.nonKeyParameters = new ArrayList<>(this.paramteters);
        this.nonKeyParameters.removeAll(keyParameters);
        
        this.nonKeyOutParamters = new ArrayList<>(this.outParameters);
        this.nonKeyOutParamters.removeAll(keyParametes);

        this.nonKeyInParamters = new ArrayList<>(this.inParameters);
        this.nonKeyInParamters.removeAll(this.keyParametes);
                
        this.keyOutParameters = new ArrayList<>(this.keyParametes);
        this.keyOutParameters.retainAll(this.outParameters);
        
        this.keyInParameters = new ArrayList<>(this.keyParametes);
        this.keyInParameters.retainAll(this.inParameters);
    }
    
    private BSPLReferenceSchema(Builder builder) {
        this(builder.name, 
                builder.sender, 
                builder.receiver, 
                builder.keyParameters, 
                builder.inParameters, 
                builder.outParameters, 
                builder.nilParameters);
    }

    public String getName() {
        return name;
    }

    public boolean containsParameter(String parameter) {
        return paramteters.contains(parameter);
    }
    
    public boolean isKeyParameter(String parameter) {
        return keyParametes.contains(parameter);
    }
    
    public boolean isOutParameter(String parameter) {
        return outParameters.contains(parameter);
    }
    
    public boolean isInParameter(String parameter) {
        return inParameters.contains(parameter);
    }
    
    public boolean isNilParameter(String parameter) {
        return nilParameters.contains(parameter);
    }
    
    public List<String> getParameters() {
        return new ArrayList<>(paramteters);
    }
    
    public List<String> getNonKeyParameters() {
        return Collections.unmodifiableList(nonKeyParameters);
    }
    
    public int getNumberOfParameters() {
        return paramteters.size();
    }
    
    public List<String> getKeyParameters() {
        return Collections.unmodifiableList(keyParametes);
    }
    
    public List<String> getKeyInParameters() {
        return Collections.unmodifiableList(keyInParameters);
    }
    
    public List<String> getKeyOutParameters() {
        return Collections.unmodifiableList(keyOutParameters);
    }
    
    public List<String> getOutParameters() {
        return Collections.unmodifiableList(outParameters);
    }
    
    public List<String> getInParameters() {
        return Collections.unmodifiableList(inParameters);
    }
    
    public List<String> getNilParameters() {
        return Collections.unmodifiableList(nilParameters);
    }
    
    public List<String> getNonKeyOutParameters() {
        return Collections.unmodifiableList(nonKeyOutParamters);
    }
    
    public List<String> getNonKeyInParameters() {
        return Collections.unmodifiableList(nonKeyInParamters);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.sender);
        hash = 67 * hash + Objects.hashCode(this.receiver);
        hash = 67 * hash + Objects.hashCode(this.keyParametes);
        hash = 67 * hash + Objects.hashCode(this.inParameters);
        hash = 67 * hash + Objects.hashCode(this.outParameters);
        hash = 67 * hash + Objects.hashCode(this.nilParameters);
        return hash;
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
        final BSPLReferenceSchema other = (BSPLReferenceSchema) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.sender, other.sender)) {
            return false;
        }
        if (!Objects.equals(this.receiver, other.receiver)) {
            return false;
        }
        if (!Objects.equals(this.keyParametes, other.keyParametes)) {
            return false;
        }
        if (!Objects.equals(this.inParameters, other.inParameters)) {
            return false;
        }
        if (!Objects.equals(this.outParameters, other.outParameters)) {
            return false;
        }
        return Objects.equals(this.nilParameters, other.nilParameters);
    }
    
    static class Builder {
        
        private final String name;
        private final String sender;
        private final String receiver;
        private final List<String> keyParameters;
        private final List<String> inParameters;
        private final List<String> outParameters;
        private final List<String> nilParameters;
        
        public Builder(String name, String sender, String receiver, List<String> keyParameters, List<String> outParameters) {
            this.name = name;
            this.sender = sender;
            this.receiver = receiver;
            this.keyParameters = new ArrayList<>(keyParameters);
            this.outParameters = new ArrayList<>(outParameters);
            this.inParameters = new ArrayList<>();
            this.nilParameters = new ArrayList<>();
        }

        public Builder addKeyParameter(String parameter) {
            this.keyParameters.add(parameter);
            return this;
        }

        public Builder addOutParameter(String parameter) {
            this.outParameters.add(parameter);
            return this;
        }

        public Builder addInParameter(String parameter) {
            this.inParameters.add(parameter);
            return this;
        }

        public Builder addNilParameter(String parameter) {
            this.nilParameters.add(parameter);
            return this;
        }

        public Builder addKeyParameters(Collection<String> parameters) {
            this.keyParameters.addAll(parameters);
            return this;
        }

        public Builder addOutParameters(Collection<String> parameters) {
            this.outParameters.addAll(parameters);
            return this;
        }

        public Builder addInParameters(Collection<String> parameters) {
            this.inParameters.addAll(parameters);
            return this;
        }

        public Builder addNilParameters(Collection<String> parameters) {
            this.nilParameters.addAll(parameters);
            return this;
        }
        
        public BSPLReferenceSchema build() {
            return new BSPLReferenceSchema(this);
        }
    }
}
