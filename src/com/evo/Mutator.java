package com.evo;


public interface Mutator {
    MutationSet mutationSet();

    void setSelector(Selector selector);

    void mutate();
}
