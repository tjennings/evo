package com.evo;

import java.util.*;

public class Grammar {
    private static Random random = new Random();

    List<Function> functions = new ArrayList<Function>();
    List<Symbol> terminals = new ArrayList<Symbol>();
    List<Variable> variables = new ArrayList<Variable>();

    protected void constant(float val) {
        terminals.add(new Constant(val));
    }
    
    
    protected RandomConstantGenerator random_constants(int numRandoms) {
        return new RandomConstantGenerator(this, numRandoms);
    }

    protected void function(Function f) {
        functions.add(f);
    }

    protected void variable(String name) {
        Variable v = new Variable(name);
        terminals.add(v);
        variables.add(v);
    }

    public Symbol randomTerminal() {
        return terminals.get(random.nextInt(terminals.size()));
    }


    public Function randomFunction() {
        return functions.get(random.nextInt(functions.size()));
    }

    
    //TODO: delete this?
    public void setVarValue(String name, float value) {
        Variable v = findVar(name);
        v.setValue(value);
    }

    private Variable findVar(String name) {
        for(Variable v : variables) {
            if(name.equals(v.getName())) return v;

        }
        throw new IllegalArgumentException("Variable " + name + " is not defined in the grammar");
    }
    
    public class RandomConstantGenerator {
        private Grammar grammar;
        private int numRandoms;
        private float lowerBound;
        private float upperBound;

        private RandomConstantGenerator(Grammar aThis, int numRandoms) {
            this.grammar = aThis;
            this.numRandoms = numRandoms;
        }

        public RandomConstantGenerator range(float lowerBound) {
            this.lowerBound = lowerBound;
            return this;
        }

        public void to(float upperBound) {
            this.upperBound = upperBound;
            addRandomConstants();
        }

        private void addRandomConstants() {
            for(int i = 0; i < numRandoms; i++) {
                //(maxrandom - minrandom) * rd.nextDouble() + minrandom;
                float constant = (upperBound - lowerBound) * random.nextFloat() + lowerBound;
                grammar.constant(constant);
            }
        }
        
    }
            
}
