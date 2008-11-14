package com.evo;

import com.evo.functions.Add;
import java.util.Collections;
import junit.framework.TestCase;

public class FitnessEvaluatorWithVariablesTest extends TestCase {
    private RandomTournamentSelector selector;
    private Population population;


    public void setUp() {
        selector = new RandomTournamentSelector();
    }

    public void testWithOneVariable() {
        Symbol oneVariable = new Add().statement(new Constant(1), new Variable("X"));
        Individual oneVarIndiv = new BasicIndividual(oneVariable);

        final FitnessEvaluator evaluator = new BasicFitnessEvaluator() {{
          criteria().expects(2).var("X", 1);
          criteria().expects(3).var("X", 2);
        }};
        population = new Population(Collections.singletonList(oneVarIndiv), evaluator);
        selector.population(population);

        Individual best = selector.best();
        assertSame(oneVarIndiv, best);
        assertEquals(0.0f, oneVarIndiv.error());
    }

    public void testWithTwoVariables() {
        Symbol twoVar = new Add().statement(new Variable("Y"), new Variable("X"));
        Individual twoVarIndiv = new BasicIndividual(twoVar);


        final FitnessEvaluator evaluator = new BasicFitnessEvaluator() {{
          criteria().expects(9).var("Y", 5).var("X", 4);
          criteria().expects(8).var("Y", 2).var("X", 6);
        }};

        population = new Population(Collections.singletonList(twoVarIndiv), evaluator);
        selector.population(population);

        Individual best = selector.best();
        assertSame(twoVarIndiv, best);
        assertEquals(0.0f, twoVarIndiv.error());
    }
}
