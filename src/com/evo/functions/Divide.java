package com.evo.functions;

import com.evo.Function;
import com.evo.Symbol;
import com.evo.Node;

public class Divide implements Function {

    public Node statement(Symbol l, Symbol r) {
        return new Node(this, l, r);
    }

    public float evaluate(Symbol l, Symbol r) {
        //Bypassing divide by zero operations.
        //Not a great solution, copied from TinyGP
        if(0.0f == r.result())
            return l.result();
        return l.result() / r.result();
    }

    public String toString() {
        return " / ";
    }
}

