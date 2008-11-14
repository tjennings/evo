package com.evo;

import com.evo.visitor.NodeVisitor;


public class Node implements Symbol{
    private final Function function;
    private Symbol left;
    private Symbol right;

    public Node(Function function, Symbol left, Symbol right) {
        this.function = function;
        this.left = left;
        this.right = right;
    }

    public float result() {
        return function.evaluate(left, right);
    }

    public void setVar(String name, float value) {
        left.setVar(name, value);
        right.setVar(name, value);
    }

    public String toString() {
        return "(" + left + function + right + ")";
    }

    public int length() {
        return 1 + left.length() + right.length();
    }

    public Symbol clone() {
        return new Node(function, left.clone(), right.clone());
    }

    public void visitBy(NodeVisitor visitor) {
        if(null == left || null == right)
            throw new IllegalStateException("Node cannot have null children:" + this);
        visitor.visit(this);
        visitor.visit(left);
        visitor.visit(right);
    }

    public void setLeft(Symbol sym) {
        this.left = sym;
    }

    public void setRight(Symbol sym) {
        this.right = sym;
    }
}
