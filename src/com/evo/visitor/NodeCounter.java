package com.evo.visitor;

import com.evo.Node;
import com.evo.Symbol;

public class NodeCounter implements NodeVisitor {

    public int count = 0;

    public void visit(Symbol node) {
        if (node instanceof Node) {
            count++;
        }
    }
}
