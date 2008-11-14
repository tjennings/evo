package com.evo;

import com.evo.visitor.NodeVisitor;

public class Constant implements Symbol{
    private float value;

    public Constant(float value) {
        this.value = value;
    }

    public float result() {
        return this.value;
    }

    public void setVar(String name, float value) {
        //Not implemented for this type
    }

    public String toString() {
       return String.valueOf(value);
    }

    public int length() {
       return 1;
    }

    public Symbol clone() {
        return this; //Constants are immutable
    }

    public void visitBy(NodeVisitor visitor) {
        visitor.visit(this);
    }
}
