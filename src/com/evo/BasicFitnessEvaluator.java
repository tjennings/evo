package com.evo;

import java.util.List;
import java.util.ArrayList;

public class BasicFitnessEvaluator implements FitnessEvaluator {
    List<Criteria> criterias = new ArrayList<Criteria>(0);

    public Criteria criteria() {
        Criteria criteria = new Criteria();
        criterias.add(criteria);
        return criteria;
    }

    public Criteria criteria(Criteria criteria) {
        criterias.add(criteria);
        return criteria;
    }

    public void evaluate(Individual indiv) {
        if(indiv.isEvaluated()) return;

        float error = 0.0f;
        for(Criteria crit : criterias) {
            crit.setVars(indiv);
            error += Math.abs(indiv.result() - crit.expected());
        }
        indiv.setError(error / criterias.size());
    }
}