package com.evo.visitor;

import com.evo.Individual;
import com.evo.Symbol;
import java.util.Random;

public class CrossoverSelector implements NodeVisitor {

    private Random random = new Random();
    private int xOverPoint = 0;
    private int visitCount = 0;
    private Symbol crossover;
    private Individual target;

    public CrossoverSelector(Individual second) {
        this.target = second;
        
        if(second.length() > 1)
            this.xOverPoint = random.nextInt(second.length() - 1);
    }

    public void visit() {
        target.visitBy(this);
    }

    public void visit(Symbol node) {
        if (null != crossover) {
            return;
        }
        if (xOverPoint == visitCount) {
            crossover = node;
        }
        visitCount++;
        visit(node);
    }

    public Symbol getCrossover() {
        if (null == crossover) 
            throw new IllegalStateException("Could not select crossover for: " + target);
        return crossover;
    }
}
