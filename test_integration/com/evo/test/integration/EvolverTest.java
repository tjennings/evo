package com.evo.test.integration;

import com.evo.*;
import com.evo.functions.Add;
import com.evo.functions.Divide;
import com.evo.functions.Multiply;
import com.evo.functions.Subtract;
import junit.framework.TestCase;

/**
 * Trying to evolve a solution to 1 + 1 = 2
 */
public class EvolverTest extends TestCase {
    
    public void testSolveOnePlusOneEqualsTwo() {
        final Grammar grammar = new Grammar() {{
            constant(1);
            function(new Add());
        }};
        
        //Evaluator
        final FitnessEvaluator evaluator = new BasicFitnessEvaluator() {{
            criteria().expects(2);
        }};                
        
        
        Evolver e = Evolver.create(grammar, evaluator);        
        e.generationsToRun(1000);        
        
        Individual solution = e.run();        
        assertNotNull(solution);
        assertEquals(2.0f, solution.result());
        
    }
}
