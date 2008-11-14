package com.evo;

import com.evo.functions.Add;
import com.evo.functions.Multiply;
import junit.framework.TestCase;

import java.util.List;

public class ProgramGeneratorTest extends TestCase {
   
    public void testGrammarShouldCalculateOnePlusOne() {
        Grammar grammar = new Grammar() {{
           constant(1.0f);
           function(new Add());
        }};

        RandomPopulator generator = new RandomPopulator(grammar);
        generator.setMaxDepth(1); //can only generate one node

        assertProgramFound("(1.0 + 1.0)", 100, generator);
    }

    public void testGenerateTwotimesTwoPlusOne() {
        Grammar grammar = new Grammar() {{
           constant(2.0f);
           constant(1.0f);
           function(new Add());
           function(new Multiply());
        }};

        RandomPopulator generator = new RandomPopulator(grammar);
        generator.setMaxDepth(2);
        assertProgramFound("((2.0 * 2.0) + 1.0)", 1000, generator);
    }

    public void testGenerateWithVariables() {
        Grammar grammar = new Grammar() {{
           variable("X");
           function(new Add());
        }};

        grammar.setVarValue("X", 10);

        RandomPopulator generator = new RandomPopulator(grammar);
        generator.setMaxDepth(2);
        assertProgramFound("(X + X)", 1000, generator);
        assertSolution(20.0f, 1000, generator);
    }

    public void testGenerateIndividual() {
        Grammar grammar = new Grammar() {{
           constant(2.0f);
           constant(1.0f);
           function(new Add());
           function(new Multiply());
        }};

        Populator generator = new RandomPopulator(grammar);
        generator.setMaxDepth(2);
        assertNotNull(generator.newIndividual());
    }                                                                          

    public void testGeneratePopulation() {
        Grammar grammar = new Grammar() {{
           constant(2.0f);
           constant(1.0f);
           function(new Add());
           function(new Multiply());
        }};

        Populator generator = new RandomPopulator(grammar);
        generator.setMaxDepth(10);

        List<Individual> population = generator.newPopulation(10000);

        assertEquals(10000, population.size());
        for(Individual indiv : population) {
            assertNotNull(indiv);
        }
    }
    
    public void testGenerateWithRandoms() {
        Grammar grammar = new Grammar() {{
            random_constants(100).range(-5).to(5);
        }};

        Populator generator = new RandomPopulator(grammar);
        generator.setMaxDepth(0);

        List<Individual> population = generator.newPopulation(10000);

        assertEquals(10000, population.size());
        for(Individual indiv : population) {
            assertTrue(5.0f >= indiv.result());
            assertTrue(-5.0f <= indiv.result());
        }
    }

    public void testFunctionArity() {}

    private void assertProgramFound(String solution, int maxTries, RandomPopulator generator) {
        for(int i = 0; i < maxTries; i++) {
          Symbol root = generator.grow();
          if(solution.equals(root.toString())) {
              return;
          }
        }
        fail("Solution " + solution + " not found in " + maxTries + " tries");
    }

    private void assertSolution(float expected, int maxTries, RandomPopulator generator) {
        for(int i = 0; i < maxTries; i++) {
          Symbol root = generator.grow();
          if(expected == root.result()) {
              return;
          }
        }
        fail("did not get result " + expected + " in " + maxTries + " tries");
    }
}
