package com.evo;

import com.evo.visitor.MutationApplier;

public class RandomMutation implements Mutation {
    private Populator populator;
    
    public RandomMutation(Populator populator) {
        this.populator = populator;
    }
    
    public Individual mutate(Selector selector) {
        Individual child = selector.best().clone();
        
        Symbol mutation = populator.grow();
        
        MutationApplier xOverApp = new MutationApplier(child, mutation);
        xOverApp.visit();
        return child;
    }
}