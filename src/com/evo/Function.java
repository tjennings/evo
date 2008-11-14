package com.evo;

public interface Function{
    Symbol statement(Symbol l, Symbol r);
    float evaluate(Symbol l, Symbol r);
}
