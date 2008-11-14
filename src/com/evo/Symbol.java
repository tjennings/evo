package com.evo;

import com.evo.visitor.NodeVisitor;

public interface Symbol extends Cloneable {

    void visitBy(NodeVisitor visitor);

    int length();
    
    float result();

    void setVar(String name, float value);
    
    Symbol clone();
}
