package com.evo;

public class BasicMutator implements Mutator {
    private MutationSet mutationSet;
    private Selector selector;

    public MutationSet mutationSet() {
        if(null == this.mutationSet)
            this.mutationSet = new MutationSet();
        return mutationSet;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public void mutate() {
        Individual child = mutationSet.mutate(selector);
        selector.replaceWith(child);
    }
}
