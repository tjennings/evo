package com.evo;

public class IndividualFactory {
    public static Individual create(Symbol programRoot) {
        return new BasicIndividual(programRoot);
    }
}
