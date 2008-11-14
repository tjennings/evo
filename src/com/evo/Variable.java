package com.evo;

import com.evo.visitor.NodeVisitor;


public class Variable implements Symbol{
    private String name;
    private float value;

    public Variable(String name) {
        this.name = name;
    }
    
    public Variable(String name, float value) {
        this.name = name;
        this.value = value;
    }

    public float result() {
        return value;
    }

    public void setVar(String name, float value) {
        if(this.name.equals(name))
            this.value = value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public int length() {
        return 1;
    }

    public Symbol clone() {
        return new Variable(name, value);
    }

    public void visitBy(NodeVisitor visitor) {
        visitor.visit(this);
    }
}
