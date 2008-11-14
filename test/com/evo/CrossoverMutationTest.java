package com.evo;

import junit.framework.TestCase;
import com.evo.functions.Add;

import java.util.Set;
import java.util.HashSet;


public class CrossoverMutationTest extends TestCase {
    private Individual bestIndiv;
    private Population population;
    private FitnessEvaluator evaluator;
    private Selector selector;
    private BasicIndividual secondBestIndiv;

    public void setUp() {
        Symbol best = new Add().statement(new Constant(1), new Constant(1));
        bestIndiv = new BasicIndividual(best);
        Symbol secondBest = new Add().statement(new Constant(-3), new Constant(4));
        secondBestIndiv = new BasicIndividual(secondBest);

        evaluator = new BasicFitnessEvaluator() {{
            criteria().expects(2);
        }};
        
        population = new Population(evaluator) {{
            add(bestIndiv);
            add(secondBestIndiv);
        }};

        selector = new ElitistTournamentSelector(population);
    }

    public void testCrossover() {
        CrossoverMutation mutation = new CrossoverMutation();
        Individual result = mutation.mutate(selector);

        Set<String> solutionSet = new HashSet<String>() {{
            add("(1.0 + -3.0)");
            add("(1.0 + 4.0)");
            add("(-3.0 + 1.0)");
            add("(4.0 + 1.0)");
            add("(1.0 + (-3.0 + 4.0))");
            add("((-3.0 + 4.0) + 1.0)");
        }};
        
        System.out.println(result);

        assertSolutionIn(solutionSet, result);
    }
    
    public void testCrossoverShouldNotCauseSideEffectsWithVariables() {
        population.clear();
        Variable x = new Variable("X");
        Variable y = new Variable("Y");
        
        Symbol best = new Add().statement(x, x);
        bestIndiv = new BasicIndividual(best);
        Symbol secondBest = new Add().statement(y, y);
        secondBestIndiv = new BasicIndividual(secondBest);
        
        population.add(bestIndiv);
        population.add(secondBestIndiv);
        
        CrossoverMutation mutation = new CrossoverMutation();
        Individual child = mutation.mutate(selector);
        float progResult = child.result();
        
        bestIndiv.setVar("X", 10);
        secondBestIndiv.setVar("Y", 100);
        
        assertEquals("Result from child should not change", 
                progResult, child.result());
    }
    
    public void testCrossoverSingleTerminalPrograms() {
        population.clear();
        population.add(new BasicIndividual(new Constant(3)));
        population.add(new BasicIndividual(new Constant(1)));
        
        CrossoverMutation mutation = new CrossoverMutation();
        Individual child = mutation.mutate(selector);
        
        assertEquals(3.0f, child.result());
    }

    private void assertSolutionIn(Set<String> solutionSet, Individual result) {
        for(String solution : solutionSet) {
            if(solution.equals(result.toString())) {
                return;
            };
        }
        fail("Solution not in set: [" + result.toString() + "]");
    }
}
