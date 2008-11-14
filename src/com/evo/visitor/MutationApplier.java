package com.evo.visitor;

import com.evo.Individual;
import com.evo.Node;
import com.evo.Symbol;
import java.util.Random;

public class MutationApplier implements NodeVisitor {

    Random random = new Random();
    public Symbol crossoverNode;
    public int nodeCount = 0;
    private int nodeToMutate = 0;;
    private int visitCount = 0;
    private Individual target;

    public MutationApplier(Individual target, Symbol mutation) {
        this.target = target;
        this.crossoverNode = mutation;

        NodeCounter nCounter = new NodeCounter();
        target.visitBy(nCounter);
        this.nodeCount = nCounter.count;
        
        if(nodeCount > 1)
            this.nodeToMutate = random.nextInt(nodeCount);
    }

    public void visit() {
        target.visitBy(this);
    }

    public void visit(Symbol sym) {
        if (notANode(sym)) return;
        
        if (nodeToMutate == visitCount) {
            Node node = (Node) sym;
            if (random.nextBoolean()) {
                node.setLeft(crossoverNode);
            } else {
                node.setRight(crossoverNode);
            }
        }
        visitCount++;
    }
    
    private boolean notANode(Symbol sym) {
        return !(sym instanceof Node);
    }
}
