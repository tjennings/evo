package com.evo;

import com.evo.visitor.NodeVisitor;


public class BasicIndividual implements Individual{
    private Symbol program;
    private float error = Float.MAX_VALUE;

    public BasicIndividual(Symbol program) {
        this.program = program;
    }
    
    public int length() {
        return program.length();
    }

    public float result() {
        return program.result();
    }

    public void setError(float error) {
        this.error = error;
    }

    public float error() {
        return error;
    }

    public void setVar(String name, float value) {
        program.setVar(name, value);
    }

    public boolean isEvaluated() {
        return error != Float.MAX_VALUE;
    }
   
    public String toString() {
        return program.toString();
    }

    public Individual clone() {
        return new BasicIndividual(program.clone());
    }

    public boolean sameAs(Individual indiv) {
        return toString().equals(indiv.toString());
    }

    public void visitBy(NodeVisitor visitor) {
        program.visitBy(visitor);
    }

    public boolean betterThan(Individual other) {
        return this.error() < other.error();
    }

    public boolean worseThan(Individual other) {
        return this.error() > other.error();
    }
}
