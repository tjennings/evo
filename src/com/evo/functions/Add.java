package com.evo.functions;

import com.evo.Function;
import com.evo.Symbol;
import com.evo.Node;


public class Add implements Function {

    public Node statement(Symbol l, Symbol r) {
        return new Node(this, l, r);
    }

    public float evaluate(Symbol l, Symbol r) {
        return l.result() + r.result();
    }

    public String toString() {
        return " + ";
    }
}
