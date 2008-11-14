package com.evo;

import com.evo.visitor.NodeVisitor;

public interface Individual extends Cloneable {

    public boolean betterThan(Individual best);

    public void visitBy(NodeVisitor visitor);

    public boolean worseThan(Individual worst);
    
    Individual clone();
    
    boolean sameAs(Individual indiv);
    
    int length();
    
    float result();

    void setError(float error);

    float error();

    void setVar(String name, float value);

    boolean isEvaluated();
}
