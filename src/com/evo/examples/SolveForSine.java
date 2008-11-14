package com.evo.examples;

import com.evo.BasicFitnessEvaluator;
import com.evo.Evolver;
import com.evo.FitnessEvaluator;
import com.evo.Grammar;
import com.evo.Individual;
import com.evo.functions.Add;
import com.evo.functions.Divide;
import com.evo.functions.Multiply;
import com.evo.functions.Subtract;

public class SolveForSine {
    public static void main(String[] args) {
        final Grammar grammar = new Grammar() {{
            random_constants(100).range(-5).to(5);
            variable("X");
            function(new Add());
            function(new Subtract());
            function(new Multiply());
            function(new Divide());
        }};
        
        //Evaluator
        final FitnessEvaluator evaluator = new BasicFitnessEvaluator() {{
            criteria().expects(0).var("X", 0);
            criteria().expects(0.1f).var("X", 0.0998334166468282f);
            criteria().expects(0.2f).var("X", 0.198669330795061f);
            criteria().expects(1.8f).var("X", 0.973847630878195f);
            criteria().expects(2.9f).var("X", 0.239249329213982f);
            criteria().expects(4f).var("X", 0.198669330795061f);
            criteria().expects(6.2f).var("X", -0.756802495307928f);
        }};                
                
        Evolver e = Evolver.create(grammar, evaluator); 
        e.generationsToRun(100000);     
        e.solutionThreshold(0.01f);
        
        Individual solution = e.run();
        Individual best = e.best();
        //System.out.println(best + " : " + best.error());                
        System.out.println(solution);
    }
}
