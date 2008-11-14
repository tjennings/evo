package com.evo;

public interface FitnessEvaluator {
    void evaluate(Individual indiv);
    public Criteria criteria();
    public Criteria criteria(Criteria criteria);
}
