package com.evo;

import com.evo.visitor.MutationApplier;
import com.evo.visitor.CrossoverSelector;
import java.util.Random;

public class CrossoverMutation implements Mutation{
    static Random random = new Random(); //TODO: Consolidate this with Randomizer.
    
    public Individual mutate(Selector selector) {
        Individual child = selector.best().clone();
        Individual mutationSource = selector.secondBest().clone();
        
        CrossoverSelector xOverSel = new CrossoverSelector(mutationSource);
        xOverSel.visit();
              
        MutationApplier xOverApp = new MutationApplier(child, xOverSel.getCrossover());
        xOverApp.visit();
        
        return child;
    }    
}