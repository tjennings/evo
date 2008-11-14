package com.evo;

import java.util.*;

public class MutationSet {
    List<MutationAndChance> chain = new ArrayList<MutationAndChance>();
    private int shareTaken = 0;

    public void add(float chance, Mutation mutation) {
        float lowerBound = shareTaken;
        float upperBound = shareTaken + chance;
        shareTaken += chance;
        chain.add(new MutationAndChance(lowerBound, upperBound, mutation));
    }

    public Individual mutate(Selector selector) {
        Mutation mut = selectMutation();
        return mut.mutate(selector);
    }

    public Mutation selectMutation() {
        if(chain.isEmpty())
            throw new IllegalStateException("Cannot call selectMutation() on an empty MutationSet");
        if(shareTaken > 100)
            throw new IllegalStateException("Cannot allocate more than 100%, currently allocated: " + shareTaken);

        float chance = Randomizer.nextFloat() * 100;
        for(MutationAndChance mut : chain) {
            if(mut.chanceWithinBounds(chance))
                return mut.mutation;
        }
        throw new IllegalStateException("No mutation found rand was: " + chance);
    }

    private class MutationAndChance {
        public Mutation mutation;
        public float lowerBound;
        public float upperBound;

        MutationAndChance(float lowerBound, float upperBound, Mutation mutation) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
            this.mutation = mutation;
        }

        public boolean chanceWithinBounds(float chance) {
            return lowerBound <= chance && upperBound >= chance;
        }

    }
}
